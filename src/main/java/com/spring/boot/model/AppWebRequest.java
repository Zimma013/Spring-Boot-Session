package com.spring.boot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;

public class AppWebRequest {


    WebRequest appWebRequest;

    public WebRequest getAppWebRequest() {
        return appWebRequest;
    }

    public void setAppWebRequest(WebRequest appWebRequest) {
        this.appWebRequest = appWebRequest;
    }

    public void tell() {
        if(appWebRequest==null){
            System.out.println("jeest");
        }else{
            System.out.println("nieee");
        }
    }


}
