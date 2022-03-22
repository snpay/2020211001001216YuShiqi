package com.YuShiqi.week4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "JDBCDemoServlet", value = "/JDBCDemoServlet")
public class JDBCDemoServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        super.init();

        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false";
        String username="sa";
        String password="admin123456789";
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(url,username,password);
            System.out.println("connerction -->"+con);
        }   catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
        //关闭连接
        try{
            con.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
