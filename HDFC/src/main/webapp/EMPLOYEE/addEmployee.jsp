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
<script src="JAVASCRIPT/validateForm.js"></script>
<body>

<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<form name="myForm" onsubmit="return validateForm([13,15,16,17,18,19,20,21,22])" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
<input type="hidden" name="role" value="1"> 
<input type="submit" value="ADD EMPLOYEE">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">A NEW EMPLOYEE HAS BEEN ADDDED TO YOUR BRANCH THE EMPLOYEE ID IS "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>