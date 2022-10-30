<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "bank.hdfc.pack.BankDefinition"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FUND TRANSFER</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<form action="transferServlet">
ENTER THE OTHER ACCOUNT NUMBER : <input type="number" name="otherAccount"><br>
ENTER THE AMOUNT :<input type="number" name="amount"><br>
ENTER THE DESCRIPTION :<br>
<textarea name="description" rows="4" cols="50" placeholder="type here..."></textarea><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>>  
<input type="submit" value="transfer">
<% if(session.getAttribute("message") !=null){
	int resultInt=Integer.valueOf((String)session.getAttribute("message"));
	session.removeAttribute("message");
	if(resultInt==1){
		out.print("<h1>MONEY HAS BEEN TRANSFERED SUCCESSFULLY</h1>");
	}
	else{
		out.print(BankDefinition.accountMessage(resultInt));
	}
}	
%>
</form>
</body>
</html>