<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
	import="bank.hdfc.function.SavingAccount" import="java.util.HashMap"
	import="bank.hdfc.pack.BankDefinition"
	import="bank.hdfc.function.CurrentAccount"
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.loan, li a.payLoan{
	background-color: red;
}
</style>
</head>

<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/loanSidebar.jsp"></jsp:include>
<div class="content">
 <h1>PAY LOAN</h1>
 <form action="payLoanServlet">
 ENTER THE AMOUNT YOU WANT TO PAY: <input type="number" name="amount">
 ENTER THE ACCOUNT YOU WANT TO PAY: 
 <select name="accountChoose">
 <% 
 ((Customer) session.getAttribute("customer")).loadSavingAccount();
 ((Customer) session.getAttribute("customer")).loadCurrentAccounts();
	HashMap<Integer, SavingAccount> savingAccounts = ((Customer) session.getAttribute("customer")).getSavingAccounts();
	CurrentAccount currentAccount = ((Customer) session.getAttribute("customer")).getCurrentAccount();
	if (savingAccounts.size()!=0) {
		for (SavingAccount savingAccount : savingAccounts.values()) {
			out.print( "<option value="+savingAccount.getAccountNumber()+">ACC NO : " + savingAccount.getAccountNumber()+ "ACC TYPE : SAVING</option>" );	
		}
	}
	if(currentAccount!=null){
		out.print( "<option value="+currentAccount.getAccountNumber()+">ACC NO : " + currentAccount.getAccountNumber()+ "ACC TYPE : CURRENT</option>" );
	}
 
 %>
 
 </select>
 <input type="hidden" name="accountNumber" value=<%= request.getParameter("accountNumber")%>>  
 <input type="submit" value="PAY LOAN">
 </form>
 
 <% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
	
}	
%>
 </div>
</body>
</html>