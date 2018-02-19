package com.spring.boot.model;

import org.springframework.util.Assert;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.UUID;

public class Conversation implements SessionStoreAttribute {
    private String name;
    private String cID;

    private boolean isActive;
    /*attName, attValue*/
    private HashMap<String, String> dataMap;

    public Conversation() {
        this.cID = UUID.randomUUID().toString();
        this.dataMap = new HashMap<>();
        this.isActive = true;
        this.name ="Baba Jaga";
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

    @Override
    public void storeAttribute(String attributeName, String attributeValue) {

        this.dataMap.put(attributeName, attributeValue);
    }

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
