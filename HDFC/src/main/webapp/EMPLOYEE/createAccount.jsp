<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CREATE ACCOUNT</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.createAccount{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
<h1>CREATE ACCOUNT</h1>
<form name="myForm" onsubmit="return validateForm([23,24])" action="createAccountServlet" method="post" >
ENTER THE CUSTOMER ID :<input type="number" name="customerId"><br>
CHOOSE AN ACCOUNT:
<select name="accountChoice">
<option value="1">SAVING ACCOUNT</option>
<option value="2">CURRENT ACCOUNT</option>
</select><br>
ENTER INITIAL DEPOSIT :<input type="number" name="initialDeposit"><br>
<input type="submit" value="CREATE ACCOUNT">
</form>
</body>
</html>