<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.concurrent.ConcurrentHashMap"
    import="bank.hdfc.pack.CustomerDetail"
    import="bank.hdfc.function.Employee"
    import="bank.hdfc.function.Branch"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VIEW CUSTOMERS</title>
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<%
	ConcurrentHashMap<Integer,CustomerDetail> customerDetails= new ConcurrentHashMap<>();
	Employee employee=((Employee)session.getAttribute("employee"));
	
	employee.getBranch().loadCustomers();
	customerDetails=Branch.getCustomerDetails();
%>
<table><tr><th>TRANSACTION ID</th><th>DATE</th><th>TIME</th><th>AMOUNT</th><th>REMAINING BALANCE</th></tr>

<c:forEach items="${customerDetails}" var="customerDetail">
<tr>
<td>${customerDetail.value. getCustomerId()}</td>

<tr>
</c:forEach>
</table>
<!-- for(Transaction transaction:transactions.values()){
	
	out.print("<tr>" 
			+ "<td>" + transaction.getTransactionId()+ "</td>" 
			+ "<td>"+transaction.getDate().toString()+ "</td>" 
			+ "<td>"+ transaction.getTime().toString()+ "</td>" 
			+ "<td>"+ transaction.getAmount()+ "</td>"
			+ "<td>"+ transaction.getRemainingBalance()+"</td>"
			+"</tr>"); -->
	
}

</body>
</html>