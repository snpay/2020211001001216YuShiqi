<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo 1-week 11</title>
</head>
<body>
<%
    //set attribute
    pageContext.setAttribute("attName","att in page");
    request.setAttribute("attName","att in request");
    session.setAttribute("attName","att in session");
    application.setAttribute("attName","att in application");
%>
<h2> get 4 scope variable - using java code</h2>
att value: <%=pageContext.getAttribute("attName",PageContext.PAGE_SCOPE)%><br>
att value: <%=pageContext.getAttribute("attName",PageContext.REQUEST_SCOPE)%><br>
att value: <%=pageContext.getAttribute("attName",PageContext.SESSION_SCOPE)%><br>
att value: <%=pageContext.getAttribute("attName",PageContext.APPLICATION_SCOPE)%><br>

find value: <%=pageContext.findAttribute("attName")%><!-- which one? -->
<h2> get 4 scope variable - using EL code</h2>
find value: ${attName}<br><!-- ==findAttribute("attName") which one?-->
get session value: ${sessionScope.attName}<br>
get request value: ${requestScope.attName}<br>
get page value: ${pageScope.attName}<br>
get application value: ${applicationScope.attName}<br>
</body>
</html>