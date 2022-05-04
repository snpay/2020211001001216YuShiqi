package com.YuShiqi.controller;

import com.YuShiqi.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = {"/admin/home", "/admin/login"})
public class AdminHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//return a session (if exist) or null but its no create a new session
        if(session!=null && session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getUsername())){
                request.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(request,response);
            }else {
                //have session but not admin user
                session.invalidate();//kill session right now
                request.setAttribute("message","Unauthorized Access Admin Module!!!");
                request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request,response);
            }
        }else {
            //no session - then user click the link
            request.setAttribute("message","Please login as admin!!!");
            request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}