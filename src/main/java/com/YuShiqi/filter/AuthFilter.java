package com.YuShiqi.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //request come here before go to the selvet doGet() or doPost()
        System.out.println("I am in AuthFilter before doFilter()-->"+System.currentTimeMillis());
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("getRequestURL-->"+httpRequest.getRequestURL());
        System.out.println("getScheme-->"+httpRequest.getScheme());//http
        System.out.println("getServerName-->"+httpRequest.getServerName());//tomcat
        System.out.println("getServerPort-->"+httpRequest.getServerPort());
        System.out.println("getRequestURI-->"+httpRequest.getRequestURI());
        System.out.println("getSelvetPath-->"+httpRequest.getServletPath());
        System.out.println("getQueryString-->"+httpRequest.getQueryString());
        System.out.println("getMethod-->"+httpRequest.getMethod());
        chain.doFilter(request, response);//call next filter - if no next filter - then go to the selvet
        //response come back after selvet
        System.out.println("I am in AuthFilter after doFilter()-->"+System.currentTimeMillis());
    }
}