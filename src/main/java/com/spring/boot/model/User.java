package com.spring.boot.model;

import java.util.UUID;

public class User {

    private String name;

    private String password;

    private String token;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.token = UUID.randomUUID().toString();
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
