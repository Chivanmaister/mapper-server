package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.repositories.CoordinateDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class BaseCoordinateDao implements CoordinateDao {

    private MysqlDataSource dataSource;

    public BaseCoordinateDao(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

}
