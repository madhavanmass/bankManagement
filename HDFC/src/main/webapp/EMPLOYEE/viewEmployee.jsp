<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.concurrent.ConcurrentHashMap"
	import="bank.hdfc.pack.EmployeeDetail"
	import="bank.hdfc.function.Manager"
	import="bank.hdfc.function.Admin"
	 import="bank.hdfc.function.Branch"
	import="java.util.regex.Matcher" import="java.util.regex.Pattern"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VIEW CUSTOMERS</title>
<link rel="stylesheet" href="CSS/topNavStyle.css">
<style>
nav a.viewEmployee {
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
Manager manager = ((Manager) session.getAttribute("employee"));
Admin admin=(Admin)session.getAttribute("admin");
%>
 <c:if test="<%=manager!=null %>">
	<jsp:include page="/COMMON/employeeNav.jsp"></jsp:include>
</c:if>
<c:if test="<%=admin!=null%>">
	<jsp:include page="/COMMON/adminNav.html"></jsp:include>
</c:if>
	<div class="content1">
		<h1>VIEW EMPLOYEE</h1>
		<form class="search" action="viewEmployee">
			<input type="text" name="name"><input type="submit"
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
		
		
		
		if(manager!=null){
			ConcurrentHashMap<Integer, EmployeeDetail> employeeDetails = new ConcurrentHashMap<>();
			manager.getBranch().setEmployeeDetails(null);
			manager.getBranch().loadEmployee();
			employeeDetails = manager.getBranch().getEmployeeDetails();
			if (request.getParameter("name") != null && name == "") {
				name = request.getParameter("name");
			}
			for (EmployeeDetail employeeDetail : employeeDetails.values()) {
				pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
				StringBuffer str=new StringBuffer(employeeDetail.getName());
				str.append(":");
				str.append(employeeDetail.getEmployeeId());
				matcher = pattern.matcher(str.toString());	
				boolean matchFound = matcher.find();
				if (matchFound) {
					out.println("<table>" + employeeDetail.toString() + "</table>");
				}
			}
		}
		else if (admin!=null){
			admin.loadBranch();
			for(Branch branch:admin.getBranchDetails().values()){
				ConcurrentHashMap<Integer, EmployeeDetail> employeeDetails = new ConcurrentHashMap<>();
				branch.setEmployeeDetails(null);
				branch.loadEmployee();
				employeeDetails = branch.getEmployeeDetails();
				if (request.getParameter("name") != null && name == "") {
					name = request.getParameter("name");
				}
				for (EmployeeDetail employeeDetail : employeeDetails.values()) {
					pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
					StringBuffer str=new StringBuffer(employeeDetail.getName());
					str.append(":");
					str.append(employeeDetail.getEmployeeId());
					str.append(":");
					str.append(employeeDetail.getBranchId());
					matcher = pattern.matcher(str.toString());	
					boolean matchFound = matcher.find();
					if (matchFound) {
						out.println("<table>" + employeeDetail.toString() 
								+ "<tr><td style=\"width:50%\"> BRANCH ID </td><td>"+employeeDetail.getBranchId()+ "</td></tr>"
								+ "</table>");
					}
				}
			}
		}
		
		
		%>

	</div>
</body>
</html>