<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@page import="com.YuShiqi.model.User" %>
<h1 align="center">User Info</h1>
<%--
<%
    //read cookie
    Cookie [] allCookies = request.getCookies();//all cookies
    for (Cookie c : allCookies){
        //get one by one
        out.println("<br/>"+c.getName()+"---"+c.getValue());
    }
%>
--%>

<%
    User user1 = (User) session.getAttribute("user");
%>
<table width=600 border=1 align=center>
    <tr>
        <td>Id</td>
        <td>UserName</td>
        <td>PassWord</td>
        <td>Email</td>
        <td>Gender</td>
        <td>BirthDate</td>
    </tr>
    <tr>
        <td><%=user1.getId()%></td>
        <td><%=user1.getUsername()%></td>
        <td><%=user1.getPassword()%></td>
        <td><%=user1.getEmail()%></td>
        <td><%=user1.getGender()%></td>
        <td><%=user1.getBirthdate()%></td>
    </tr>
</table>
<center>
    <a href="updateUser" >Update</a>
</center>
<%@include file="footer.jsp"%>