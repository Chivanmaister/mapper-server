package com.ivan.servlet.services;

import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.List;

public interface HistoryService {

  List<History> getHistoryList(Integer userId, String name, Long dateFrom, Long dateTo) throws ServiceException;
}
