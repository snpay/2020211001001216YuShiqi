<%--
  Created by IntelliJ IDEA.
  User: ysq
  Date: 2022/4/6
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@include file="header.jsp"%>
<h1 align="center">User List</h1>
<table width=540 border=1 align=center>
    <caption>Users</caption>
    <tr>
        <td>ID</td>
        <td>UserName</td>
        <td>PassWord</td>
        <td>Email</td>
        <td>Gender</td>
        <td>BirthDate</td>
    </tr>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("rsname");
        while (rs.next()){
            int id1 = rs.getInt("id");
            String username1 = rs.getString("username");
            String password1 =rs.getString("password");
            String email1 = rs.getString("email");
            String gender1 = rs.getString("gender");
            String birthdate1 = rs.getString("birthdate");
            out.println("           <tr>");
            out.println(               "<td>"+id1+"</td>");
            out.println(               "<td>"+username1+"</td>");
            out.println(               "<td>"+password1+"</td>");
            out.println(               "<td>"+email1+"</td>");
            out.println(               "<td>"+gender1+"</td>");
            out.println(               "<td>"+birthdate1+"</td>");
            out.println("           </tr>");
        }
    %>
</table>
<%@include file="footer.jsp"%>