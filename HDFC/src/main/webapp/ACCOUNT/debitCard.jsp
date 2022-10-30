<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="bank.hdfc.function.Customer"
    import="bank.hdfc.function.DebitCard"
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEBIT CARD</title>
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
<jsp:include page="/COMMON/topnav.html"></jsp:include>
<jsp:include page="/COMMON/sidebar.jsp"></jsp:include>

<%
Customer customer=((Customer)session.getAttribute("customer"));
DebitCard debitCard=null;
int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber){
	customer.getCurrentAccount().loadDebitCard();
	debitCard=customer.getCurrentAccount().getDebitCard();
}
else{
	customer.getSavingAccounts().get(accountNumber).loadDebitCard();
	debitCard=customer.getSavingAccounts().get(accountNumber).getDebitCard();
}
if(debitCard!=null){
	out.print(debitCard.toString());
}
%>
<c:if test="<%=debitCard!=null%>">
<h1>CHANGE M-PIN</h1>
<button onclick="show()">CHANGE PASSWORD</button>
<form id="form" action="changeMPin" method="post" style="display:none;">
ENTER YOUR OLD MPIN:<input type="number" name="oldMPin">
ENTER NEW MPIN:<input type="number" name="oldMPin">
<input type="hidden" value=<%=request.getParameter("accountNumber")%> name="accountNumber">
<input type="submit" value="CONFIRM">
</form>
<% if(session.getAttribute("message") !=null){
	boolean result=((Boolean)session.getAttribute("message"));
	session.removeAttribute("message");
	if(result){
		out.print("<h2>THE M-PIN HAS BEEN CHANGED SUCCESSFULLY</h2>");
	}
	else{
		out.print("<h2>THE OLD M-PIN DOESNT MATCH</h2>");
	}
}	
%>
<h1>CHANGE CARD STATUS</h1>
<form action="changeStatus">
<input type="hidden" value=<%=request.getParameter("accountNumber")%> name="accountNumber">
<button name="status" value=<%=debitCard.getStatus()%> type="submit"><%=debitCard.getStatus()==1?"LOCK":"ACTIVATE" %></button>
</form>

</c:if>
<c:if test="<%=debitCard==null%>">
<h1>YOU DONT HAVE A DEBIT CARD</h1>
</c:if>



</body>
</html>