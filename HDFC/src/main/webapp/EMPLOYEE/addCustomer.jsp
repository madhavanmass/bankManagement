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
</head>
<body>

<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="addMemberServlet" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
<input type="submit" value="CREATE CUSTOMER">

</form>
</body>
</html>