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
</head>
<body>
 <jsp:include page="/COMMON/topnav.html"></jsp:include>
 <jsp:include page="/COMMON/loansidebar.jsp"></jsp:include>

 <h1>LOAN ACCOUNT</h1>
 
 <%
 Customer customer= ((Customer)session.getAttribute("customer"));
 out.print(customer.getLoanAccounts().get(Integer.valueOf(request.getParameter("accountNumber"))).toString());
 %>
</body>
</html>