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
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="issueDebitCardServlet" method="post">
ENTER THE CUSTOMER ID :<input type="text" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="number" name="accountNumber"><br>
ENTER THE M-Pin :<input type="number" name="mPin"><br>
<input type="submit" value="ISSUE DEBIT CARD"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>"+(String)session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>