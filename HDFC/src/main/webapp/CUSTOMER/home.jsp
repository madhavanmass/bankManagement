<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.home{
	background-color: red;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body style="background-image: url('IMAGE/background.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;">

<jsp:include page="/COMMON/topNav.html"></jsp:include>

<div class="content1">
<h1 style="text-align: center;">WELCOME TO HDFC ${sessionScope.customer.getName()}</h1>
</div>

</body>
</html>