<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OPEN DEPOSIT</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.openDeposit{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>

<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content1">
<div class="content">
<h1>OPEN DEPOSIT</h1>
<form name="myForm" onsubmit="return validateForm([7])" action="openDepositServlet" >
ENTER THE AMOUNT :<input type="number" name="amount"><br>
SELECT THE TYPE:
<select name="depositType">
<option value="1">FIXED DEPOSIT</option>
<option value="2">RECURRING DEPOSIT</option>
</select><br>
SELECT THE DEPOSIT POLICY :
<select name="depositPolicy">
<option value="1">MONTHS[1]   INTERSET[0.01]</option>
<option value="2">MONTHS[2]   INTERSET[0.025]</option>
<option value="3">MONTHS[3]   INTERSET[0.04]</option>
<option value="4">MONTHS[6]   INTERSET[0.1]</option>
<option value="5">YEAR[1]   INTERSET[0.3]</option>
<option value="6">YEARS[5]   INTERSET[1.7]</option>
</select><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber") %>>
<input type="submit" value="OPEN DEPOSIT">
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}	
%>
</div>
</div>
</body>
</html>