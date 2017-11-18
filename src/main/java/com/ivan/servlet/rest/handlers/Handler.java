package com.ivan.servlet.rest.handlers;

import com.ivan.servlet.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler {

    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServiceException;
}
