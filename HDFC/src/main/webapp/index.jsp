<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
</head>
<body>
<h1>WELCOME TO HDFC</h1>
<h2>CUSTOMER LOGIN</h2>

<form action="customerVerify" method="post">
ENTER CUSTOMER ID     : <input type="number" name="customerId" value="7"><br>
ENTER PASSWORD        :<input type="password" name="password" value="gokul000"><br>
<% if(session.getAttribute("message") instanceof String)
{
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
<input type="submit" value="LOGIN">
</form>

</body>
</html>