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

    // znalezienie aktywnej konwersacji klienta na podstawie jego tokena
    public Conversation findActiveConversation(String token){
        if (this.userCIDsMap.get(token) == null) {
            return null;
        } else {
            for (Conversation conv: this.userCIDsMap.get(token)) {
                if(conv.isActive()){
                    return conv;
                }
            }
        }
        return null;
    }

    // znalezienie konwersacji klienta przy użyciu cID
    public Conversation findConvByCID(String token, String cID){

        // jeśli istnieje tylko jedna konwersacja jest ona zwracana jeśli zgadza się cID
        if(userCIDsMap.get(token).size() == 1){
            if(userCIDsMap.get(token).get(0).getcID().equals(cID)){
                return userCIDsMap.get(token).get(0);
            } else {
                return null;
            }
        // jeśli istnieje więcej konwersacji jest ona poszukiwana w pętli
        } else {
            ArrayList<Conversation> list = this.userCIDsMap.get(token);
            for(int i = 0; i < list.size(); i++) {
                if (list.get(i).getcID().equals(cID)){
                    return list.get(i);
                }
            }
            return null;
        }
    }

    // funkcja zwraca String zawierający listę radio-buttonów konwersacji
    public String printConvList(String token){
        String out = "";
        if(!userCIDsMap.get(token).isEmpty()) {
            for (Conversation conv : this.userCIDsMap.get(token)) {
                // wyróżnienie aktywnej obserwacji
                if(conv.isActive()){
                    out += "<p class=\"conv_choice\">" +
                            "<input type = \"radio\"  name=\"convName\" " +
                            "value= \"" + conv.getcID() + "\" checked=\"checked\">" +
                            "<u>" + conv.getName()+ " </u></p>";
                } else {

                    out += "<p class=\"conv_choice\">" +
                            "<input type = \"radio\"  name=\"convName\" " +
                            "value= \"" + conv.getcID() + "\">" +
                            conv.getName() + "</p>";
                }
            }
        }
        out += "<br/>";
        return out;
    }

    // zwraca wartość atrybutu z aktywnej konwersacji
    public Object retrieveAttribute(String token, String attributeName){

        return this.findActiveConversation(token).retrieveAttribute(attributeName);
    }

    // tworzenie nowej konwersacji
    public void newConversation(String token) {

        if(this.userCIDsMap.get(token) == null) {
            this.userCIDsMap.put(token, new ArrayList<>());
        }
        Conversation newConversation = new Conversation(String.valueOf(this.userCIDsMap.get(token).size()+1));

        //inicjalizacja atrybutów ze strony konwersacji
        newConversation.storeAttribute("form_text","");
        newConversation.storeAttribute("form_name","");
        newConversation.storeAttribute("form_surname","");
        //checkboxy jezyki
        newConversation.storeAttribute("lang_c","");
        newConversation.storeAttribute("lang_j","");
        newConversation.storeAttribute("lang_pa","");
        newConversation.storeAttribute("lang_py","");
        newConversation.storeAttribute("lang_a","");
        // checkboxy wzorce
        newConversation.storeAttribute("design_builder","");
        newConversation.storeAttribute("design_abstractfactory","");
        newConversation.storeAttribute("design_factorym","");
        newConversation.storeAttribute("design_prototype","");
        newConversation.storeAttribute("design_singleton","");
        newConversation.storeAttribute("design_adapter","");
        newConversation.storeAttribute("design_decorator","");
        newConversation.storeAttribute("design_fasade","");
        newConversation.storeAttribute("design_composite","");
        newConversation.storeAttribute("design_bridge","");
        newConversation.storeAttribute("design_proxy","");
        newConversation.storeAttribute("design_flyweight","");
        newConversation.storeAttribute("design_interpreter","");
        newConversation.storeAttribute("design_iterator","");
        newConversation.storeAttribute("design_chain","");
        newConversation.storeAttribute("design_mediator","");
        newConversation.storeAttribute("design_template","");
        newConversation.storeAttribute("design_observer","");
        newConversation.storeAttribute("design_visitor","");
        newConversation.storeAttribute("design_memento","");
        newConversation.storeAttribute("design_command","");
        newConversation.storeAttribute("design_state","");
        newConversation.storeAttribute("design_strategy","");

        // jeśli użytkownik posiada aktywną konwersacje
        // to nowa zostanie stworzona jako aktywna
        // a stara zostanie zdezaktywowana
        if(!this.userCIDsMap.get(token).isEmpty()){
            Conversation conv = findActiveConversation(token);
            conv.setActive(false);
            this.userCIDsMap.get(token)
                    .add(newConversation);

        // dodawana jest nowa konwersacja
        }else{
            this.userCIDsMap.get(token).add(newConversation);
        }
    }

    // usuwanie konwersacji
    public boolean removeConversation(String token, Conversation conv){
        return this.userCIDsMap.get(token).remove(conv);
    }

    // zwraca rozmiar listy konwersacji
    public int convListSize(String token) {
        System.out.println("convListSize: "+userCIDsMap.get(token).size());
        return userCIDsMap.get(token).size();
    }

    // zwraca pierwszą nieaktywną konwersacje
    public Conversation findOneDeactivated(String token) {
        ArrayList<Conversation> conv = this.userCIDsMap.get(token);
        for( int i = 0; i < conv.size(); i++){
            if(!conv.get(i).isActive()) {
                return conv.get(i);
            }
        }
        return null;
    }
}
