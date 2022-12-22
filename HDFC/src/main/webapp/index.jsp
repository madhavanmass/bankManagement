<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib uri="WEB-INF/tags.tld" prefix="m" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<h1 style="text-align: center;">WELCOME TO HDFC</h1>
<h2 style="text-align: center;">CUSTOMER LOGIN</h2>

<form name="myForm" onsubmit="return validateForm([23,27])" action="customerVerify" method="post">
ENTER CUSTOMER ID     :<input type="number" name="customerId" ><br>
ENTER PASSWORD        :<input id="password" type="password" name="password" ><br>

<input type="submit" value="LOGIN">
</form>
<% if(session.getAttribute("message")!=null)
{
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>

</body>
</html>