package com.spring.boot.contoller.designpattern.strategy;

import com.spring.boot.model.ConversationDB;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface Strategy {
    // funkcja przetwarzająca żądanie
    String execute(WebRequest request, Model m, RedirectAttributes redirected, ConversationDB conversationDB);
}
