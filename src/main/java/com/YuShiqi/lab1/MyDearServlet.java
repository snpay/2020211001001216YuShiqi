package com.YuShiqi.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyDearServlet", value = "/my")
public class MyDearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String classname = request.getParameter("classname");
        String id = request.getParameter("id");
        String send = request.getParameter("send");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =  response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>MyDearJsp</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("name:"+name+"<br>");
        out.println("submit:"+send+"<br>");
        out.println("class:"+classname+"<br>");
        out.println("id:"+id+"<br>");
        out.println("</body>");
        out.println("</html>");
    }
}