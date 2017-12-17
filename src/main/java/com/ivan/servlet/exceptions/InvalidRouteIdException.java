package com.ivan.servlet.exceptions;

public class InvalidRouteIdException extends ServiceException {

    public InvalidRouteIdException() {
        super(ErrorCodes.INVALID_ROUTE_ID, "Null route id");
    }

    public InvalidRouteIdException(String message) {
        super(ErrorCodes.INVALID_ROUTE_ID, message);
    }
}
