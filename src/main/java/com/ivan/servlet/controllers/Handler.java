package com.ivan.servlet.controllers;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.services.Service;
import com.ivan.servlet.services.UserService;

public class Handler {

  protected Service service;

 public  Handler(Service service) {
    this.service = service;
  }

  public Integer getUserId(Integer userId) {
    try {
      service.getService(UserService.class).validateUserExists(userId);
    } catch (ServiceException e) {

    }
    return userId;
  }
}
