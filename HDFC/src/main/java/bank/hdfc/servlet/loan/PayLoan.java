package bank.hdfc.servlet.loan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;


public class PayLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int accountChoose=Integer.valueOf(request.getParameter("accountChoose"));
		int amount=Integer.valueOf(request.getParameter("amount"));
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int messageNumber=0;
		if(customer.getCurrentAccount().getAccountNumber()==accountChoose) {
			messageNumber=customer.getCurrentAccount().debitMoney(Integer.valueOf(request.getParameter("amount")), "LOAN PAYMENT");
		}
		else {
			messageNumber=customer.getSavingAccounts().get(accountChoose).debitMoney(Integer.valueOf(request.getParameter("amount")), "LOAN PAYMENT");
		}
		if(messageNumber==0) {
			customer.getLoanAccounts().get(accountNumber).payLoan(amount);
		}
		response.sendRedirect("payLoan?accountNumber="+accountNumber);
	}

}
