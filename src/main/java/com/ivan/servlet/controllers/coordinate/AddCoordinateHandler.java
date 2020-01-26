package com.ivan.servlet.controllers.coordinate;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class AddCoordinateHandler {

  @Autowired
  Service service;

  @PostMapping("/coordinate/add")
  public String addRoute(@RequestParam("routeId") Integer routeId, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
    StringWriter response = new StringWriter();
    Coordinate coordinate;
    try {
      coordinate = service.getService(CoordinateService.class).addCoordinate(latitude, longitude, routeId);
      service.getService(RouteService.class).validateRouteExists(routeId);
      JsonUtils.createCoordinateResponse(response, coordinate);
    } catch (ServiceException e) {
      String message = "error adding route";
      response = new StringWriter();
      try {
        JsonUtils.createErrorResponse(response, ErrorCodes.INVALID_ROUTE_NAME, message);
      } catch (IOException ignored) {
      }
    } catch (IOException e) {
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
