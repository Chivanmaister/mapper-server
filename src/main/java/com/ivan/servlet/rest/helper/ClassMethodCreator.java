package com.ivan.servlet.rest.helper;

public class ClassMethodCreator {
    private Class handlerClass;
    private String method;

    public ClassMethodCreator(Class handlerClass, String method) {
        this.handlerClass = handlerClass;
        this.method = method;
    }

    public Class getHandlerClass() {
        return handlerClass;
    }

    public String getMethod() {
        return method;
    }
}
