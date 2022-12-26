<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "bank.hdfc.pack.BankDefinition"
    import="bank.hdfc.function.Customer"
    import="java.util.HashMap"
    import="bank.hdfc.function.Beneficiary"
    %>
     <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
   
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
<form name="myForm" onsubmit="return formatCheck({7:7,8:7,9:7,30:7,31:7})" action="transferServlet" >

<%
String connectedAccountNumber=request.getParameter("connectedAccount");
String accountHolderName=request.getParameter("accountHolderName");
String ifscCode=request.getParameter("ifsccode");
String bankName=request.getParameter("bankName");

%>
ENTER THE ACCOUNT HOLDER NAME [AS IF IN BANK CARD] :
<c:if test="<%=accountHolderName==null%>">
<input type="text" name="accountHolderName" >
</c:if>
<c:if test="<%=accountHolderName!=null%>">
<input type="text" value=<%=accountHolderName%> name="accountHolderName" readonly>
</c:if>

SELECT THE BANK : 

<c:if test="<%=bankName != null%>">
<input type="hidden" name="bankName" value=<%=BankDefinition.getBankId(bankName)%>>
<input type="text" value=<%=bankName%>  readonly>
</c:if>

<c:if test="<%=bankName == null %>">
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
</c:if>

ENTER THE ACCOUNT NUMBER :

<c:if test="<%=connectedAccountNumber==null%>">
<input name="otherAccount" type ="number">
</c:if>
<c:if test="<%=connectedAccountNumber!=null%>">
<input value=<%=connectedAccountNumber%> name="otherAccount" type ="number" readonly>
</c:if>




ENTER THE IFSC CODE :
<c:if test="<%=ifscCode==null%>">
<input type="text" name="ifsccode">
</c:if>
<c:if test="<%=ifscCode!=null%>">
<input type="text" value=<%=ifscCode%> name="ifsccode" readonly>
</c:if>

ENTER THE AMOUNT :<input type="number" name="amount"><br>

ENTER THE MODE : 
<select name="mode">
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