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
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<form name="myForm" onsubmit="return validateForm([23,7,1])" action="depositMoneyServlet" method="post" >
ENTER THE CUSTOMER ID :<input type="number" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="number" name="accountNumber"><br>
ENTER THE DEPOSIT AMOUNT :<input type="number" name="amount"><br>
<input type="submit" value="DEPOSIT MONEY"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</body>
</html>