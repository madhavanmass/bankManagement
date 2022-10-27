<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
       import="bank.hdfc.function.Customer"
    import="bank.hdfc.function.Transaction"
    import="java.util.HashMap"
    import="bank.hdfc.pack.BankDefinition"
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
<%
HashMap<Integer,Transaction> transactions= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
customer.getLoanAccounts().get(accountNumber).loadTransactions(customer.getLoanAccounts().get(accountNumber).getBalance());
transactions=customer.getLoanAccounts().get(accountNumber).getTransactions();
out.print(
		"<table><tr><th>TRANSACTION ID</th><th>DATE</th><th>TIME</th><th>AMOUNT</th><th>REMAINING BALANCE</th></tr> ");
for(Transaction transaction:transactions.values()){
	
	out.print("<tr>" 
			+ "<td>" + transaction.getTransactionId()+ "</td>" 
			+ "<td>"+transaction.getDate().toString()+ "</td>" 
			+ "<td>"+ transaction.getTime().toString()+ "</td>" 
			+ "<td>"+ transaction.getAmount()+ "</td>"
			+ "<td>"+ transaction.getRemainingBalance()+"</td>"
			+"</tr>");
	
}
out.print("</table>");

%>
</body>
</html>