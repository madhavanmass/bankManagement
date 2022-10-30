<ul>
  <li><a class="accountDetail" href="accountDetail?accountNumber=<%=request.getParameter("accountNumber")%>">ACCOUNT DETAIL</a></li>
  <li><a class="payBill" href="payBill?accountNumber=<%=request.getParameter("accountNumber")%>">PAY BILL</a></li>
  <li><a class="transfer" href="transfer?accountNumber=<%=request.getParameter("accountNumber")%>">TRANSFER</a></li>
  <li><a class="transaction" href="transaction?accountNumber=<%=request.getParameter("accountNumber")%>">TRANSACTION HISTORY</a></li>
  <li><a class="beneficiary" href="beneficiary?accountNumber=<%=request.getParameter("accountNumber")%>">BENEFICIARY</a></li>
  <li><a class="debitCard" href="debitCard?accountNumber=<%=request.getParameter("accountNumber")%>">DEBIT CARD</a></li>
  <li><a class="openDeposit" href="openDeposit?accountNumber=<%=request.getParameter("accountNumber")%>">OPEN DEPOSIT</a></li>
</ul>