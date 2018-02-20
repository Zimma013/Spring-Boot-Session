package com.spring.boot.model;

import java.util.HashMap;
import java.util.UUID;

public class Conversation implements SessionStoreAttribute {

    private String name;
    private String cID;
    private boolean isActive;

    /*
                  attName, attValue
    */
    private HashMap<String, String> dataMap;

    public Conversation(String name) {
        this.cID = UUID.randomUUID().toString();
        this.dataMap = new HashMap<>();
        this.isActive = true;
        this.name ="Konwersacja Nr " + name;
    }

    public String getcID() {
        return cID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //Wyczyszczenie wartości parametrów zapisanych w konwersacji (w dataMap)
    public void clearAll() {
        this.setActive(false);
        this.setName("Konwersacja Pusta");
        for(String key : dataMap.keySet()) {
            this.cleanupAttribute(key);
        }
    }

    @Override
    public void storeAttribute(String attributeName, String attributeValue) {

        this.dataMap.put(attributeName, attributeValue);
    }

    @Override
    public void editAttribute(String attributeName, String attributeValue) {

        this.dataMap.replace(attributeName, attributeValue);
    }

    @Override
    public Object retrieveAttribute(String attributeName) {

        return this.dataMap.get(attributeName);
    }

    @Override
    public void cleanupAttribute(String attributeName) {

        this.dataMap.replace(attributeName, "");
    }
}
