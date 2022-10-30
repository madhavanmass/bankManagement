<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GIVE LOAN</title>
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="giveLoanServlet" method="post">
ENTER THE CUSTOMER ID :<input type="text" name="customerId"><br>
<select name="loanType">
<option value="1">PERSONAL LOAN</option>
<option value="2">HOME LOAN</option>
<option value="3">EDUCATION LOAN</option>
<option value="4">GOLD LOAN</option>
<option value="5">VEHICLE LOAN</option>
</select>
ENTER THE AMOUNT :<input type="number" name="amount"><br>
ENTER THE ACCOUNT NUMBER YOU WANT TO PUT THE MONEY IN :<input type="text" name="accountNumber"><br>
<input type="submit" value="GIVE LOAN"> 
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>"+(String)session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>