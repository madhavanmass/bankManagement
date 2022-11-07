<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="java.util.HashMap"
    import="bank.hdfc.function.Beneficiary"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENEFICIARY</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<link rel="stylesheet" href="CSS/sideBarStyle.css">
<style>
nav a.accounts, li a.beneficiary{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content">
<h1>BENEFICIARY</h1>
<%
HashMap<Integer,Beneficiary> beneficiarys= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadBenificary();
	beneficiarys=customer.getCurrentAccount().getBeneficiary();
}
else{
	customer.getSavingAccounts().get(accountNumber).loadBenificary();
	beneficiarys= customer.getSavingAccounts().get(accountNumber).getBeneficiary();
}

if(beneficiarys.size()==0 || beneficiarys==null){
	out.println("<h2>YOU DONT HAVE ANY BENEFICIARYS</h2>");
}

else{
	out.print(
			"<table><tr><th>BENEFICIARY ID</th><th>CONNECTED ACCOUNT</th><th>TRANSFERED AMOUNT</th><th>TRANSACTION LIMIT</th><th>UPDATE</th><th>DELETE</th></tr> ");
	for(Beneficiary beneficiary:beneficiarys.values()){
		out.print("<tr>" 
				+"<form name=\"myForm\" onsubmit=\"return validateForm([2,3,4])\" action=\"updateBeneficiary\">"
				+ "<td><input type=\"number\" name=\"beneficiaryId\" value="+beneficiary.getBeneficiaryId()+" readonly></td>"
				+ "<td><input type=\"number\" name=\"connectedAccount\" value="+beneficiary.getConnectedAccount()+ " readonly></td>"
				+ "<td><input type=\"number\" value="+beneficiary.getTransferAmount()+ " readonly></td>"
				+ "<td ><input class=\"limit\" type=\"number\" name=\"transactionLimit\" value="+beneficiary.getTransactionLimit()+"></td>"
				+"<input type=\"hidden\" value="+request.getParameter("accountNumber")+" name=\"accountNumber\">"
				+ "<td><input type=\"submit\" value=\"UPDATE\"></td>"
				+ "<td><input  type=\"submit\" formaction=\"deleteBeneficiary\" value=\"DELETE\"></td>"
				+"</form>"
				+"</tr>");
	}
	out.print("</table>");
}
%>
<h1>ADD BENEFICIARY</h1>
<form name="myForm" onsubmit="return validateForm([3,4])" action="addBeneficiary">
ENTER THE ACCOUNT NUMBER YOU TO CONNECT :<input type="number" name="connectedAccount"><br>
ENTER THE TRANSFER LIMIT :<input type="number" name="transactionLimit"><br>
<input type="hidden" value= <%=request.getParameter("accountNumber")%> name="accountNumber">
<input type="submit" value="ADD BENEFICIARY">
</form>
</div>
</body>

</html>