<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="java.util.HashMap"
    import="bank.hdfc.function.Beneficiary"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENEFICIARY</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<h1>BENEFICIARY</h1>
<%
HashMap<Integer,Beneficiary> beneficiarys= new HashMap<>();
Customer customer=((Customer)session.getAttribute("customer"));
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadBenificary();
	beneficiarys=customer.getCurrentAccount().getBeneficiary();
}
else{
	customer.getSavingAccounts().get(accountNumber).loadBenificary();
	beneficiarys= customer.getSavingAccounts().get(accountNumber).getBeneficiary();
}

if(beneficiarys.size()==0 || beneficiarys==null){
	out.println("YOU DONT HAVE ANY BENEFICIARYS");
}
else{
	out.print(
			"<table><tr><th>BENEFICIARY ID</th><th>CONNECTED ACCOUNT</th><th>TRANSACTION LIMIT</th><th>UPDATE</th><th>DELETE</th></tr> ");
	for(Beneficiary beneficiary:beneficiarys.values()){
		out.print("<tr>" 
				+"<form action=\"updateBeneficiary\">"
				+ "<td><input type=\"text\" name=\"beneficiaryId\" value="+beneficiary.getBeneficiaryId()+" readonly></td>"
				+ "<td><input type=\"text\" name=\"connectedAccount\" value="+beneficiary.getConnectedAccount()+ " readonly></td>"
				+ "<td><input type=\"number\" name=\"transactionLimit\" value="+beneficiary.getTransactionLimit()+"></td>"
				+"<input type=\"hidden\" value="+request.getParameter("accountNumber")+" name=\"accountNumber\">"
				+ "<td><input type=\"submit\" value=\"UPDATE\"></td>"
				+ "<td><input  type=\"submit\" formaction=\"deleteBeneficiary\" value=\"DELETE\" ></td>"
				+"</form>"
				+"</tr>");
	}
	out.print("</table>");
}
%>
<h1>ADD BENEFICIARY</h1>
<form action="addBeneficiary">
ENTER THE ACCOUNT NUMBER YOU TO CONNECT :<input type="number" name="connectedAccount"><br>
ENTER THE TRANSFER LIMIT :<input type="number" name="transactionLimit"><br>
<input type="hidden" value= <%=request.getParameter("accountNumber")%> name="accountNumber">
<input type="submit" value="ADD BENEFICIARY">
</form>
</body>
</html>