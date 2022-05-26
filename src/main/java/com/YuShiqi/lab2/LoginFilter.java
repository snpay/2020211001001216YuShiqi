package com.YuShiqi.lab2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/lab2/validate.jsp","/lab2/welcome.jsp"})//"/lab2/validate.jsp"
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("I am in LoginFilter-->init()");
    }

    public void destroy() {
        System.out.println("I am in LoginFilter-->destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("I am in LoginFilter-->doFilter() request before chain");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        if (session!=null){//&& session.getAttribute("login")!=null
            chain.doFilter(request, response);
            //httpServletRequest.getRequestDispatcher("/lab2/welcome.jsp").forward(httpServletRequest,httpServletResponse);
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/lab2/login.jsp");
        }
        System.out.println("I am in LoginFilter-->doFilter() request after chain");
    }
}
