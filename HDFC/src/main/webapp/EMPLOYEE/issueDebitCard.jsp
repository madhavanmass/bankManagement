<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ISSUE DEBIT CARD</title>
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="issueDebitCardServlet" method="post">
ENTER THE CUSTOMER ID :<input type="text" name="customerId"><br>
ENTER THE ACCOUNT NUMBER :<input type="number" name="accountNumber"><br>
ENTER THE M-Pin :<input type="number" name="amount"><br>
<input type="submit" value="ISSUE DEBIT CARD"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print(request.getAttribute("message")+"<br>");
	session.removeAttribute("message");
}
%>
</body>
</html>