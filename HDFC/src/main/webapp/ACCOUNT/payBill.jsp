<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "bank.hdfc.pack.BankDefinition"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PAY BILL</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.payBill{
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
<h1>PAY BILL</h1>
<form name="myForm" onsubmit="return validateForm([7,8])" action="payBillServlet">
SELECT THE BILL TYPE :
<select name="billType">
<option >INSURENCE BILL</option>
<option >MOBILE RECHARGES</option>
<option >ELECTIC BILL</option>
<option >LPG/GAS BILLS</option>
<option >TAX BILLS</option>
<option >SUBCRIPTION</option>
<option >CABLE TV</option>
</select>
ENTER THE AMOUNT :<input type="number" name="amount"><br>

ENTER THE DESCRIPTION :<br>
<textarea name="description" rows="4" cols="50" placeholder="type here..."></textarea><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>>  
<input type="submit" value="PAY BILL">
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
	
}	
%>
</form>
</div>
</div>
</body>
</html>