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

<c:if test="${cookie.customerId.value != null}">
<jsp:forward page="customerVerify" > 
<jsp:param name="customerId" value="${cookie.customerId.value}"/>
<jsp:param name="password" value="${cookie.customerPassword.value}"/>
</jsp:forward>  
</c:if> 

<c:out value="${cookie.customerId.value}"/>
<c:out value="${cookie.customerPassword.value}"/> 
<form action="customerVerify" method="post">
ENTER CUSTOMER ID     : <input type="number" name="customerId"><br>
ENTER PASSWORD        :<input type="password" name="password"><br>
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