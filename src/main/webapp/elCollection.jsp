<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>Demo 3 - week 11</title>
</head>
<body>
<%
    //array
    String[] firstName = {"Bill","Scott","Larry"};
    //list
    ArrayList<String> lastName = new ArrayList<String>();
    lastName.add("Ellison");
    lastName.add("Gates");
    lastName.add("McNealy");
    //map
    HashMap<String,String> companyName = new HashMap<String, String>();
    companyName.put("Ellison","Son");
    companyName.put("Gates","Oracle");
    companyName.put("McNealy","Microsoft");
    //set attribute in any one scope
    request.setAttribute("fName",firstName);
    request.setAttribute("lName",lastName);
    request.setAttribute("company",companyName);
%>
<h2> get date from Array --> List --> Map - using EL code</h2>
<ul>
    <li>${fName[0]} ${lName[0]}-->${company["Ellison"]}</li>
    <li>${fName[1]} ${lName[1]}-->${company[lName[1]]}</li>
    <li>${fName[2]} ${lName[2]}-->${company[lName[2]]}</li>
</ul>
</body>
</html>
