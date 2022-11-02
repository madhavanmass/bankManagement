<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.concurrent.ConcurrentHashMap"
    import="bank.hdfc.pack.CustomerDetail"
    import="bank.hdfc.function.Employee"
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
nav a.viewCustomer{
	background-color: red;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
<form action="viewCustomer">
<input type="text" name="name"><input type="submit" value="SEARCH"><br>
</form>

<%
	String name="";
	Pattern pattern;
	Matcher matcher;
	ConcurrentHashMap<Integer,CustomerDetail> customerDetails= new ConcurrentHashMap<>();
	Employee employee=((Employee)session.getAttribute("employee"));
	employee.getBranch().loadCustomers();
	customerDetails=Branch.getCustomerDetails();
	if(request.getParameter("name")!=null && name==""){
		name=request.getParameter("name");
	}
	for(CustomerDetail customerDetail:customerDetails.values()){
		pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(customerDetail.getName());
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      out.println("<table>"+customerDetail.toString()+"</table>");
	    }
	}
%>


</body>
</html>