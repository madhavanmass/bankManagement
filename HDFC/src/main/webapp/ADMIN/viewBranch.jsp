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
</style>
</head>
<body>
<jsp:include page="/COMMON/adminnav.html"></jsp:include>
<form action="viewBranch">
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
		pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(branchDetail.getAddressLine1()+branchDetail.getAddressLine2()+branchDetail.getPinCode());
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      out.println(branchDetail.toString());
	    }
	}
%>


</body>
</html>