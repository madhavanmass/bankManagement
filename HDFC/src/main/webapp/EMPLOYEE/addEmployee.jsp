<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD EMPLOYEE</title>
</head>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.addEmployee{
	background-color: red;
}
</style>
<body>

<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="addMemberServlet" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
<input type="hidden" name="role" value="1"> 
<input type="submit" value="ADD EMPLOYEE">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>A NEW EMPLOYEE HAS BEEN ADDDED TO YOUR BRANCH THE EMPLOYEE ID IS "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>