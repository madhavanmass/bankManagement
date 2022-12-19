package bank.hdfc.servlet.deposit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class CancelDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int depositId=Integer.valueOf(request.getParameter("depositId"));
		int amount=0;
		if(customer.getFixedDeposits()!=null && customer.getFixedDeposits().containsKey(depositId)) {
			amount=customer.getFixedDeposits().get(depositId).getDepositAmount();
			customer.getFixedDeposits().get(depositId).cancelDeposit();
		}
		else if(customer.getRecurrsiveDeposits()!=null) {
			amount=customer.getRecurrsiveDeposits().get(depositId).getDepositAmount();
			customer.getRecurrsiveDeposits().get(depositId).cancelDeposit();
		}
		customer.loadCurrentAccounts();
		customer.loadSavingAccount();
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().getBranch().doDeposit(accountNumber, 0, amount, 0, 2, "DEPOSIT MONEY");
		}
		else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
			customer.getSavingAccounts().get(accountNumber).getBranch().doDeposit(accountNumber, 0, amount, 0, 2, "DEPOSIT MONEY");
		}
		
		if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().setBalance(customer.getCurrentAccount().getBalance()+amount);	
		}
		else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)) {
			customer.getSavingAccounts().get(accountNumber).setBalance(amount + customer.getSavingAccounts().get(accountNumber).getBalance());
		}
		customer.setFixedDeposits(null);
		customer.setRecurrsiveDeposits(null);
		response.sendRedirect("deposit");
	}

}
