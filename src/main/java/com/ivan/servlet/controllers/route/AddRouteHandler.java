package com.ivan.servlet.controllers.route;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class AddRouteHandler {

  private final Service service;

  public AddRouteHandler(Service service) {
    this.service = service;
  }

  @PostMapping("/route/add")
  public String addRoute(@RequestParam("userId") Integer userId, @RequestParam("name") String routeName) throws IOException, ServiceException {
    StringWriter response = new StringWriter();
    service.getService(UserService.class).validateUserExists(userId);
    Route route = service.getService(RouteService.class).addRoute(routeName, userId);
    JsonUtils.createRouteResponse(response, route);

    return response.toString();
  }
}
