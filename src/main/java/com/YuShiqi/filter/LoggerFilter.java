package com.YuShiqi.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "LoggerFilter")
public class LoggerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //request come here before go to the selvet doGet() or doPost()
        System.out.println("I am in LoggerFilter-->doFilter() before selvet - (request come here)");
        chain.doFilter(request, response);//call next filter - if no next filter - then go to the selvet
        //response come back after selvet
        System.out.println("I am in LoggerFilter-->doFilter() after selvet - (response come here)");
    }
}