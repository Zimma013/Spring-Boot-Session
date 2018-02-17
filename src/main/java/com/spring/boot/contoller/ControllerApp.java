package com.spring.boot.contoller;

import com.spring.boot.model.ModelApp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerApp {

    @Autowired
    private ModelApp model;
    private Object tmp="";
    private Object tmp2="";
    private Object txt="";

    @RequestMapping("/")
    public String home(WebRequest request){


      /*  if(request.getParameter("_cid") == null){
            System.out.println("nullllllll");
        }*/
        //conv.getConversationId(request);

        return "/login";
    }
    @RequestMapping("/login")
    public String login(WebRequest request,Model model2){

        if(request.getParameter("formularz_text") != null){
            model.storeAttribute(request,"formularz_text", request.getParameter("formularz_text"));
            System.out.println(model.retrieveAttribute(request,"formularz_text"));
        }
        txt = model.retrieveAttribute(request, "formularz_text");

        model2.addAttribute("tmp",tmp);
        model2.addAttribute("tmp2",tmp2);
        return "/login";
    }

    @RequestMapping("/formularz")
    public String formularz(WebRequest request,Model model2){

        model.storeAttribute(request,"name", request.getParameter("name"));
        model.storeAttribute(request,"password", request.getParameter("password"));
        tmp = model.retrieveAttribute(request, "password");
        tmp2 = model.retrieveAttribute(request, "name");
        model2.addAttribute("tekst",txt);
        return "/formularz";
    }

    @RequestMapping(value = "/waliduj", method = {RequestMethod.POST, RequestMethod.GET})
    public String waliduj(WebRequest request){



        return "/waliduj";
    }
}