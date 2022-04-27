package com.YuShiqi.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//two ways of mapping filter to selvet
//way 1 -- use web.xml
//way 2 -- use @WebFilter
@WebFilter(filterName = "HelloFilter",urlPatterns = {"/hello-selvet"})
//test 1:url /hello-selvet is only for one selvet-HelloSelvet
//test 2:url /* is for all selvet
//test 3 url /home,/login,/register.jsp is for these 3 selvet or jsp page
public class HelloFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("I am in HelloFilter-->init()");//when start tomcat
    }

    public void destroy() {
        System.out.println("I am in HelloFilter-->destroy()");//when stop tomcat
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //request come here before go to the selvet doGet() or doPost()
        System.out.println("I am in HelloFilter-->doFilter() before selvet - (request come here)");
        chain.doFilter(request, response);//call next filter - if no next filter - then go to the selvet
        //response come back after selvet
        System.out.println("I am in HelloFilter-->doFilter() after selvet - (response come here)");
    }
}