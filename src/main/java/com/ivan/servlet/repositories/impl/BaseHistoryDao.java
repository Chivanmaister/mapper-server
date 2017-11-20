package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.HistoryDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class BaseHistoryDao implements HistoryDao {

    private MysqlDataSource dataSource;

    public BaseHistoryDao(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public History getHistory() throws ServiceException {
        return null;
    }
}
