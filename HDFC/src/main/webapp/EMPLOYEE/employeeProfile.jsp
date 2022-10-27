<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Employee"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROFILE</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<%=((Employee)session.getAttribute("employee")).toString() %>
<form action="changePassword" method="post">
<input type="submit" value="Change Password">
</form>
</body>
</html>