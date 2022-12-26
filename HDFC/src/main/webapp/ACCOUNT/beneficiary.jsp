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
<div class="content1">
<div class="content">
<h1>BENEFICIARY</h1>
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
if(beneficiarys.size()==0 || beneficiarys==null){
	out.println("<h2>YOU DONT HAVE ANY BENEFICIARYS</h2>");
}
else{
	out.print(
			"<table><tr><th>BENEFICIARY NAME</th><th>BENEFICIARY BANK</th><th>ACCOUNT NUMBER</th><th>TRANSFERED AMOUNT</th><th>TRANSACTION LIMIT</th><th>UPDATE</th><th>DELETE</th><th>TRANSFER</th></tr> ");
	for(Beneficiary beneficiary:beneficiarys.values()){
		out.print("<tr>" 
				
				+"<form name=\"myForm\" onsubmit=\"return validateForm([3,4])\" action=\"updateBeneficiary\">"
				+ "<td><input type=\"text\" name=\"accountHolderName\" value="+beneficiary.getAccountHolderName()+" readonly></td>"
				+ "<td><input type=\"text\" name=\"bankName\" value=\""+beneficiary.getBankName()+"\" readonly></td>"
				+ "<td><input type=\"number\" name=\"connectedAccount\" value="+beneficiary.getConnectedAccount()+ " readonly></td>"
				+ "<td><input type=\"number\" value="+beneficiary.getTransferAmount()+ " readonly></td>"
				+ "<td ><input class=\"limit\" type=\"number\" name=\"transactionLimit\" value="+beneficiary.getTransactionLimit()+"></td>"
				+ "<input type=\"hidden\" name=\"beneficiaryId\" value="+beneficiary.getBeneficiaryId()+">"
				+"<input type=\"hidden\" value="+request.getParameter("accountNumber")+" name=\"accountNumber\">"
				+"<input type=\"hidden\" value="+beneficiary.getIFSCCode()+" name=\"ifsccode\">"
				+ "<input type=\"hidden\" name=\"beneficiaryId\" value="+beneficiary.getBeneficiaryId()+">"
				+ "<td><input type=\"submit\" value=\"UPDATE\"></td>"
				+ "<td><input  type=\"submit\" formaction=\"deleteBeneficiary\" value=\"DELETE\"></td>"
				+ "<td><input  type=\"submit\" formaction=\"transfer\" value=\"TRANSFER\"></td>"
				+"</form>"
				+"</tr>");
	}
	out.print("</table>");
}
%>

<h1>ADD BENEFICIARY</h1>

<form name="myForm" onsubmit="return formatCheck({3:7,4:7,30:7,31:7})" action="addBeneficiary">
ENTER THE ACCOUNT HOLDER NAME :<input type="text" name="accountHolderName"><br>
SELECT THE BANK : 
<select name="bankName">
    <optgroup label="SAME BANK">
        <option value=1 >HDFC</option>
    </optgroup>
    <optgroup label="OTHER BANK">
        <option value=2 >SOUTH INDIAN BANK</option>
        <option value=3 >STATE BANK OF INDIA</option>
        <option value=4 >CANARA BANK</option>
        <option value=5 >BANK OF BARODA</option>
        <option value=6 >CENTRAL BANK OF INDIA</option>
        <option value=7 >INDIAN BANK</option>
    </optgroup>
</select>
ENTER THE ACCOUNT NUMBER :<input type="number" name="connectedAccount"><br>
ENTER THE IFSC CODE:<input type="text" name="ifsccode"><br>
ENTER THE TRANSFER LIMIT :<input type="number" name="transactionLimit"><br>
<input type="hidden" value= <%=request.getParameter("accountNumber")%> name="accountNumber">
<input type="submit" value="ADD BENEFICIARY">
</form>
</div>
</div>
</body>

</html>