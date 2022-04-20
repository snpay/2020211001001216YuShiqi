<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>
    Login
</h1>
<%
    //read cookies
    Cookie [] allCookies = request.getCookies();//get all cookies
    String username = "",password = "",rememberMeValue = "";
    if (allCookies != null){
        //read 3 cookies
        for (Cookie c : allCookies){
            //get one by one
            if (c.getName().equals("cUsername")){
                //get value of this cookie
                username = c.getValue();
            }
            if (c.getName().equals("cPassword")){
                //get value of this cookie
                password = c.getValue();
            }
            if (c.getName().equals("cRememberMe")){
                //get value of this cookie
                rememberMeValue = c.getValue();
            }
        }
    }
%>
<form method="post" action="login">
    Username:<input name="username" type="text" value="<%=username%>" size="20"><br>
    Password:<input name="password" type="password" value="<%=password%>" size="20"><br>
    <input type="checkbox" name="rememberMe" value="1" <%=rememberMeValue.equals("1") ? "checked" : ""%> checked/>RememberMe<br>
    <input name="login" type="submit" size="10" value="Login">
</form>
<%
    if (!(request.getAttribute("message") == null)){
        //error
        out.println(request.getAttribute("message"));
    }
%>
<%@include file="footer.jsp"%>