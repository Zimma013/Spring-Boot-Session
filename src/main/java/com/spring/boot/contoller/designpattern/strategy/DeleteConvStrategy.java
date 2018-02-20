package com.spring.boot.contoller.designpattern.strategy;

import com.spring.boot.model.Conversation;
import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class DeleteConvStrategy implements Strategy {

    // usuwanie wybranej konwersacji
    @Override
    public String execute(WebRequest request, Model m,
                          RedirectAttributes redirected, ConversationDB conversationDB) {
        String userToken = request.getParameter("t");
        Conversation selectedConv = conversationDB
                .findConvByCID(userToken, request.getParameter("convName"));
        if(conversationDB.convListSize(userToken) > 1) {
            if(selectedConv.isActive()) {
                conversationDB.findOneDeactivated(userToken).setActive(true);
                selectedConv.setActive(false);
            }
        conversationDB.removeConversation(userToken,selectedConv);
        } else {
            selectedConv.clearAll();
            selectedConv.setActive(true);
        }
        //przekazaniue dalej tokena klienta
        redirected.addAttribute("t",request.getParameter("t"));
        return "redirect:/mainPage";
    }
}
