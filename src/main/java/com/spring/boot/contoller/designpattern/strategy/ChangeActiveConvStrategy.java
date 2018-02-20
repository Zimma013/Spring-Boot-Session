package com.spring.boot.contoller.designpattern.strategy;

import com.spring.boot.model.Conversation;
import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ChangeActiveConvStrategy implements Strategy {

    // Zmiana aktywnej konwersacji na wybraną
    @Override
    public String execute(WebRequest request, Model m,
                          RedirectAttributes redirected, ConversationDB conversationDB) {
        String userToken = request.getParameter("t");
        Conversation returnedConv = conversationDB.findConvByCID(userToken, request.getParameter("convName"));
/*
        System.out.println("CID FORM PARAMETER RETURNED : "+returnedConv.getcID());
        System.out.println("CID FORM ACTIVE : "+conversationDB.findActiveConversation(request.getParameter("t")).getcID());
        System.out.println("IF OLD IS ACTIVE : "+conversationDB.findActiveConversation(request.getParameter("t")).isActive());
*/
        conversationDB.findActiveConversation(request.getParameter("t")).setActive(false);
        returnedConv.setActive(true);

//        System.out.println("IF NEW IS ACTIVE : "+conversationDB.findActiveConversation(request.getParameter("t")).isActive());

        //przekazaniue dalej tokena klienta
        redirected.addAttribute("t",request.getParameter("t"));
        return "redirect:/mainPage";
    }
}
