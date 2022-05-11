package com.YuShiqi.controller;


import com.YuShiqi.dao.ProductDao;
import com.YuShiqi.model.Category;
import com.YuShiqi.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215) //upload file up to 16MB
public class AddProductServlet extends HttpServlet {

    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList = category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get all parameters
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price") != null ? Double.parseDouble(request.getParameter("price")) : 0.0;
        int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 0;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;//input stream of the upload file
        Part fileParts = request.getPart("picture");//obtains the upload file part in the multipart request
        if (fileParts != null){
            inputStream = fileParts.getInputStream();
        }
        //set int model
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        //call same in dao
        ProductDao productDao = new ProductDao();
        int n = 0;
        try {
            n = productDao.save(product,con);
            if (n>0){
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //forward
    }
}
