package com.YuShiqi.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LifeCycleServlet", value = "/life")
public class LifeCycleServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //System.out.println("I am in init");
        System.out.println("I am from default constructor");
        ServletContext context = config.getServletContext();
        int count = 0;
        context.setAttribute("count",count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("I am in service");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =  response.getWriter();
        int count1 = (Integer) request.getServletContext().getAttribute("count");
        count1++;
        out.println("Since loading this servlet has been accessed "+count1+" times");
        request.getServletContext().setAttribute("count",count1);

    }