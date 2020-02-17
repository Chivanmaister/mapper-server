package com.ivan.servlet.controllers.user;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class GetUserHandler {

  private final Service service;

  public GetUserHandler(Service service) {
    this.service = service;
  }

  @GetMapping("/user/getByEmail")
  public String getUserByEmail(@RequestParam("email") String email) throws IOException, ServiceException {
    StringWriter response = new StringWriter();
    service.getService(UserService.class).validateUserExists(email);
    User user = service.getService(UserService.class).getUser(email);
    JsonUtils.createUserResponse(response, user);

    return response.toString();
  }

  @GetMapping("/user/getById")
  public String getUserById(@RequestParam("id") Integer userId) throws ServiceException, IOException {
    StringWriter response = new StringWriter();
    service.getService(UserService.class).validateUserExists(userId);
    User user = service.getService(UserService.class).getUser(userId);
    JsonUtils.createUserResponse(response, user);

    return response.toString();
  }
}
