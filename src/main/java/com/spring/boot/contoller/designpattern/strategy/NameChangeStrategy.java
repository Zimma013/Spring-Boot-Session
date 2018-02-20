package com.spring.boot.contoller.designpattern.strategy;

import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class NameChangeStrategy implements Strategy{

    // zmiana nazwy wybranej konwersacji
    @Override
    public String execute(WebRequest request, Model m,
                          RedirectAttributes redirected, ConversationDB conversationDB) {

        String userToken = request.getParameter("t");

        //Przekazawnie do widoku starej nazwy konwersacji
        m.addAttribute("convName_var", conversationDB.findConvByCID(
                userToken, request.getParameter("convName"))
                .getName());

        /*
        System.out.println("convNameModel ??: "+conversationDB.findConvByCID(
                userToken, request.getParameter("convName"))
                .getName());
        */
        //przekazanie cID starej konwersacji
        m.addAttribute("convCID_var",request.getParameter("convName"));

        //adres przekierowania do zmiany nazwy wybranej konwersacji
        m.addAttribute("redirect_validateNameChange_var","/validateNameChange?t=" + userToken);

//        System.out.println("CID: "+request.getParameter("convName"));

        return "/convChangeName";
    }
}
