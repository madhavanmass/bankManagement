<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="JAVASCRIPT/formatCheck.js"></script>
<meta charset="ISO-8859-1">
<title>ADD CUSTOMER</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.addCustomer{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>


<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<div class="content1">
<h1>ADD CUSTOMER</h1>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
<form name="myForm" onsubmit="return  formatCheck({11:7,12:7,13:3,14:7,15:7,16:7,17:4,18:6,19:2,20:7,21:7,22:7,27:1})" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
<input type="submit" value="CREATE CUSTOMER">

</form>
</div>
</body>
</html>