package com.spring.boot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {

    private String name;

    private String password;

    private String token;

    //to wywalic
    private ArrayList<String> cIDlist;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.token = UUID.randomUUID().toString();
        this.cIDlist = new ArrayList<>();
    }

    public ArrayList<String> getcIDlist() {
        return cIDlist;
    }

    public void addTocIDlist(String cID) {
        this.cIDlist.add(cID);
    }

    public void removeFromcIDlist(String cID) {
        int index = this.cIDlist.indexOf(cID);
        this.cIDlist.remove(index);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
