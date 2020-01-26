package com.ivan.servlet.controllers.route;

import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class UpdateRouteHandler {

  @Autowired
  Service service;

  @PostMapping("/route/update")
  public String updateRouteHandler(@RequestParam("id") Integer routeId, @RequestParam("name") String routeName) {
    StringWriter response = new StringWriter();
    try {
      service.getService(RouteService.class).validateRouteExists(routeId);
      service.getService(RouteService.class).updateRouteName(routeId, routeName);
    } catch (ServiceException se) {
      String message = "error getting route";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_ROUTE_ID, message);
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
