package com.ivan.servlet.controllers.user;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class AddUserHandler {

  private final Service service;

  public AddUserHandler(Service service) {
    this.service = service;
  }

  @PostMapping("/user/add")
  public String addUser(@RequestParam("email") String email) throws ServiceException, IOException {
    StringWriter response = new StringWriter();
    service.getService(UserService.class).validateEmail(email);
    User user = service.getService(UserService.class).addUser(email);
    JsonUtils.createUserResponse(response, user);
    return response.toString();
  }
}
