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
<jsp:include page="/COMMON/adminNav.html"></jsp:include>
<div class="content1">
<h1>ADD BRANCH</h1>
<form name="myForm" onsubmit="return validateForm([10,11,12,13])" action="addBranchServlet" method="post">
ENTER THE BRANCH NAME : <input type="text" name="branchName"><br>
ENTER ADDRESSLINE 1:<input type="text" name="addressLine1"><br>
ENTER ADDRESSLINE 2:<input type="text" name="addressLine2"><br>
ENTER PINCODE :<input type="number" name="pincode"><br>
<input type="submit" value="CREATE BRANCH">
</form>
<% if(session.getAttribute("message") !=null){
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</div>
</body>
</html>