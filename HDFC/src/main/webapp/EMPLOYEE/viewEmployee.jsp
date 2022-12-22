<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.concurrent.ConcurrentHashMap"
    import="bank.hdfc.pack.EmployeeDetail"
    import="bank.hdfc.function.Manager"
    import="bank.hdfc.function.Branch"
    import="java.util.regex.Matcher"
	import="java.util.regex.Pattern"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VIEW CUSTOMERS</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.viewEmployee{
	background-color: red;
}
form{
 all: unset;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<div class="content1">
<h1>VIEW EMPLOYEE</h1>
<form class="search" action="viewEmployee">
<input type="text" name="name"><input type="submit" value="SEARCH"><br>
</form>

<%
	String name="";
	Pattern pattern;
	Matcher matcher;
	ConcurrentHashMap<Integer,EmployeeDetail> employeeDetails= new ConcurrentHashMap<>();
	Manager manager=((Manager)session.getAttribute("employee"));
	Branch.setCustomerDetails(null);
	manager.getBranch().loadEmployee();
	employeeDetails=Branch.getEmployeeDetails();
	if(request.getParameter("name")!=null && name==""){
		name=request.getParameter("name");
	}
	for(EmployeeDetail employeeDetail:employeeDetails.values()){
		pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(employeeDetail.getName());
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      out.println("<table>"+employeeDetail.toString()+"</table>");
	    }
	}
%>

</div>
</body>
</html>