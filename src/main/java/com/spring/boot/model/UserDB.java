package com.spring.boot.model;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@Repository
public class UserDB {

                /*  login, User */
    private HashMap<String, User> loginUserMap;

    public UserDB() {
        this.loginUserMap = new HashMap<>();
        this.initialPopulation();
    }

    private void initialPopulation() {
        this.loginUserMap.put("admin", new User("admin","test"));
    }

    public String getToken(String login){
        return this.loginUserMap.get(login).getToken();
    }

    public void register(WebRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        loginUserMap.put(login, new User(login,password));
    }

    // identyfikacja klienta na podstawie loginu przekazanego w zapytaniu
    public boolean userExists(WebRequest request) {
        User userFound = loginUserMap.get(request.getParameter("login"));

        if(userFound != null) {
            return true;
        }else
            return false;
    }

    // Sprawdzenie zgodności hasła z zapytania z hasłem w bazie
    public boolean checkPassword(WebRequest request){
        String passFromDB = this.loginUserMap.get(request.getParameter("login")).getPassword();
        String passFromRequest = request.getParameter("password");

        return passFromDB.equals(passFromRequest);
    }

    // Wypisanie wszystkich użytkowników w bazie
    public void printAll(){
        for(String userStr : this.loginUserMap.keySet()) {
            System.out.println("login: " + userStr +
                    " haslo: " + this.loginUserMap.get(userStr).getPassword()
            + " token: " + this.loginUserMap.get(userStr).getToken());
        }
    }
}
