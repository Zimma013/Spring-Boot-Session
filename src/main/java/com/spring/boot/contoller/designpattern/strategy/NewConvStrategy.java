package com.spring.boot.contoller.designpattern.strategy;

import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class NewConvStrategy implements Strategy {

    // stworzenie nowej konwersacji
    @Override
    public String execute(WebRequest request, Model m,
                          RedirectAttributes redirected, ConversationDB conversationDB) {

        String userToken = request.getParameter("t");

        conversationDB.newConversation(userToken);

        //przekazaniue dalej tokena klienta
        redirected.addAttribute("t",request.getParameter("t"));
        return "redirect:/mainPage";
    }
}
