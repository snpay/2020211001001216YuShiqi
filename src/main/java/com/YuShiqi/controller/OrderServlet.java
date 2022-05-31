package com.YuShiqi.controller;

import com.YuShiqi.dao.OrderDao;
import com.YuShiqi.model.Item;
import com.YuShiqi.model.Order;
import com.YuShiqi.model.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> paymentList = Payment.findAllPayment(con);
        request.setAttribute("paymentTypeList", paymentList);
        request.getRequestDispatcher("WEB-INF/views/order.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = request.getParameter("cutomerId") != null ? Integer.parseInt(request.getParameter("cutomerId")) : 0;
        int paymentId = request.getParameter("paymentId") != null ? Integer.parseInt(request.getParameter("paymentId")) : 0;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String postalCode = request.getParameter("postalCode");
        String state = request.getParameter("state");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String notes = request.getParameter("notes");
        double orderTotal = request.getParameter("orderTotal") != null ? Double.parseDouble(request.getParameter("orderTotal")) : 0.0;

        String msg = null;
        if (customerId == 0 || paymentId == 0 || firstName == null || firstName.trim().length() == 0 || phone == null ||
                phone.trim().length() == 0 || address1 == null || address1.trim().length() == 0 || postalCode == null || postalCode.trim().length() == 0){
            msg = "Error ! , Enter Required(*) Info.";
            request.setAttribute("message", msg);
            List<Payment> paymentList = Payment.findAllPayment(con);
            request.setAttribute("paymentTypeList", paymentList);
            System.out.println("缺少信息");
            request.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(request,response);
        }
        Order o = new Order();
        o.setCustomerId(customerId);
        o.setPaymentId(paymentId);
        o.setFirstName(firstName);
        o.setLastName(lastName);
        o.setPhone(phone);
        o.setAddress1(address1);
        o.setAddress2(address2);
        o.setPostalCode(postalCode);
        o.setState(state);
        o.setCity(city);
        o.setCountry(country);
        o.setNotes(notes);
        o.setOrderTotal(orderTotal);
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("cart") != null) {
            ArrayList<Item> cartItems = (ArrayList<Item>) session.getAttribute("cart");
            o.setOrderDetails(new HashSet<Item>(cartItems));
        }
        OrderDao orderDao = new OrderDao();
        int n = 0;
        try {
            n = orderDao.save(con, o);
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println(n);
        if(n > 0) {
            request.getRequestDispatcher("/WEB-INF/views/orderSuccess.jsp").forward(request,response);
        }
    }
}
