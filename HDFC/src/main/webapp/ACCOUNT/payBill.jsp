<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PAY BILL</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<form action="payBillServlet">
ENTER THE AMOUNT :<input type="number" name="amount"><br>
ENTER THE DESCRIPTION :<br>
<textarea name="description" rows="4" cols="50" placeholder="type here...">.</textarea><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber")%>>  
<input type="submit" value="pay">
<% if(request.getAttribute("message") instanceof Integer){
	out.print("<h1>"+request.getAttribute("message")+"</h1>");
}	
%>
</form>
</body>
</html>