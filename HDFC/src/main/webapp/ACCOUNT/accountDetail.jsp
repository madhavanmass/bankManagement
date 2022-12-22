<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ACCOUNT DETAILS</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.accountDetail{
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
<h1>ACCOUNT DETAILS</h1>
<%
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
Customer customer=((Customer) session.getAttribute("customer"));
customer.loadCurrentAccounts();
customer.loadSavingAccount();
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	out.print("<table>"+customer.getCurrentAccount().toString()+"</table>");
}
else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
	out.print("<table>"+customer.getSavingAccounts().get(accountNumber).toString()+"</table>");	
}
%>

<c:if test="<%=customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber%>">
<h2>SET A SPENT LIMIT</h2>
<form action="userSetLimit?accountNumber=<%=request.getParameter("accountNumber")%>" oninput="limit.value=parseInt(userSetLimit.value)" >
<input type="range" min="1000" max="100000"  name="userSetLimit">
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>> 
<button type="submit" >UPDATE LIMIT TO  <output name="limit"><%=customer.getCurrentAccount().getUserSetLimit()%></output></button>

</form>
</c:if>
</div>
</div>
</body>
</html>