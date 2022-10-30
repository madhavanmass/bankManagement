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
</head>

<body>

<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content">
<%
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
Customer customer=((Customer) session.getAttribute("customer"));
customer.loadCurrentAccounts();
customer.loadSavingAccount();
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	out.print(customer.getCurrentAccount().toString());
}
else{
	out.print(customer.getSavingAccounts().get(accountNumber).toString());	
}
%>

<c:if test="<%=customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber%>">
<h4>SET A SPENT LIMIT</h4>
<form action="userSetLimit?accountNumber=<%=request.getParameter("accountNumber")%>" oninput="limit.value=parseInt(userSetLimit.value)">
1000 <input type="range" min="1000" max="100000" value=<%=customer.getCurrentAccount().getUserSetLimit()%> name="userSetLimit"> 100000<br>
<output name="limit"></output><br>
<input type="submit" value="UPDATE LIMIT">
</form>
</c:if>
</div>
</body>
</html>