package com.ivan.servlet.handlers.history;

import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.HistoryService;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetHandler implements Handler {

  private RestService restService;

  public GetHandler(RestService restService) {
    this.restService = restService;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
    Integer userId = getUser(request);
    Long dateFrom = getDateFrom(request);
    Long dateTo = getDateTo(request);
    String name = getName(request);

    List<History> histories = restService.getService(HistoryService.class).getHistoryList(userId, name, dateFrom, dateTo);
//    JsonUtils.createHistoryResponse(response, histories);
  }

  private String getName(HttpServletRequest request) {
    String name = request.getParameter("name");
    return name;
  }

  private Long getDateTo(HttpServletRequest request) {
    String dateToString = request.getParameter("dateTo");
    if (dateToString == null || dateToString.equalsIgnoreCase("")) {
      return null;
    }
    return Long.valueOf(dateToString);
  }

  private Long getDateFrom(HttpServletRequest request) {
    String dateFromString = request.getParameter("dateFrom");
    if (dateFromString == null || dateFromString.equalsIgnoreCase("")) {
      return null;
    }
    return Long.valueOf(dateFromString);
  }

  private Integer getUser(HttpServletRequest request) throws ServiceException {
    Integer userId = Integer.valueOf(request.getParameter("userId"));
    restService.getService(UserService.class).validateUserExists(userId);
    return userId;
  }


}
