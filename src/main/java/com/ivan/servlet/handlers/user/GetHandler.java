package com.ivan.servlet.handlers.user;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetHandler implements Handler {
  private UserService userService;

  public GetHandler(RestService userService) throws ServiceException {
    this.userService = userService.getService(UserService.class);
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
    String email = getEmail(request);
    User user = userService.getUser(email);
    JsonUtils.createUserJson(response, user);
  }

  private String getEmail(HttpServletRequest request) throws ServiceException {
    String email = request.getParameter("email");
    userService.validateEmail(email);
    return email;
  }
}
