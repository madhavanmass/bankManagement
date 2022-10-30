<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ASSIGN MANAGER</title>
</head>
<body>
<jsp:include page="/COMMON/adminnav.html"></jsp:include>
<form action="addMemberServlet" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
ENTER THE BRANCH ID : <input type="number" name="branchId">
<input type="hidden" name="role" value="2"> 
<input type="submit" value="ASSIGN MANAGER">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>THE MANAGER HAS BEEN SUCCESSFULLY ASSIGNED TO THE BRANCH.. WITH THE MANAGER ID "+session.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>