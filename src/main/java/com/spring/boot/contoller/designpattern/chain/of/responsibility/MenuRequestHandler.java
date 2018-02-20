package com.spring.boot.contoller.designpattern.chain.of.responsibility;

import com.spring.boot.contoller.designpattern.strategy.Strategy;
import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MenuRequestHandler {

    private Strategy strategy;
    private MenuRequestHandler next;
    private String context;

    public MenuRequestHandler(String context, Strategy strategy) {
        this.strategy = strategy;
        this.next = null;
        this.context = context;
    }

    // dodawanie nowego handlera do łańcucha odpowiedzialności
    public void add(MenuRequestHandler next){
        if(this.next == null){
            this.next = next;
        }else {
            this.next.add(next);
        }
    }

    // funkcja przetwarzająca żądanie
    // może zapwenić obsługę lub przekazać je do kolejnego ogniwa
    public String handle(String context, WebRequest request, Model m, RedirectAttributes redirected, ConversationDB conversationDB) {
        if(this.context.equals(context)) {

            return this.strategy.execute(request, m, redirected, conversationDB);
        } else if (this.next != null) {

            return this.next.handle(context, request, m, redirected, conversationDB);
        }
        return null;
    }

}
