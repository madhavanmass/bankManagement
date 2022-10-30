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

<meta charset="ISO-8859-1">
<title>PROFILE</title>
<script type="text/javascript">
var a=0;
function show()
{
    if(a==1){
    	a=0;
        document.getElementById("form").style.display="none";
    }
    else{
    	a=1;
        document.getElementById("form").style.display="block";
    }
}

</script>
</head>
<body>
<c:if test="<%=request.getSession().getAttribute(\"customer\") != null %>">
<jsp:include page="/COMMON/topnav.html"></jsp:include>
</c:if>

<c:if test="<%=request.getSession().getAttribute(\"employee\") != null%>">
<jsp:include page="/COMMON/employeenav.jsp"></jsp:include>
</c:if>

<c:if test="<%=request.getSession().getAttribute(\"admin\") != null%>">
<jsp:include page="/COMMON/adminnav.html"></jsp:include>
</c:if>



<%
if(session.getAttribute("customer")!=null){
	out.print(((Customer)session.getAttribute("customer")).toString());
}
else if(session.getAttribute("employee")!=null){
	out.print(((Employee)session.getAttribute("employee")).toString());
}
else if(session.getAttribute("admin")!=null){
	out.print(((Admin)session.getAttribute("admin")).toString());
}

%>
<button onclick="show()">CHANGE PASSWORD</button>
<form id="form" action="changePasswordServlet" method="post" style="display:none;">
ENTER THE OLD-PASSWORD :<input type="text" name="oldPassword"><br>
ENTER THE NEW-PASSWORD :<input type="text" name="newPassword"><br>
<% if(session.getAttribute("message") !=null){
	out.print("<h2>"+request.getAttribute("message")+"</h2>");
	session.removeAttribute("message");
}
%>
<input type="submit" value="CONFIRM">
</form>

</body>
</html>