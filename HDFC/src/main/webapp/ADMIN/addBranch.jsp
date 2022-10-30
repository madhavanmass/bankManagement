<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD BRANCH</title>
</head>
<body>
<jsp:include page="/COMMON/adminnav.html"></jsp:include>
<form action="addBranchServlet" method="post">
ENTER THE BRANCH NAME : <input type="text" name="branchName"><br>
ENTER ADDRESSLINE 1:<input type="text" name="addressLine1"><br>
ENTER ADDRESSLINE 2:<input type="text" name="addressLine2"><br>
ENTER PINCODE :<input type="number" name="pinCode"><br>
<input type="submit" value="CREATE BRANCH">
</form>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>A NEW BRANCH HAS BEEN ADDED THE BRACH ID IS "+session.getAttribute("message")+" </h2>");
	session.removeAttribute("message");
}
%>
</body>
</html>