<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD EMPLOYEE</title>
</head>
<body>

<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
<form action="addEmployeeServlet" method="post">
<jsp:include page="/COMMON/createperson.html"></jsp:include>
<input type="submit" value="ADD EMPLOYEE">
</form>
<% if(session.getAttribute("message") !=null){
	out.print(request.getAttribute("message")+"<br>");
	session.removeAttribute("message");
}
%>
</body>
</html>