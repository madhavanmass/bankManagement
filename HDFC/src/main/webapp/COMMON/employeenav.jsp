<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bank.hdfc.function.Employee" %>
<nav>
	<a class="profile" href="profilePage">PROFILE</a>
	<a class="addCustomer" href="addCustomer">ADD CUSTOMER</a>
	<a class="createAccount" href="createAccount">CREATE ACCOUNT</a>
	<a class="depositMoney" href="depositMoney">DEPOSIT MONEY</a>
	<a class="issueDebitCard" href="issueDebitCard">ISSUE DEBIT CARD</a>
	<a class="viewCustomer" href="viewCustomer">VIEW CUSTOMER</a>
	<c:if test="<%=((Employee)session.getAttribute(\"employee\")).getRole()==2%>">
	<a class="giveLoan" href="giveLoan">GIVE LOAN</a>
	<a class="addEmployee" href="addEmployee">ADD EMPLOYEE</a>
	<a class="viewEmployee" href="viewEmployee">VIEW EMPLOYEE</a>
	</c:if>
	<a class="logout" href="logout">LOGOUT</a>
</nav>