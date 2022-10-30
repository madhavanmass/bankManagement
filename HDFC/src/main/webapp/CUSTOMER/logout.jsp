<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGOUT</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.logout{
	background-color: red;
}
</style>
</head>
<body>
<h1>ARE YOU SURE YOU WANT TO LOGOUT</h1>
<form action="logoutServlet">
  <input type="submit" value="LOGOUT">
</form>
</body>
</html>