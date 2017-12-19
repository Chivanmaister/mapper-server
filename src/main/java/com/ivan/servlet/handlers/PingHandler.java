package com.ivan.servlet.handlers;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PingHandler implements Handler {

  private RestService restService;

  public PingHandler(RestService restService) {
    this.restService = restService;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
    String ping = request.getParameter("hello");
    JsonUtils.createPingJson(response, ping);
  }
}
