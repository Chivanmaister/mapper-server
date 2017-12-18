package com.ivan.servlet.handlers.route;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.InvalidNameException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddHandler implements Handler {

    private RouteService routeService;
    private UserService userService;

    public AddHandler(RestService restService) throws ServiceException {
        routeService = restService.getService(RouteService.class);
        userService = restService.getService(UserService.class);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Integer userId = getUserId(request);
        String name = getName(request);
        Route route = routeService.addRoute(name, userId);
        JsonUtils.createRouteJson(response, route);
    }

    private Integer getUserId(HttpServletRequest request) throws ServiceException {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        userService.validateUserExists(userId);
        return userId;
    }

    private String getName(HttpServletRequest request) throws ServiceException {
        String name = request.getParameter("name");
        if (name == null) {
            throw new InvalidNameException("Parameter name is invalid");
        }
        return name;
    }
}
