<ul>
<c:if test
  <li><a href="accountDetail?accountNumber=<%=request.getParameter("accountNumber")%>">ACCOUNT DETAIL</a></li>
  <li><a href="payBill?accountNumber=<%=request.getParameter("accountNumber")%>">PAY BILL</a></li>
  <li><a href="transfer?accountNumber=<%=request.getParameter("accountNumber")%>">TRANSFER</a></li>
  <li><a href="transaction?accountNumber=<%=request.getParameter("accountNumber")%>">TRANSACTION HISTORY</a></li>
  <li><a href="beneficiary?accountNumber=<%=request.getParameter("accountNumber")%>">BENEFICIARY</a></li>
  <li><a href="debitCard?accountNumber=<%=request.getParameter("accountNumber")%>">DEBIT CARD</a></li>
  <li><a href="openDeposit?accountNumber=<%=request.getParameter("accountNumber")%>">OPEN DEPOSIT</a></li>
</ul>