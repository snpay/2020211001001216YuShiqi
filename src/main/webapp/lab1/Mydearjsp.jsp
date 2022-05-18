<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Using java code to get value:</h2>
name:<%=request.getParameter("name")%><br>
submit:<%=request.getParameter("send")%><br>
class:<%=request.getParameter("classname")%><br>
id:<%=request.getParameter("id")%><br>

<h2>Using EL flag to get value:</h2>
name:${param.name}<br>
submit:${param.send}<br>
class:${param.classname}<br>
id:${param.id}<br>
</body>
</html>