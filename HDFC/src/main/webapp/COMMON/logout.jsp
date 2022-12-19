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
input[type=submit]{
	width: 100%;
	padding: 15px;
	border: 4px solid #ccc;
	border-radius: 4px;
	resize: vertical;
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