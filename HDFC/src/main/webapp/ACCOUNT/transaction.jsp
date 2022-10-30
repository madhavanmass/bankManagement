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
<title>TRANSACTION HISTORY</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<h1>TRANSACTION HISTORY</h1>
<%
HashMap<Integer,Transaction> transactions= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadTransactions(customer.getCurrentAccount().getBalance());
	transactions=customer.getCurrentAccount().getTransactions();
}
else{
	customer.getSavingAccounts().get(accountNumber).loadTransactions(customer.getSavingAccounts().get(accountNumber).getBalance());
	transactions= customer.getSavingAccounts().get(accountNumber).getTransactions();
}

out.print(
		"<table><tr><th>TRANSACTION ID</th><th>DATE</th><th>TIME</th><th>AMOUNT</th><th>ACTION</th><th>REMAINING BALANCE</th><th>TRANSFERRED ACCOUNT</th><th>DESCRIPTION</th></tr> ");
for(Transaction transaction:transactions.values()){
	
	out.print("<tr>" 
			+ "<td>" + transaction.getTransactionId()+ "</td>" 
			+ "<td>"+transaction.getDate().toString()+ "</td>" 
			+ "<td>"+ transaction.getTime().toString()+ "</td>" 
			+ "<td>"+ transaction.getAmount()+ "</td>"
			+ "<td>" + BankDefinition.actionType(transaction.getAction()) + "</td>"
			+ "<td>"+ transaction.getRemainingBalance()+"</td>"
			+ "<td>"+ transaction.getTransferAccountNumber()+ "</td>"
			+ "<td>"+ transaction.getDescription()+ "</td>"
			+"</tr>");
	
}
out.print("</table>");
%>
</body>
</html>