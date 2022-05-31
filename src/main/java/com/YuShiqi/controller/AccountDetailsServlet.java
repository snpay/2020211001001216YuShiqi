package com.YuShiqi.controller;

import com.YuShiqi.dao.OrderDao;
import com.YuShiqi.dao.UserDao;
import com.YuShiqi.model.Order;
import com.YuShiqi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accountDetails")
public class AccountDetailsServlet extends HttpServlet {
    private Connection con = null;
    public void init() throws SecurityException{
        con =(Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }//end of post

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session!=null && session.getAttribute("user")!=null) {
            User user = (User) session.getAttribute("user");
            int id = user.getId();
            UserDao dao = new UserDao();
            try {
                user = dao.findById(con, id);
                request.setAttribute("user", user);
                OrderDao orderDao = new OrderDao();
                List<Order> orderList = orderDao.findByUserId(con, id);
                request.setAttribute("orderList", orderList);
                request.getRequestDispatcher("WEB-INF/views/accountDetails.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("no account ,redirect to login page");
            response.sendRedirect("login");

        }
    }//end doGet



}//end