<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.HashMap"
    import="bank.hdfc.function.Admin"
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
nav a.viewBranch{
	background-color: red;
}
form{
 all: unset;
}
</style>
</head>
<body>

<jsp:include page="/COMMON/adminNav.html"></jsp:include>
<div class="content1">
<h1>VIEW BRANCH</h1>
<form class="search" action="viewBranch">
<input type="text" name="name"><input type="submit" value="SEARCH"><br>
</form>

<%
	String name="";
	Pattern pattern;
	Matcher matcher;
	HashMap<Integer,Branch> branches= new HashMap<>();
	Admin admin=((Admin)session.getAttribute("admin"));
	admin.loadBranch();
	branches=admin.getBranchDetails();
	if(request.getParameter("name")!=null && name==""){
		name=request.getParameter("name");
	}
	for(Branch branchDetail:branches.values()){
		branchDetail.loadCustomers();
		branchDetail.loadEmployee();
		pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
		StringBuffer str=new StringBuffer(branchDetail.getAddressLine1());
		str.append(":");
		str.append(branchDetail.getAddressLine2());
		str.append(":");
		str.append(branchDetail.getPinCode());
		str.append(":");
		str.append(branchDetail.getBranchName());
		str.append(":");
		str.append(branchDetail.getIfscCode());
		matcher = pattern.matcher(str.toString());
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      out.println("<table>"+branchDetail.toString()+
	    		  "<tr><td style=\"width:50%\" > NUMBER OF EMPLOYEE </td><td>"+branchDetail.getEmployeeDetails().size()+ "</td></tr>"+
	    		  "</table>");
	    }
	}
%>

</div>
</body>
</html>