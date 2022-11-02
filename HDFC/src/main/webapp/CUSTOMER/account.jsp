<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bank.hdfc.function.Customer"
	import="bank.hdfc.function.SavingAccount" import="java.util.HashMap"
	import="bank.hdfc.pack.BankDefinition"
	import="bank.hdfc.function.CurrentAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>accounts</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.accounts{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
   <jsp:include page="/COMMON/topNav.html"></jsp:include>
	<h1>SAVING ACCOUNT</h1>
	<%
	((Customer) session.getAttribute("customer")).loadSavingAccount();
	HashMap<Integer, SavingAccount> savingAccounts = ((Customer) session.getAttribute("customer")).getSavingAccounts();
	if (savingAccounts.size()!=0) {
		out.print(
		"<table><tr><th>ACCOUNT  NUMBER</th><th>ACCOUNT TYPE</th><th>ACCOUNT BALACE</th><th>TRANSFERRED AMOUNT</th><th>ACTION</th></tr> ");
		for (SavingAccount savingAccount : savingAccounts.values()) {
			out.print("<tr>" 
			+ "<td>" + savingAccount.getAccountNumber()+ "</td>" 
			+ "<td>"+ BankDefinition.accountName(savingAccount.getAccountType())+ "</td>" 
			+ "<td>"+ savingAccount.getBalance()+ "</td>" 
			+ "<td>" + savingAccount.getTransferredAmount()+ "</td>"
			+ "<td><a href=accountDetail?accountNumber="+savingAccount.getAccountNumber()+">USE</a></td>"
			+"</tr>");
		}
		out.print("</table>");
	} 
	else{
		out.print("<h2>IT SEEMS YOU DONT HAVE A SAVING ACCOUNT</h2>");
	}
	
	%>

	<h1>CURRENT ACCOUNT</h1>
	<%
	((Customer) session.getAttribute("customer")).loadCurrentAccounts();
	CurrentAccount currentAccount = ((Customer) session.getAttribute("customer")).getCurrentAccount();
	if (currentAccount!=null) {

		out.print(
		"<table><tr><th>ACCOUNT  NUMBER</th><th>ACCOUNT TYPE</th><th>ACCOUNT BALACE</th><th>TRANSFERRED AMOUNT</th><th>ACTION</th></tr> ");
		out.print("<tr>" + "<td>" + currentAccount.getAccountNumber() + "</td>" + "<td>"
		+ BankDefinition.accountName(currentAccount.getAccountType()) + "</td>" + "<td>"
		+ currentAccount.getBalance() + "</td>" + "<td>" + currentAccount.getTransferredAmount() + "</td>"
		+ "<td> <a href=accountDetail?accountNumber="+currentAccount.getAccountNumber() +">USE</a></td>"
		+ "</tr>");

	}
	else{
		out.print("<h2>IT SEEMS YOU DONT HAVE A CURRENT ACCOUNT</h2>");

	}
	%>
</body>
</html>