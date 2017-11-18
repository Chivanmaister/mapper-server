package com.ivan.servlet.rest;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.rest.helper.HandlerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class RestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String method =  servletRequest.getMethod();

        String requestURI = servletRequest.getRequestURI();
        HandlerFactory factory = new HandlerFactory();
        if (!requestURI.equalsIgnoreCase("/")) {
            try {
                Handler handler = (Handler) factory.getDispatcher(requestURI, method);
                handler.handle(servletRequest, servletResponse);
            } catch (ServiceException | IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                //error handle
            }
            servletResponse.setHeader("Content-Type", "application/json");
            servletResponse.setCharacterEncoding("UTF-8");
        }
    }
}
