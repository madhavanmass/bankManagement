<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD BRANCH</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.addBranch{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<h1>ADD BRANCH</h1>
<jsp:include page="/COMMON/adminNav.html"></jsp:include>
<form name="myForm" onsubmit="return validateForm([10,11,12,13])" action="addBranchServlet" method="post">
ENTER THE BRANCH NAME : <input type="text" name="branchName"><br>
ENTER ADDRESSLINE 1:<input type="text" name="addressLine1"><br>
ENTER ADDRESSLINE 2:<input type="text" name="addressLine2"><br>
ENTER PINCODE :<input type="number" name="pincode"><br>
<input type="submit" value="CREATE BRANCH">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\" >A NEW BRANCH HAS BEEN ADDED THE BRACH ID IS "+session.getAttribute("message")+" </h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>