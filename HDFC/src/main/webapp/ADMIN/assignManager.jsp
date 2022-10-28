<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD EMPLOYEE</title>
</head>
<body>


<form action="addMember" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
ENTER THE BRANCH ID : <input type="number" name="branchId">
<input type="hidden" name="role" value="2"> 
<input type="submit" value="ASSIGN MANAGER">
</form>
<% if(session.getAttribute("message") !=null){
	out.print(request.getAttribute("message")+"<br>");
	session.removeAttribute("message");
}
%>
</body>
</html>