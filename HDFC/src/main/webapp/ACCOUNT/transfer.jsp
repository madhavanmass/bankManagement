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
<script src="JAVASCRIPT/formatCheck.js"></script>
</head>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); 
response.setDateHeader("Expires", 0);
%>
<body>

<jsp:include page="/COMMON/topNav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<div class="content1">
<div class="content">
<h1>TRANSFER</h1>
<form name="myForm" onsubmit="return formatCheck({7:7,8:7,9:7,30:7,31:7,32:2})" action="transferServlet" >
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


<%-- SELECT IF BENEFICIARY : 
<select  name="beneficiary" >
    <optgroup label="NOT BENEFICIARY">
        <option value=0 >NON BENEFICIARY</option>
    </optgroup>
    <optgroup label="BENEFICIARY">
    <%
	if(beneficiarys.size()!=0){
		for(Beneficiary beneficiary:beneficiarys.values()){
			out.print( "<option value="+beneficiary.getBeneficiaryId()+"> "+beneficiary.getAccountHolderName()+"["+beneficiary.getBankName() +"] </option>" );
		}
	}
	%>
    </optgroup>
</select> --%>

ENTER THE ACCOUNT HOLDER NAME [AS IF IN BANK CARD] :
<input type="text" name="accountHolderName">
SELECT THE BANK : 
<select name="bankName">
    <optgroup label="SAME BANK">
        <option value=1 >HDFC</option>
    </optgroup>
    <optgroup label="OTHER BANK">
        <option value=2 >SOUTH INDIAN BANK</option>
        <option value=3 >STATE BANK OF INDIA</option>
        <option value=4 >CANARA BANK</option>
        <option value=5>BANK OF BARODA</option>
        <option value=6>CENTRAL BANK OF INDIA</option>
        <option value=7>INDIAN BANK</option>
    </optgroup>
</select>
ENTER THE ACCOUNT NUMBER :
<input name="otherAccount" type ="number">




ENTER THE IFSC CODE :
<input type="text" name="ifsccode"><br>
ENTER THE MOBILE  NUMBER : 
<input type="number" name="phoneNumber"><br>
ENTER THE AMOUNT :<input type="number" name="amount"><br>

ENTER THE MODE : 
<select>
<option>IMPS (immediate)</option>
<option>NEFT (within 1 hour)</option>
<option>UPI (immediate)</option>
</select>
REMARKS :<br>
<textarea name="description" rows="4" cols="50" placeholder="type here..."></textarea><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>>  
<input type="submit" value="TRANSFER">
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}	
%>
</div>
</div>
</body>
</html>