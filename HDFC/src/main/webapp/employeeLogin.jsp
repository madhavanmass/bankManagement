<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<script src="JAVASCRIPT/validateForm.js"></script>

</head>
<body>

<h1 style="text-align: center;">WELCOME TO HDFC</h1>
<h2 style="text-align: center;">STAFF LOGIN</h2>

<form name="myForm" onsubmit="return validateForm([26,27])" action="verify" method="post">
ENTER YOUR ID     : <input type="number" name="userId"><br>
ENTER PASSWORD    :<input type="password" name="password"><br>
<!-- SELECT YOUR ROLE :
<select name="role" >
<option value="1">EMPLOYEE</option>
<option value="2">MANAGER</option>
<option value="3">ADMIN</option>
</select><br> -->

<input type="submit" value="LOGIN">
</form>
<% if(session.getAttribute("message")!=null)
{
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</body>
</html>