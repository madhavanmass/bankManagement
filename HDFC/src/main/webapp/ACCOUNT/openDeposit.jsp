<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.openDeposit{
	background-color: red;
}
</style>
</head>

<body>

<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content">
<h1>OPEN DEPOSIT</h1>
<form action="openDepositServlet">
ENTER THE AMOUNT :<input type="number" name="amount"><br>
SELECT THE TYPE:
<select name="depositType">
<option value="1">FIXED DEPOSIT</option>
<option value="2">RECURRING DEPOSIT</option>
</select><br>
SELECT THE DEPOSIT POLICY :
<select name="depositPolicy">
<option value="1">ONE MONTH</option>
<option value="2">2 MONTH</option>
<option value="3">3 MONTH</option>
<option value="4">6 MONTH</option>
<option value="5">ONE YEAR</option>
<option value="6">5 YEAR</option>
</select><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber") %>>
<input type="submit" value="OPEN DEPOSIT">
</form>
<% if(session.getAttribute("message") !=null){
	boolean message=(boolean)session.getAttribute("message");
	session.removeAttribute("message");
	out.print(message?"A NEW DEPOSIT HAS BEEN OPEN FOR YOU GO CHECK IN THE DEPOSIT TAB":"IT SEEMS YOU HAVE IN SUFFICIENT BLANCE PLEASE TRY IN OTHER ACCOUNTS");
}	
%>
</div>
</body>
</html>