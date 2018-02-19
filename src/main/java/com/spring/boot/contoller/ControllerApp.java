package com.spring.boot.contoller;

import com.spring.boot.model.Conversation;
import com.spring.boot.model.ConversationDB;
import com.spring.boot.model.ModelApp;
import com.spring.boot.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;

@Controller
public class ControllerApp {

    @Autowired
    private ModelApp model;

    @Autowired
    private UserDB userDB;

    @Autowired
    private ConversationDB conversationDB;

    private String message = "";

    private Object tmp="";
    private Object tmp2="";
    private Object txt="";

    @RequestMapping("/")
    public String home(WebRequest request, Model m){
        message = "Witaj - wprowadź swoje dane";
        m.addAttribute("login_msg",message);
        return "/login";
    }
    @RequestMapping("/login")
    public String login(WebRequest request,Model model2){

        /*
        if(request.getParameter("formularz_text") != null){

            model.storeAttribute(request,"formularz_text", request.getParameter("formularz_text"));

            System.out.println(model.retrieveAttribute(request,"formularz_text"));
        }
        */

        return "/login";
    }

    @RequestMapping(value = "/formularz")
    public String formularz(WebRequest request,Model model2){

        String token = request.getParameter("t");
        //System.out.print("token w /formularz : "+token);
        Object form_text_conversation;

        //this.userDB.printAll();
        form_text_conversation = this.conversationDB.findActiveConversation(token)
                .retrieveAttribute("form_text");

        model2.addAttribute("list_var",this.conversationDB.printConvList(token));
        model2.addAttribute("form_text_var",form_text_conversation);
        model2.addAttribute("t","saveForm?t="+token);
        model2.addAttribute("redirectCRUD","convCRUD?t="+token);

        //redirected.addAttribute("t",token);

        return "/formularz";
    }
    @RequestMapping(value = "/saveForm")
    public String saveForm(WebRequest request, RedirectAttributes redirected){

        String token = request.getParameter("t");

        System.out.println("SAVE FORM token : "+token);
        System.out.println("TEXT FROM REQUEST : "+request.getParameter("form_text"));
        this.conversationDB.findActiveConversation(token)
                .editAttribute("form_text",request.getParameter("form_text"));

        redirected.addAttribute("t",token);

        return "redirect:/formularz";
    }


    /*Mapowanie walidacji Logowania*/

    @RequestMapping(value = "/loginValidate", method = {RequestMethod.POST, RequestMethod.GET})
    public String validateLogin(WebRequest request, Model m, RedirectAttributes redirected){

        if(this.userDB.userExists(request)){
            if(this.userDB.checkPassword(request)){
                String  token = this.userDB.getToken(request.getParameter("login"));
                redirected.addAttribute("t",token);

                this.conversationDB.newConversation(token);
                //this.conversationDB.newConversation(this.userDB.getToken(request.getParameter("login")));

                this.conversationDB.findActiveConversation(token).storeAttribute("form_text","");

                return "redirect:/formularz";
            }else{
                message = "Podałeś złe hasło!";
                m.addAttribute("login_msg",message);
                return "/login";
            }
        }else{
            message = "Podałeś zły login!";
            m.addAttribute("login_msg",message);
            return "/login";
        }

    }
    /*Przekierowanie Rejestacji*/

    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    public String register(){
        return "/register";
    }


    /*Mapowanie walidacji Rejestacji*/

    @RequestMapping(value = "/registerValidate", method = {RequestMethod.POST, RequestMethod.GET})
    public String validateRegister(WebRequest request, Model m){

        if(this.userDB.userExists(request)){
           message = "Login zajęty!";
           m.addAttribute("register_msg",message);
           return "/register";

        }else{
            if(request.getParameter("password").equals(request.getParameter("password2"))){
                this.userDB.register(request);
                message = "Twoje konto zostało założone. Zaloguj się:";
                m.addAttribute("login_msg",message);
                return "/login";
            }else{
                message = "Podane hasła się nie zgadzają!";
                m.addAttribute("register_msg",message);
                return "/register";
            }

        }

    }

    @RequestMapping("/convCRUD")
    public String convCRUD(WebRequest request,  RedirectAttributes redirected){

        String type = request.getParameter("submitType");

        System.out.println("token FORM convCRUD : "+request.getParameter("t"));
        String token = request.getParameter("t");
        redirected.addAttribute("t",request.getParameter("t"));

        if(type.equals("1")) {
            return "/formNameChange";
        } else if (type.equals("2")) {
            Conversation conv = this.conversationDB.findOneDeactivated(request.getParameter("t"));
            if (conv != null) {
                this.conversationDB.findActiveConversation(request.getParameter("t"))
                        .setActive(false);
                conv.setActive(true);
            }

            return "redirect:/formularz";
        } else{
            System.out.println("CID FORM PARAMETER CONVNAME : "+request.getParameter("convName"));


            Conversation returnedConv = this.conversationDB.findConvByCID(token, request.getParameter("convName"));
            System.out.println("CID FORM PARAMETER RETURNED : "+returnedConv.getcID());

            System.out.println("CID FORM ACTIVE : "+this.conversationDB.findActiveConversation(request.getParameter("t")).getcID());

            System.out.println("IF OLD IS ACTIVE : "+this.conversationDB.findActiveConversation(request.getParameter("t")).isActive());


            this.conversationDB.findActiveConversation(request.getParameter("t")).setActive(false);


            returnedConv.setActive(true);

            System.out.println("IF NEW IS ACTIVE : "+this.conversationDB.findActiveConversation(request.getParameter("t")).isActive());

            //this.conversationDB.findActiveConversation(request.getParameter("t")).i
            return "redirect:/formularz";
        }

    }

}