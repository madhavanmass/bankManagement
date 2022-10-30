<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
</head>
<body>
<h1>WELCOME TO HDFC</h1>
<h2>STAFF LOGIN</h2>
<form action="verify" method="post">
ENTER YOUR ID     : <input type="number" name="userId" value="7"><br>
ENTER PASSWORD    :<input type="password" name="password" value="akilan000"><br>
SELECT YOUR ROLE :
<select name="role" >
<option value="1">EMPLOYEE</option>
<option value="2">MANAGER</option>
<option value="3">ADMIN</option>
</select><br>
<% if(session.getAttribute("message")!=null)
{
	out.print((String)session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
<input type="submit" value="LOGIN">
</form>

</body>
</html>