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
<script src="JAVASCRIPT/formatCheck.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<div class="content1">
<h1>ISSUE DEBIT CARD</h1>

<form name="myForm" onsubmit="return formatCheck({25:5,23:7,1:7})" action="issueDebitCardServlet" method="post">
ENTER THE CUSTOMER ID :<input type="number" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="number" name="accountNumber"><br>
ENTER THE M-Pin :<input type="number" name="mPin"><br>
<p style="font-size: 12px;" >* THE MPIN MUST ONLY HAVE 4 DIGIT</p>
<input type="submit" value="ISSUE DEBIT CARD"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</div>
</body>
</html>