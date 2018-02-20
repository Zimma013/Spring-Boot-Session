package com.spring.boot.contoller.designpattern.facade;

import com.spring.boot.contoller.designpattern.chain.of.responsibility.MenuRequestHandler;
import com.spring.boot.contoller.designpattern.strategy.ChangeActiveConvStrategy;
import com.spring.boot.contoller.designpattern.strategy.DeleteConvStrategy;
import com.spring.boot.contoller.designpattern.strategy.NameChangeStrategy;
import com.spring.boot.contoller.designpattern.strategy.NewConvStrategy;
import com.spring.boot.model.ConversationDB;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Repository
public class CoRFacade {

    private MenuRequestHandler menuRequestHandler;

    // Fasada zarządzająca subsystemem ogniw łańcucha odpowiedzialności
    public CoRFacade() {
        this.menuRequestHandler = new MenuRequestHandler(
                "1", new NameChangeStrategy());
        this.menuRequestHandler.add(new MenuRequestHandler("2", new NewConvStrategy()));
        this.menuRequestHandler.add(new MenuRequestHandler("3", new ChangeActiveConvStrategy()));
        this.menuRequestHandler.add(new MenuRequestHandler("4", new DeleteConvStrategy()));

    }

    // funkcja rozpoczynająca przetwarzanie żądania
    public String run(String context, WebRequest request, Model m, RedirectAttributes redirected, ConversationDB conversationDB) {
        return this.menuRequestHandler.handle(context,request,m,redirected,conversationDB);
    }
}
