package com.YuShiqi.week5;

import com.YuShiqi.dao.UserDao;
import com.YuShiqi.model.User;

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
        //when click "Login" the method is get()
        //forward to login.jsp
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //write mvc code
        //use model and dao
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findByUsernamePassword(conn,username,password);
            if (user != null){
                //valid
                /*//week8 code:demo#1 use cookie for session
                //create a cookie
                //step1:create an object of cookie class
                Cookie cookie = new Cookie("sessionId",""+user.getId());//sessionId = userId
                //step2:set age of cookie
                cookie.setMaxAge(10*60);//10 minutes
                //step3:add cookie into response
                response.addCookie(cookie);*/

                //week 8 exercise#2:add code for rememberMe
                String rememberMe = request.getParameter("rememberMe");
                if (rememberMe != null && rememberMe.equals("1")){
                    //want to rememberMe
                    //create 3 cookies
                    Cookie usernameCookie = new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe",rememberMe);
                    //set age of cookies
                    usernameCookie.setMaxAge(5);//5 second
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    //add cookies into response
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }

                //week 8 exercise#1:create a session
                HttpSession session = request.getSession();//create a new session if not exist otherwise return existing session
                /*//check session id
                System.out.println("session id-->"+session.getId());*/
                //set time for session
                session.setMaxInactiveInterval(10*60);//for 10 minutes if request not come,tomcat kill session
                //change request(one page) to session - so we can get session attribute in many jsp page
                session.setAttribute("user",user);//set user info into session
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else{
                //invalid
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //move jdbc code to dao
       /* response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String sql = "SELECT * FROM Users WHERE username='"+username+"' AND password='"+password+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                //out.println("Login Success!!!<br>");
                //out.println("Welcome,"+username);
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
            //while (rs.next()){
                //String username1 = rs.getString("username");
                //String password1 = rs.getString("password");
                //if(username.equals(username1) && password.equals(password1)){
                    //out.println("Login Success!!!<br>");
                    //out.println("Welcome,"+username);
                    //break;
                //}else {
                    //out.println("Username or Password Error!!!");
                //}
            //}
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

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
