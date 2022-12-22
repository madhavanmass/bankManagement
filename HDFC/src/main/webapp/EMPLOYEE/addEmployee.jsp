<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="JAVASCRIPT/formatCheck.js"></script>
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
<div class="content1">
<h1>ADD EMPLOYEE</h1>
<form name="myForm" onsubmit="return  formatCheck({11:7,12:7,13:3,14:7,15:7,16:7,17:4,18:6,19:2,20:7,21:7,22:7,27:1})" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
<input type="hidden" name="role" value="1"> 
<input type="submit" value="ADD EMPLOYEE">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">!!A NEW EMPLOYEE HAS BEEN ADDDED SUCCESSFULLY!! THE ID FOR THE NEW EMPLOYEE IS : "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</div>
</body>
</html>