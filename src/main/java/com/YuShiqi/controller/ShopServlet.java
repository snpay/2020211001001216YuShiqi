package com.YuShiqi.controller;

import com.YuShiqi.dao.ProductDao;
import com.YuShiqi.model.Category;
import com.YuShiqi.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {

    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set all category into request
        List<Category> categoryList = Category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);
        //set all product into request
        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        try {
            if (request.getParameter("id")==null){
                //show all product
                productList = productDao.findAll(con);
            }else {
                //show one type of product
                int catId = Integer.parseInt(request.getParameter("id"));
                productList = productDao.findByCategoryId(catId,con);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        request.setAttribute("productList",productList);
        //forward
        String path = "/WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
