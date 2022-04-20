package com.YuShiqi.controller;

import com.YuShiqi.dao.UserDao;
import com.YuShiqi.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection conn = null;
    @Override
    public void init() throws ServletException {
        super.init();
        conn = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //forward to updateUser.jsp
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get all(6) request parameters
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        //create an object of User Model
        User user = new User();
        //set all 6 values into User Model
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        //这里的Date是java.sql.Date,继承于java.util.Date,可以进行强转
        user.setBirthdate(Date.valueOf(birthdate));
        //create an object of UserDao
        UserDao userDao = new UserDao();
        //call updateUser() in UserDao
        try {
            if (userDao.updateUser(conn,user) == 1){
                //kill the old session
                request.getSession(false).invalidate();//kill right now
                //create a new session
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("user",user);
                //forward to userInfo.jsp
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}