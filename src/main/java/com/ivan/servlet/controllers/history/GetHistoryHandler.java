package com.ivan.servlet.controllers.history;

import com.fasterxml.jackson.core.JsonGenerator;
import com.ivan.servlet.controllers.Handler;
import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.HistoryService;
import com.ivan.servlet.services.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
public class GetHistoryHandler extends Handler {

  private Service service;

  public GetHistoryHandler(Service service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/history/hmm/get")
  public String getHistory(@RequestParam("userId") Integer userId, @RequestParam(name = "dateFrom", required = false) Long dateFrom, @RequestParam(name = "dateTo", required = false) Long dateTo, @RequestParam(name = "name", required = false) String name) throws ServiceException, IOException {
    Integer validUserId = getUserId(userId);
    List<History> historyList = service.getService(HistoryService.class).getHistoryList(validUserId, name, dateFrom, dateTo);
    StringWriter stringWriter = new StringWriter();
    JsonUtils.createHistoryResponse(stringWriter, historyList);
    return stringWriter.toString();
  }
}
