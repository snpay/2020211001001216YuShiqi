package com.YuShiqi.week6;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//tell tomcat this class is a listener:@WebListener
@WebListener
public class JDBCServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //like main method for this webapp
        //called when tomcat start
        //we can use this method to get jdbc connection when tomcat start
        ServletContextListener.super.contextInitialized(sce);
        ServletContext context = sce.getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection --> "+conn);
            //set connection as a context attribute for all jsp and servlet
            context.setAttribute("conn",conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //end point of webapp
        //called when tomcat stop
        ServletContextListener.super.contextDestroyed(sce);
        //remove connection from context
        sce.getServletContext().removeAttribute("conn");
    }
}