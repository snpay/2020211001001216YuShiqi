<%--
  Created by IntelliJ IDEA.
  User: ysq
  Date: 2022/4/6
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1 align="center">User List</h1>
<table width=540 border=1 align=center>
    <caption>Users</caption>
    <tr>
        <td>UserName</td>
        <td>PassWord</td>
        <td>Email</td>
        <td>Gender</td>
        <td>BirthDate</td>
    </tr>
    <tr>
        <td><%=request.getAttribute("username")%></td>
        <td><%=request.getAttribute("password")%></td>
        <td><%=request.getAttribute("email")%></td>
        <td><%=request.getAttribute("gender")%></td>
        <td><%=request.getAttribute("birthdate")%></td>
    </tr>
</table>
<%@include file="footer.jsp"%>