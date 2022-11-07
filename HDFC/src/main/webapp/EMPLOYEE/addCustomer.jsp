<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<h1>ADD CUSTOMER</h1>
<form name="myForm" onsubmit="return validateForm([13,15,16,17,18,19,20,21,22])" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
<input type="submit" value="CREATE CUSTOMER">

</form>
</body>
</html>