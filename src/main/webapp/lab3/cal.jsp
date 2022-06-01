<%--
  Created by IntelliJ IDEA.
  User: ysq
  Date: 2022/6/1
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab 3</title>
</head>
<body>
<form method="post" action="cal">
    FirstVal:&nbsp;&nbsp;&nbsp;&nbsp;:<input name="firstval" type="text" value="${cookie.firstval.value}">
    Enter a Name :<input name="name" type="text" value="${cookie.name.value}"> <br/>
    SecondVal:<input name="secondval" type="text" value="${cookie.secondval.value}">
    &nbsp;Length &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
    <input name="length" type="text" value="${cookie.length.value}"> <br/>
    Result&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <input name="result" type="text" value="${cookie.result.value}"> <br/>
    <input name="add" type="submit" value="add">
    <input name="sub" type="submit" value="sub">
    <input name="mul" type="submit" value="mul">
    <input name="div" type="submit" value="div">
    <input name="length" type="submit" value="computer length">
    <input name="reset"  type="reset" value="reset">
</form>
</body>
</html>
