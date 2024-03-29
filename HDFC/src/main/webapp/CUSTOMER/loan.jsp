<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="bank.hdfc.function.LoanAccount"
    import="java.util.HashMap"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>loan</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.loan{
	background-color: red;
}
</style>
</head>
<body>
 <jsp:include page="/COMMON/topNav.html"></jsp:include>
<div class="content1">
	<h1>LOAN ACCOUNT</h1>
	<%
	
	if(session.getAttribute("message") !=null){
		out.print(session.getAttribute("message"));
		session.removeAttribute("message");
	}
	((Customer) session.getAttribute("customer")).setLoanAccounts(null);
	((Customer) session.getAttribute("customer")).loadLoanAccount();
	HashMap<Integer, LoanAccount> loanAccounts = ((Customer) session.getAttribute("customer")).getLoanAccounts();
	if (loanAccounts.size()!=0) {
		out.print(
		"<table><tr><th>ACCOUNT  NUMBER</th><th>TOTAL AMOUNT</th><th>BALANCE</th><th>LOAN END DATE</th><th>AMOUNT PAID</th><th>ACTION</th></tr> ");
		for (LoanAccount loanAccount : loanAccounts.values()) {
			out.print("<tr>" 
			+ "<td>" + loanAccount.getAccountNumber()+ "</td>" 
			+ "<td>"+ loanAccount.getTotalAmount()+ "</td>" 
			+ "<td>"+ loanAccount.getBalance()+ "</td>" 
			+ "<td>" + loanAccount.getLoanEndDate().toString()+ "</td>"
			+"<td>" + loanAccount.getAmountPaid()+ "</td>"
			+ "<td><a href=loanDetail?accountNumber="+loanAccount.getAccountNumber() +">VIEW</a></td>"
			+"</tr>");
		}
		out.print("</table>");
	} 
	else{
		out.print("<h2>IT SEEMS YOU DONT HAVE A LOAN </h2>");
	}
	
	%>
	</div>
</body>
</html>