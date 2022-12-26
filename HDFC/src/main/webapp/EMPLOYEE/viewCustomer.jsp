<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.concurrent.ConcurrentHashMap"
	import="bank.hdfc.pack.CustomerDetail"
	import="bank.hdfc.function.Employee"
	import="bank.hdfc.function.Admin"
	 import="bank.hdfc.function.Branch"
	import="java.util.regex.Matcher" import="java.util.regex.Pattern"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VIEW CUSTOMERS</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.viewCustomer {
	background-color: red;
}

form {
	all: unset;
}
</style>
<script src="JAVASCRIPT/validateForm.js"></script>
</head>
<body>
<%
Employee employee = ((Employee) session.getAttribute("employee"));
Admin admin=(Admin)session.getAttribute("admin");
%>

 <c:if test="<%=employee!=null %>">
	<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
</c:if>
<c:if test="<%=admin!=null%>">
	<jsp:include page="/COMMON/adminNav.html"></jsp:include>
</c:if>
	<div class="content1">
		<h1>VIEW CUSTOMER</h1>
		<form class="search" action="viewCustomer">
			<input type="text" name="name"> <input type="submit"
				value="SEARCH"><br>
		</form>
	
		<%
		//prevent cache
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String name = "";
		Pattern pattern;
		Matcher matcher;
		
		
		
		if(employee!=null){
			ConcurrentHashMap<Integer, CustomerDetail> customerDetails = new ConcurrentHashMap<>();
			employee.getBranch().setCustomerDetails(null);
			employee.getBranch().loadCustomers();
			customerDetails = employee.getBranch().getCustomerDetails();
			if (request.getParameter("name") != null && name == "") {
				name = request.getParameter("name");
			}
			for (CustomerDetail customerDetail : customerDetails.values()) {
				pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
				StringBuffer str=new StringBuffer(customerDetail.getName());
				str.append(":");
				str.append(customerDetail.getCustomerId());
				matcher = pattern.matcher(str.toString());
				boolean matchFound = matcher.find();
				if (matchFound) {
					out.println("<table>" + customerDetail.toString() + "</table>");
				}
			}
		}
		else if(admin!=null){
			admin.loadBranch();
			
			ConcurrentHashMap<Integer, CustomerDetail> customerDetails = new ConcurrentHashMap<>();
			Branch branch=null;
			for(Branch branchInfo:admin.getBranchDetails().values()){
				branch=branchInfo;
				break;
			}
			branch.loadCustomers();
			customerDetails = branch.getCustomerDetails();
			if (request.getParameter("name") != null && name == "") {
				name = request.getParameter("name");
			}
			for (CustomerDetail customerDetail : customerDetails.values()) {
				pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
				StringBuffer str=new StringBuffer(customerDetail.getName());
				str.append(":");
				str.append(customerDetail.getCustomerId());
				matcher = pattern.matcher(str.toString());
				boolean matchFound = matcher.find();
				if (matchFound) {
					out.println("<table>" + customerDetail.toString() + "</table>");
				}
			}
		
		}
		%>

	</div>
</body>
</html>