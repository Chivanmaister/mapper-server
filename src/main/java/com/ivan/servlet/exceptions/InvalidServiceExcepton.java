package com.ivan.servlet.exceptions;

public class InvalidServiceExcepton extends ServiceException {

    public InvalidServiceExcepton() {
        super("Invalid service");
    }

    public InvalidServiceExcepton(String message) {
        super(message);
    }
}
