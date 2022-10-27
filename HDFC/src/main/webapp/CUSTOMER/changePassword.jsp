<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>change password</title>
</head>
<body>
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<h1>CHANGE PASSWORD</h1>
<form action="ChangePassword" method="post">
ENTER THE OLD-PASSWORD :<input type="text" name="oldPassword"><br>
ENTER THE NEW-PASSWORD :<input type="text" name="newPassword"><br>
<% if(request.getAttribute("message") instanceof String){
	out.print(request.getAttribute("message")+"<br>");
}
%>
<input type="submit" value="confirm">
</form>
</body>
</html>