 <ul>
  <li><a class="loanDetail" href="loanDetail?accountNumber=<%=request.getParameter("accountNumber")%>">ACCOUNT DETAIL</a></li>
  <li><a class="payLoan" href="payLoan?accountNumber=<%=request.getParameter("accountNumber")%>">PAY BILL</a></li>
  <li><a class="transaction" href="loanTransaction?accountNumber=<%=request.getParameter("accountNumber")%>">TRANSFER</a></li>
 </ul>