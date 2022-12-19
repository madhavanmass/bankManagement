<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "bank.hdfc.pack.BankDefinition"
    import="bank.hdfc.function.Customer"
    import="java.util.HashMap"
    import="bank.hdfc.function.Beneficiary"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FUND TRANSFER</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.transfer{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); 
response.setDateHeader("Expires", 0);
%>
<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content">
<h1>TRANSFER</h1>
<form name="myForm" onsubmit="return validateForm([7,8,9])" action="transferServlet" >
<!-- ENTER THE OTHER ACCOUNT NUMBER : <input type="number" name="otherAccount"><br> -->
ENTER THE OTHER ACCOUNT : 
<%
HashMap<Integer,Beneficiary> beneficiarys= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
customer.loadCurrentAccounts();
customer.loadSavingAccount();
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadBenificary();
	beneficiarys=customer.getCurrentAccount().getBeneficiary();
}
else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
	customer.getSavingAccounts().get(accountNumber).loadBenificary();
	beneficiarys= customer.getSavingAccounts().get(accountNumber).getBeneficiary();
}
%>
<input list="browsers" name="otherAccount" type ="number">
<datalist id="browsers">
<%
if(beneficiarys.size()!=0){
	for(Beneficiary beneficiary:beneficiarys.values()){
		out.print( "<option value="+beneficiary.getConnectedAccount()+"> TRANSACTION LIMIT : "+beneficiary.getTransactionLimit()+" </option>" );
	}
}
%>
</datalist>
ENTER THE AMOUNT :<input type="number" name="amount"><br>
ENTER THE DESCRIPTION :<br>
<textarea name="description" rows="4" cols="50" placeholder="type here..."></textarea><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>>  
<input type="submit" value="transfer">
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}	
%>
</div>
</body>
</html>