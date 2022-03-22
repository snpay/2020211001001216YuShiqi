package com.YuShiqi.week4;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        name = "ConfigDemoServlet",
        urlPatterns = "/config",
        initParams = {
                @WebInitParam(name = "studentName",value = "YuShiqi"),
                @WebInitParam(name = "studentId",value = "2020211001001216")
        }
)
public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String studentName = config.getInitParameter("studentName");
        String studentId = config.getInitParameter("studentId");

        PrintWriter out = response.getWriter();
        out.println("studentName:"+studentName);
        out.println("studentId:"+studentId);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}