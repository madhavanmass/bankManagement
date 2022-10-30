<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD CUSTOMER</title>
</head>
<body>
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="addMemberServlet" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
<input type="submit" value="CREATE CUSTOMER">

</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>A NEW CUSTOMER HAS BEEN ADDDED TO YOUR BANK THE CUSTOMER ID IS "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>