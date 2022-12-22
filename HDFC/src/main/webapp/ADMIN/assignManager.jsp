<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Admin"%>
<!DOCTYPE html>
<html>
<head>
<script src="JAVASCRIPT/formatCheck.js"></script>
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

<jsp:include page="/COMMON/adminNav.html"></jsp:include>
<div class="content1">
<h1>ASSIGN MANAGER</h1>
<% if(session.getAttribute("message") !=null){
	out.print(session.getAttribute("message"));
	session.removeAttribute("message");
}
%>

<form name="myForm" onsubmit="return  formatCheck({11:7,12:7,13:3,14:7,15:7,16:7,17:4,18:6,19:2,20:7,21:7,22:7,27:1})" action="addMemberServlet" method="post" >
<jsp:include page="/COMMON/createPerson.html"></jsp:include>
SELECT THE BRANCH : 

<select name="branchId">
<%
Admin admin=(Admin)session.getAttribute("admin");
admin.loadBranch();
for(Integer branch:admin.getBranchDetails().keySet()){
	if(admin.getBranchDetails().get(branch).getManager()==null){
		out.print("<option value="+branch+">"+admin.getBranchDetails().get(branch).getBranchName()+"</option>");
	}
}
%>
</select>
<!-- <input type="number" name="branchId"> -->
<input type="hidden" name="role" value="2"> 
<input type="submit" value="ASSIGN MANAGER">
</form>

</div>
</body>
</html>