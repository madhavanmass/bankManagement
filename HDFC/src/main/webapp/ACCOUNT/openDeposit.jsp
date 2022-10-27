<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>
<h1>OPEN DEPOSIT</h1>
<form action="openDepositServlet">
ENTER THE AMOUNT :<input type="number" name="amount"><br>
SELECT THE TYPE:
<select name="depositType">
<option value="1">FIXED DEPOSIT</option>
<option value="2">RECURRING DEPOSIT</option>
</select><br>
<select name="depositPolicy">
<option value="1">ONE MONTH</option>
<option value="2">2 MONTH</option>
<option value="3">3 MONTH</option>
<option value="4">6 MONTH</option>
<option value="5">ONE YEAR</option>
<option value="6">5 YEAR</option>
</select><br>
<input type="hidden" name="accountNumber" value=<%=request.getParameter("accountNumber") %>>
<input type="submit" value="OPEN DEPOSIT">
</form>
</body>
</html>