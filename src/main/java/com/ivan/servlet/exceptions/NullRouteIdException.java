package com.ivan.servlet.exceptions;

public class NullRouteIdException extends ServiceException {

    public NullRouteIdException() {
        super("Null route id");
    }

    public NullRouteIdException(String message) {
        super(message);
    }
}
