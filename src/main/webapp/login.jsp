<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>
    Login
</h1>
<form method="post" action="login">
    Username:<input name="username" type="text" size="20"><br>
    Password:<input name="password" type="password" size="20"><br>
    <input name="login" type="submit" size="10" value="Login">
</form>
<%@include file="footer.jsp"%>
