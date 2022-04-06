package com.YuShiqi.week5;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "LoginServlet",
        value = "/login"
)
public class LoginServlet extends HttpServlet {
    Connection conn = null;

    @Override
    public void init() throws ServletException {
        super.init();
        /*ServletContext context = getServletContext();
        String dirver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        try {
            Class.forName(dirver);
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection --> "+conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        conn = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String sql = "SELECT * FROM Users WHERE username='"+username+"' AND password='"+password+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                /*out.println("Login Success!!!<br>");
                out.println("Welcome,"+username);*/
                //get form rs and set into request attribute
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthdate",rs.getString("birthdate"));
                //forward to userInfo.jsp
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else {
                //out.println("Username or Password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            //能用查询语句解决的就不需要用java代码解决
           /* while (rs.next()){
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                if(username.equals(username1) && password.equals(password1)){
                    out.println("Login Success!!!<br>");
                    out.println("Welcome,"+username);
                    break;
                }else {
                    out.println("Username or Password Error!!!");
                }
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}