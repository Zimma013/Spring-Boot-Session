package com.spring.boot.model;

public interface SessionStoreAttribute {

    void storeAttribute(String attributeName, String attributeValue);

    Object retrieveAttribute(String attributeName);

    void cleanupAttribute(String attributeName);

    void editAttribute(String attributeName, String attributeValue);
}
