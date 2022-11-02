<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ASSIGN MANAGER</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.assignManager{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<h1>ASSIGN MANAGER</h1>
<jsp:include page="/COMMON/adminNav.html"></jsp:include>
<% if(session.getAttribute("message") !=null){
	out.print("<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE MANAGER HAS BEEN SUCCESSFULLY ASSIGNED TO THE BRANCH.. WITH THE MANAGER ID "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
<form name="myForm" onsubmit="return validateForm([13,14,15,16,17,18,19,20,21,22])" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
ENTER THE BRANCH ID : <input type="number" name="branchId">
<input type="hidden" name="role" value="2"> 
<input type="submit" value="ASSIGN MANAGER">
</form>


</body>
</html>