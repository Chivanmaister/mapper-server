package com.ivan.servlet.controllers.user;


import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class GetUserHandler {

  @Autowired
  Service service;

  @GetMapping("/user/getByEmail")
  public String getUserByEmail(@RequestParam("email") String email) {
    StringWriter response = new StringWriter();
    User user;
    try {
      service.getService(UserService.class).validateUserExists(email);
      user = service.getService(UserService.class).getUser(email);
      JsonUtils.createUserResponse(response, user);
    } catch (ServiceException se) {
      String message = "error adding user";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_EMAIL, message);
      } catch (IOException ignored) {
      }
    } catch (IOException oe) {
      String message = "error writing user";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INTERNAL_ERROR, message);
      } catch (IOException ignored) {
      }
    } catch (Exception e) {
      String message = "unexpected error";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INTERNAL_ERROR, message);
      } catch (IOException ignored) {
      }
    }
    return response.toString();
  }

  @GetMapping("/user/getById")
  public String getUserById(@RequestParam("id") Integer userId) {
    StringWriter response = new StringWriter();
    User user;
    try {
      service.getService(UserService.class).validateUserExists(userId);
      user = service.getService(UserService.class).getUser(userId);
      JsonUtils.createUserResponse(response, user);
    } catch (ServiceException se) {
      String message = "error getting user";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_USER, message);
      } catch (IOException ignored) {
      }
    } catch (IOException oe) {
      String message = "error writing user";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INTERNAL_ERROR, message);
      } catch (IOException ignored) {
      }
    } catch (Exception e) {
      String message = "unexpected error";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INTERNAL_ERROR, message);
      } catch (IOException ignored) {
      }
    }
    return response.toString();
  }
}
