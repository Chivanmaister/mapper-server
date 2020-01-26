package com.ivan.servlet.controllers.route;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class GetRouteHandler {

  Service service;

  public GetRouteHandler(Service service) {
    this.service = service;
  }

  @GetMapping("/route/getById")
  public String getRouteById(@RequestParam("id") Integer routeId) {
    StringWriter response = new StringWriter();
    Route route;
    try {
      service.getService(RouteService.class).validateRouteExists(routeId);
      route = service.getService(RouteService.class).getById(routeId);
      JsonUtils.createRouteResponse(response, route);
    } catch (ServiceException se) {
      String message = "error getting route";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_ROUTE_ID, message);
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
