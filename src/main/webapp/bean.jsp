<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.YuShiqi.week10.StringBean" %>
<html>
<head>
    <title>Demo#2 week 10</title>
</head>
<body>
<h2>
    Use java code to access StringBean class
</h2>
<%
    //StringBean bean = new StringBean();
    //bean.setMessage("Hello Mr Bean - from java code");
%>
<%--Message(using java code): <%=bean.getMessage()%>--%>
<h2>
    Use usebean to access StringBean class
</h2>
<jsp:useBean id="bean" class="com.YuShiqi.week10.StringBean"/>
<jsp:setProperty name="bean" property="message" value='<%=request.getParameter("message")%>'/>
Message(using java code): <%=bean.getMessage()%><br>
Message(using setProperty): <jsp:getProperty name="bean" property="message"/><br>
</body>
</html>
