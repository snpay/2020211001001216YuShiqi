package com.YuShiqi.week6;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RedirectServlet", value = "/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirect to same server: relative url
        //1.start without "/":
        /*System.out.println("before redirect");
        response.sendRedirect("index.jsp");
        System.out.println("after redirect");*/
        //2.start with "/":
        //We should add webapp url:"/2020211001001216YuShiqi_war_exploded",otherwise it didn't work.
        //response.sendRedirect("/2020211001001216YuShiqi_war_exploded/index.jsp");

        //Redirect to other server: absolute url
        response.sendRedirect("https://www.baidu.com");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}