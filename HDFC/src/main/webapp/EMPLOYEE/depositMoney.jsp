<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEPOSIT MONEY</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.depositMoney{
	background-color: red;
}
</style>
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="depositMoneyServlet" method="post">
ENTER THE CUSTOMER ID :<input type="text" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="text" name="accountNumber"><br>
ENTER INITIAL DEPOSIT :<input type="number" name="amount"><br>
<input type="submit" value="DEPOSIT MONEY"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>"+(String)session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>