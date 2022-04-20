<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Update</h1>
<%
  User user2 = (User) session.getAttribute("user");
%>
<form method="post" action="updateUser">
  <table width="200px" align="center" border="0">
    <tr>
      <td height="50px">
        <font face="san-serif" size="3">
          New User Registration
        </font>
      </td>
    </tr>
    <tr>
      <td height="50px">
        id<br><input name="id" type="text" value="<%=user2.getId()%>" size="20" >
      </td>
    </tr>
    <tr>
      <td height="50px">
        username<input name="username" type="text" value="<%=user2.getUsername()%>" size="20" >
      </td>
    </tr>
    <tr>
      <td height="50px">
        password<input name="password" type="password" value="<%=user2.getPassword()%>" size="20" >
      </td>
    </tr>
    <tr>
      <td height="50px">
        email<input name="email" type="email" value="<%=user2.getEmail()%>" size="20" >
      </td>
    </tr>
    <tr>
      <td height="50px">
        <font face="san-serif" size="2"><b>Gender</b></font>
        <input name="gender" type="radio" size="10" value="Male" <%="Male".equals(user2.getGender())?"checked":""%>/>
        <font face="san-serif" size="2">Male</font>
        <input name="gender" type="radio" size="10" value="Female" <%="Female".equals(user2.getGender())?"checked":""%>/>
        <font face="san-serif" size="2">Female</font>
      </td>
    </tr>
    <tr>
      <td height="50px">
        birthdate<input name="birthdate" type="text" value="<%=user2.getBirthdate()%>" size="20" >
      </td>
    </tr>
    <tr>
      <td height="50px">
        <input type="submit" size="10" value="Save Changes">
      </td>
    </tr>
  </table>
</form>
<%@include file="footer.jsp"%>
