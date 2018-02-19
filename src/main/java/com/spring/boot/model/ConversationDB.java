package com.spring.boot.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class ConversationDB {

    /*token, List of Conversations*/
    private HashMap<String, ArrayList<Conversation>> userCIDsMap;

    public ConversationDB() {
        this.userCIDsMap = new HashMap<>();
    }

    public Conversation findActiveConversation(String token){

        System.out.print("token : "+token);
        for (Conversation conv: this.userCIDsMap.get(token)) {
            if(conv.isActive()){
                System.out.println("ZNALEZIONO AKTYWNA");
                return conv;
            }
        }
        return null;
    }

    public Conversation findConvByCID(String token, String cID){

        if(userCIDsMap.get(token).size() == 1)
            if(userCIDsMap.get(token).get(0).isActive())
                return userCIDsMap.get(token).get(0);
            else return null;

        ArrayList<Conversation> list = this.userCIDsMap.get(token);
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getcID().equals(cID)){
                return list.get(i);
            }
        }
        return null;
    }


    public String printConvList(String token){
        String out = "";
        if(!userCIDsMap.get(token).isEmpty()) {
            for (Conversation conv : this.userCIDsMap.get(token)) {
                out += "<input type = \"radio\"  name=\"convName\" " +
                        "value= \"" + conv.getcID() + "\"";
                    if(conv.isActive())
                        out += " checked=\"checked\"";
                out += ">"+conv.getName()+ "<br />";
            }
            //System.out.println("test " + out);
        }
        //System.out.println("test " + out);
        return out;
    }

    public Object retrieveAttribute(String token, String attributeName){

        return this.findActiveConversation(token).retrieveAttribute(attributeName);
    }


    public HashMap<String, ArrayList<Conversation>> getUserCIDsMap() {
        return userCIDsMap;
    }

    public void newConversation(String token) {
        if(this.userCIDsMap.containsKey(token)){
            Conversation conv = findActiveConversation(token);
            conv.setActive(false);
            this.userCIDsMap.get(token).add(new Conversation());

        }else{
            ArrayList<Conversation> newList = new ArrayList<>();
            newList.add(new Conversation());
            this.userCIDsMap.put(token,newList);
        }
    }

    public void removeConversation(String token){
        Conversation conv = findActiveConversation(token);

    }

    public Conversation findOneDeactivated(String token) {
        ArrayList<Conversation> conv = this.userCIDsMap.get(token);
        for( int i = 0; i < conv.size(); i++){
            if(!conv.get(i).isActive()) {
                return conv.get(i);
            }
        }
        return null;
    }
    /*
    public void deleteConversation(String token, String cID) {
        return userCIDsMap;
    }
    public HashMap<String, ArrayList<String>> getUserCIDsMap() {
        return userCIDsMap;
    }
    public HashMap<String, ArrayList<String>> getUserCIDsMap() {
        return userCIDsMap;
    }
    public HashMap<String, ArrayList<String>> getUserCIDsMap() {
        return userCIDsMap;
    }
    */

}
