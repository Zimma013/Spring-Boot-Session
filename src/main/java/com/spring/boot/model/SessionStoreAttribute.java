package com.spring.boot.model;

import org.springframework.web.context.request.WebRequest;

public interface SessionStoreAttribute {

    void storeAttribute(String attributeName, String attributeValue);

    Object retrieveAttribute(String attributeName);

    void cleanupAttribute(String attributeName);
}
