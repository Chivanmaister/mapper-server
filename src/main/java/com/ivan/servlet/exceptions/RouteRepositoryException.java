package com.ivan.servlet.exceptions;

public class RouteRepositoryException extends ServiceException {

    public RouteRepositoryException() {
        super("Route repository error");
    }
    public RouteRepositoryException(String message) {
        super(message);
    }
}
