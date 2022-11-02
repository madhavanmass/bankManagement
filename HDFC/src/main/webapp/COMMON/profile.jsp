<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="bank.hdfc.function.Employee"
    import="bank.hdfc.function.Admin"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<script src="JAVASCRIPT/hideForm.js"></script>
<meta charset="ISO-8859-1">
<title>PROFILE</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.profile{
	background-color: red;
}
</style>
</head>
<body>
<c:if test="<%=request.getSession().getAttribute(\"customer\") != null %>">
<jsp:include page="/COMMON/topNav.html"></jsp:include>
</c:if>

<c:if test="<%=request.getSession().getAttribute(\"employee\") != null%>">
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
</c:if>

<c:if test="<%=request.getSession().getAttribute(\"admin\") != null%>">
<jsp:include page="/COMMON/adminNav.html"></jsp:include>
</c:if>



<%
if(session.getAttribute("customer")!=null){
	out.print("<table>"+((Customer)session.getAttribute("customer")).toString()+"</table>");
}
else if(session.getAttribute("employee")!=null){
	out.print("<table>"+((Employee)session.getAttribute("employee")).toString()+"</table>");
}
else if(session.getAttribute("admin")!=null){
	out.print("<table>"+((Admin)session.getAttribute("admin")).toString()+"</table>");
}

%>
<button onclick="show()">CHANGE PASSWORD</button>
<form id="form" action="changePasswordServlet" method="post" style="display:none;">
ENTER THE OLD-PASSWORD :<input type="text" name="oldPassword"><br>
ENTER THE NEW-PASSWORD :<input type="text" name="newPassword"><br>

<input type="submit" value="CONFIRM">
</form>
<% if(session.getAttribute("message") !=null){
	out.print(session.getAttribute("message"));
	session.removeAttribute("message");
}
%>

</body>
</html>