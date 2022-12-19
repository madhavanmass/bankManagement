<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="bank.hdfc.function.DebitCard"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEBIT CARD</title>
<script src="JAVASCRIPT/hideForm.js"></script>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.debitCard{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>

<div class="content">
<h1>DEBIT CARD DETAIL</h1>
<%

//prevent cache
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); 
response.setDateHeader("Expires", 0);


Customer customer=((Customer)session.getAttribute("customer"));
customer.loadCurrentAccounts();
customer.loadSavingAccount();
DebitCard debitCard=null;
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadDebitCard();
	debitCard=customer.getCurrentAccount().getDebitCard();
}
else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
	customer.getSavingAccounts().get(accountNumber).loadDebitCard();
	debitCard=customer.getSavingAccounts().get(accountNumber).getDebitCard();
}
if(debitCard!=null){
	out.print("<table>"+debitCard.toString()+"</table>");
}
%>
<c:if test="<%=debitCard!=null%>">
<h1>CHANGE M-PIN</h1>
<button onclick="show()">CHANGE PASSWORD</button>
<form name="myForm" onsubmit="return validateForm([5,6])" id="form" action="changeMPin" method="post" style="display:none;">
ENTER YOUR OLD MPIN:<input type="number" name="oldMPin" >
ENTER NEW MPIN:<input type="number" name="newMPin">
<input type="hidden" value=<%=request.getParameter("accountNumber")%> name="accountNumber">
<input type="submit" value="CONFIRM">
</form>
<% if(session.getAttribute("message") !=null){
	boolean result=((Boolean)session.getAttribute("message"));
	session.removeAttribute("message");
	if(result){
		out.print("<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE M-PIN HAS BEEN CHANGED SUCCESSFULLY</h2>");
	}
	else{
		out.print("<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\" >THE OLD M-PIN DOESNT MATCH</h2>");
	}
}	
%>
<h1>CHANGE CARD STATUS</h1>
<form onclick="setTimeout(function () { window.location.reload(); }, 10)" action="changeStatus">
<input type="hidden" value=<%=request.getParameter("accountNumber")%> name="accountNumber">
<button type="submit"><%=debitCard.getStatus()==1?"LOCK":"ACTIVATE" %></button>
</form>

</c:if>
<c:if test="<%=debitCard==null%>">
<h2>YOU DONT HAVE A DEBIT CARD</h2>
</c:if>
</div>
</body>

</html>