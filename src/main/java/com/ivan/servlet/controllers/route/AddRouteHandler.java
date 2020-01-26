package com.ivan.servlet.controllers.route;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class AddRouteHandler {

  @Autowired
  Service service;

  @PostMapping("/route/add")
  public String addRoute(@RequestParam("userId") Integer userId, @RequestParam("name") String routeName) {
    StringWriter response = new StringWriter();
    Route route;
    try {
      service.getService(UserService.class).validateUserExists(userId);
      route = service.getService(RouteService.class).addRoute(routeName, userId);
      JsonUtils.createRouteResponse(response, route);
    } catch (ServiceException se) {
      String message = "error adding route";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_ROUTE_NAME, message);
      } catch (IOException ignored) {
      }
    } catch (IOException oe) {
      String message = "error writing route";
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
