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
<script src="JAVASCRIPT/formatCheck.js"></script>
<script src="JAVASCRIPT/validateForm.js"></script>
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
<div class="content1">

<h1>PROFILE</h1>

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
else{
	response.sendRedirect("ERROR/sessionErrorPage.jsp");
}

%>

<button onclick="show()">CHANGE PASSWORD</button>
<form  name="myForm" id="form" onsubmit="return  formatCheck({28:1,29:7})" action="changePasswordServlet"  method="post"  style="display:none;">
ENTER THE OLD-PASSWORD :<input type="text" name="oldPassword"><br>
ENTER THE NEW-PASSWORD :<input type="text" name="newPassword"><br>
<p style="font-size: 12px;" >* THE PASSWORD [8 - 15] CHARACTERS MUST ATLEAT HAVE A 1 UPPERCASE , 1 LOWERCASE ,1 NUMBER AND 1 SPECIAL CHARACTER</p>
<input type="submit" value="CONFIRM">
</form>
<% if(session.getAttribute("message") !=null){
	out.print(session.getAttribute("message"));
	session.removeAttribute("message");
}
%>
</div>

</body>
</html>