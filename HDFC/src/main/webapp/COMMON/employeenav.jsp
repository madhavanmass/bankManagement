<nav>
	<a class="profile" href="profile">PROFILE</a>
	<a class="addCustomer" href="addCustomer">ADD CUSTOMER</a>
	<a class="createAccount" href="createAccount">CREATE ACCOUNT</a>
	<a class="depsitMoney" href="depsitMoney">DEPOSIT MONEY</a>
	<a class="issueDebitCard" href="issueDebitCard">ISSUE DEBIT CARD</a>
	<a class="viewCustomer" href="viewCustomer">VIEW CUSTOMER</a>
	<c:if test="<%=session.getAttribute(\"manager\")!=null%>">
	<a class="viewEmployee" href="viewEmployee">DEPOSIT MONEY</a>
	<a class="giveLoan" href="giveLoan">ISSUE DEBIT CARD</a>
	</c:if>
	<a class="logout" href="logout">LOGOUT</a>
</nav>