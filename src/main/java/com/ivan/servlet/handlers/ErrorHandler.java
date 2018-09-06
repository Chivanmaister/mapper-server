package com.ivan.servlet.handlers;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler {

  public void handle(Exception e, HttpServletResponse response) throws IOException {


    int errorCode = 0;
    String message = e.getMessage();
    if (e instanceof ServiceException) {
      errorCode = ((ServiceException) e).getErrorCode();
    }
    JsonUtils.createErrorJson(response, errorCode, message);
  }
}
