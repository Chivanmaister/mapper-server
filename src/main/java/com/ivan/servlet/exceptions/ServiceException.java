package com.ivan.servlet.exceptions;

public class ServiceException extends Exception {

    public ServiceException() {
        super("Unknown service");
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
