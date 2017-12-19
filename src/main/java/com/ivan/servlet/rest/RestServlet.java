package com.ivan.servlet.rest;

import com.ivan.servlet.handlers.ErrorHandler;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.rest.helper.HandlerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
    String method = servletRequest.getMethod();

    String requestURI = servletRequest.getRequestURI();
    HandlerFactory handlerFactory = new HandlerFactory();
    try {
      Handler handler = (Handler) handlerFactory.getDispatcher(requestURI, method);
      handler.handle(servletRequest, servletResponse);
    } catch (Exception e) {
      ErrorHandler errorHandler = new ErrorHandler();
      errorHandler.handle(e, servletResponse);
    }
    servletResponse.setHeader("Content-Type", "application/json");
    servletResponse.setCharacterEncoding("UTF-8");
  }
}
