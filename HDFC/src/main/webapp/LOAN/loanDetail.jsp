<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.LoanAccount"
    import="bank.hdfc.function.Customer"
    import="java.util.HashMap"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.loan, li a.loanDetail{
	background-color: red;
}
</style>
</head>

<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/loanSidebar.jsp"></jsp:include>
<div class="content1">
<div class="content">
 <h1>LOAN ACCOUNT</h1>
 
 <%
 Customer customer= ((Customer)session.getAttribute("customer"));
 out.print("<table>"+customer.getLoanAccounts().get(Integer.valueOf(request.getParameter("accountNumber"))).toString()+"</table>");
 %>
</div>
</div>
</body>
</html>