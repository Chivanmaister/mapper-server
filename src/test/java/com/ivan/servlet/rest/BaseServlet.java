package com.ivan.servlet.rest;

import org.junit.Before;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class BaseServlet {

    protected HttpServlet httpServlet;
    protected HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;

    @Before
    public void init() {
        servletResponse = mock(HttpServletResponse.class);
        servletRequest = mock(HttpServletRequest.class);
        httpServlet = mock(HttpServlet.class);
    }
}
