<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
	import="bank.hdfc.function.FixedDeposit"
	import="java.util.HashMap"
	import="bank.hdfc.pack.BankDefinition"
	import="bank.hdfc.function.RecurssiveDeposit"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEPOSIT</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.deposits{
	background-color: red;
}
</style>
</head>
<body>
<jsp:include page="/COMMON/topNav.html"></jsp:include>
<div class="content1">
	<h1>FIXED DEPOSIT</h1>
	<%
	((Customer) session.getAttribute("customer")).loadFixedDeposit();
	HashMap<Integer, FixedDeposit> fixedDeposits = ((Customer) session.getAttribute("customer")).getFixedDeposits();
	if (fixedDeposits!=null && fixedDeposits.size()!=0) {
		out.print(
		"<table><tr><th>DEPOSIT ID</th><th>ACCOUNT NUMBER</th><th>AMOUNT</th><th>DEPOSIT POLICY</th><th>DEPSOIT END DATE</th><th>INTEREST RATE</th><th>MATURITY AMOUNT</th><th>WITHDRAW</th></tr> ");
		for ( FixedDeposit fixedDeposit : fixedDeposits.values()) {
			float maturityAmount=fixedDeposit.getAmount()+(fixedDeposit.getAmount()*fixedDeposit.getTimeDifferenceInMonths()*fixedDeposit.getInterestRate());
			out.print("<tr>" 
			+ "<td>" + fixedDeposit.getDepositId()+ "</td>" 
			+ "<td>"+ fixedDeposit.getAccountNumber()+ "</td>" 
			+ "<td>"+fixedDeposit.getAmount()+ "</td>" 
			+ "<td>" + fixedDeposit.getPolicy()+ "</td>"
			+ "<td>" + fixedDeposit.getDepositEndDate()+ "</td>"
			+ "<td>" + fixedDeposit.getInterestRate()+ "</td>"
			+ "<td>" + maturityAmount+ "</td>"
			+ "<td><a href=cancelDeposit?depositId="+fixedDeposit.getDepositId()+"&accountNumber="+fixedDeposit.getAccountNumber()+">CANCEL</a></td>"
			+"</tr>");
		}
		out.print("</table>");
	} 
	else{
		out.print("<h2>IT SEEMS YOU DONT HAVE A FIXED DEPOSIT</h2>");
	}
	
	%>

	<h1>RECURRING DEPOSIT</h1>
	<%
	((Customer) session.getAttribute("customer")).loadRecurrsiveDeposit();
	HashMap<Integer, RecurssiveDeposit> recurssiveDeposits = ((Customer) session.getAttribute("customer")).getRecurrsiveDeposits();
	if (recurssiveDeposits!=null && recurssiveDeposits.size()!=0) {
		out.print(
		"<table><tr><th>DEPOSIT ID</th><th>ACCOUNT NUMBER</th><th>AMOUNT</th><th>DEPOSIT POLICY</th><th>DEPSOIT END DATE</th><th>INTEREST RATE</th><th>MATURITY AMOUNT</th><th>WITHDRAW</th></tr> ");
		for ( RecurssiveDeposit recurssiveDeposit : recurssiveDeposits.values()) {
			float maturityAmount=recurssiveDeposit.getAmount()+(recurssiveDeposit.getAmount()*recurssiveDeposit.getInterestRate()*recurssiveDeposit.getTimeDifferenceInMonths());
			out.print("<tr>" 
			+ "<td>" + recurssiveDeposit.getDepositId()+ "</td>" 
			+ "<td>"+ recurssiveDeposit.getAccountNumber()+ "</td>" 
			+ "<td>"+recurssiveDeposit.getAmount()+ "</td>" 
			+ "<td>" + recurssiveDeposit.getPolicy()+ "</td>"
			+ "<td>" + recurssiveDeposit.getDepositEndDate()+ "</td>"
			+ "<td>" + recurssiveDeposit.getInterestRate()+ "</td>"
			+ "<td>" +maturityAmount + "</td>"
			+ "<td><a href=cancelDeposit?depositId="+recurssiveDeposit.getDepositId()+"&accountNumber="+recurssiveDeposit.getAccountNumber()+">CANCEL</a></td>"
			+"</tr>");
		}
		out.print("</table>");
	} 
	else{
		out.print("<h2>IT SEEMS YOU DONT HAVE A RECURRING DEPOSIT</h2>");
	}
	%>
	</div>
</body>
</html>