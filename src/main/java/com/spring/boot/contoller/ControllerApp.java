package com.spring.boot.contoller;

import com.spring.boot.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ControllerApp {

    @Autowired
    private Model model;

    @RequestMapping("/")
    public String home(WebRequest request){


      /*  if(request.getParameter("_cid") == null){
            System.out.println("nullllllll");
        }*/
        //conv.getConversationId(request);

        return "/login";
    }
    @RequestMapping("/login")
    public String login(WebRequest request){

        if(request.getParameter("formularz_text") != null){
            model.storeAttribute(request,"formularz_text", request.getParameter("formularz_text"));
            System.out.println(model.retrieveAttribute(request,"formularz_text"));
        }


      /*  if(request.getParameter("_cid") == null){
            System.out.println("nullllllll");
        }*/
        //conv.getConversationId(request);

        return "/login";
    }

    @RequestMapping("/formularz")
    public String formularz(WebRequest request){

        model.storeAttribute(request,"name", request.getParameter("name"));
        model.storeAttribute(request,"password", request.getParameter("password"));

        System.out.println(model.retrieveAttribute(request,"password"));

        return "/formularz";
    }

    @RequestMapping(value = "/waliduj", method = {RequestMethod.POST, RequestMethod.GET})
    public String waliduj(WebRequest request){



        return "/waliduj";
    }
}
