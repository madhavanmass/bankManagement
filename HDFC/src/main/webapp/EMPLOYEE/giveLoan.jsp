<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GIVE LOAN</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.giveLoan{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<div class="content1">
<h1>GIVE LOAN</h1>
<form name="myForm" onsubmit="return validateForm([23,1,7])" action="giveLoanServlet" method="post" >
ENTER THE CUSTOMER ID :<input type="number" name="customerId"><br>
SELECT THE LOAN TYPE :
<select name="loanType">
<option value="1">PERSONAL LOAN - INTEREST[0.1] YEARS[1]</option>
<option value="2">HOME LOAN - INTEREST[0.2] YEARS[2]</option>
<option value="3">EDUCATION LOAN - INTEREST[0.05] YEARS[4]</option>
<option value="4">GOLD LOAN - INTEREST[0.15] MONTHS[6]</option>
<option value="5">VEHICLE LOAN - INTEREST[0.12] MONTHS[6]</option>
</select>
ENTER THE AMOUNT :<input type="number" name="amount"><br>
ENTER THE ACCOUNT NUMBER YOU WANT TO PUT THE MONEY IN :<input type="number" name="accountNumber"><br>
<input type="submit" value="GIVE LOAN"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</div>
</body>
</html>