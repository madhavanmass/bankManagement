<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ARE YOU SURE YOU WANT TO LOGOUT</h1>
<form action="logoutServlet">
  <input type="checkbox" id="remember" name="remember" value="remember">
  <label for="remember"> REMEMBER MY LOGIN </label><br>
  <input type="submit" value="LOGOUT">
</form>
</body>
</html>