<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ISSUE DEBIT CARD</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.issueDebitCard{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<h1>IUSSE DEBIT CARD</h1>
<form name="myForm" onsubmit="return validateForm([23,1,25])" action="issueDebitCardServlet" method="post">
ENTER THE CUSTOMER ID :<input type="number" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="number" name="accountNumber"><br>
ENTER THE M-Pin :<input type="number" name="mPin"><br>
<input type="submit" value="ISSUE DEBIT CARD"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</body>
</html>