package com.ivan.servlet.controllers;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;

@Controller
public class ErrorHandler implements ErrorController {

  private static final String EXCEPTION_OBJECT = "org.springframework.boot.autoconfigure.web.DefaultErrorAttributes.ERROR";

  @RequestMapping("/error")
  @ResponseBody
  public String handleError(HttpServletRequest request) throws IOException {
    ServiceException exception = (ServiceException) request.getAttribute(EXCEPTION_OBJECT);

    StringWriter stringWriter = new StringWriter();
    JsonUtils.createErrorResponse(stringWriter, exception.getErrorCode(), exception.getMessage());
    return stringWriter.toString();
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
