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
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.transaction{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>

<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content">
<h1>TRANSACTION HISTORY</h1>
<%
HashMap<Integer,Transaction> transactions= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.setCurrentAccount(null);
	customer.loadCurrentAccounts();
	customer.getCurrentAccount().loadTransactions(customer.getCurrentAccount().getBalance());
	transactions=customer.getCurrentAccount().getTransactions();
}
else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
	customer.getSavingAccounts().get(accountNumber).loadTransactions(customer.getSavingAccounts().get(accountNumber).getBalance());
	transactions= customer.getSavingAccounts().get(accountNumber).getTransactions();
}

out.print(
		"<table><tr><th>TRANSACTION ID</th><th>DATE</th><th>TIME</th><th>AMOUNT</th><th>ACTION</th><th>REMAINING BALANCE</th><th>TRANSFERRED ACCOUNT</th><th>DESCRIPTION</th></tr> ");
for(Transaction transaction:transactions.values()){
	String otherAccount;
	if(transaction.getTransferAccountNumber()==0){
		otherAccount="[bank]";
	}
	else if(transaction.getTransferAccountNumber()==1){
		otherAccount="[bill]";
	}
	else{
		otherAccount=String.valueOf(transaction.getTransferAccountNumber());
	}
	out.print("<tr>" 
			+ "<td>" + transaction.getTransactionId()+ "</td>" 
			+ "<td>"+transaction.getDate().toString()+ "</td>" 
			+ "<td>"+ transaction.getTime().toString()+ "</td>" 
			+ "<td>"+ transaction.getAmount()+ "</td>"
			+ "<td>" + BankDefinition.actionType(transaction.getAction()) + "</td>"
			+ "<td>"+ transaction.getRemainingBalance()+"</td>"
			+ "<td>"+ otherAccount+ "</td>"
			+ "<td>"+ transaction.getDescription()+ "</td>"
			+"</tr>");
	
}
out.print("</table>");
%>
</div>
</body>
</html>