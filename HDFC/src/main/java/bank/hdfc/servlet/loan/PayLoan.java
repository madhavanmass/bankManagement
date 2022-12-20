package bank.hdfc.servlet.loan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.pack.BankDefinition;


public class PayLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int accountChoose=Integer.valueOf(request.getParameter("accountChoose"));
		int amount=Integer.valueOf(request.getParameter("amount"));
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int messageNumber=0;
//		customer.setLoanAccounts(null);
//		customer.loadLoanAccount();
		if(customer.getCurrentAccount().getAccountNumber()==accountChoose) {
			messageNumber=customer.getCurrentAccount().transfer(accountNumber, -amount,0, "LOAN PAYMENT");
		}
		else {
			messageNumber=customer.getSavingAccounts().get(accountChoose).transfer(accountNumber, -amount, 0, "LOAN PAYMENT");
		}
		String message;
		if(messageNumber==1) {
			customer.getLoanAccounts().get(accountNumber).payLoan(amount);
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS SUCCESSFULLY PAID "+"</h2>";
		}
		else {
			message=BankDefinition.accountMessage(messageNumber);
		}
		
		if(customer.getLoanAccounts().get(accountNumber).getBalance()<=0 || customer.getLoanAccounts().get(accountNumber).getTotalAmount()-customer.getLoanAccounts().get(accountNumber).getAmountPaid() <=0) {
			customer.getLoanAccounts().get(accountNumber).closeLoanAccount();
			customer.getLoanAccounts().remove(customer.getLoanAccounts().get(accountNumber).getLoanId());
			request.getSession().setAttribute("message","<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE LOAN HAS BEEN PAID SUCCESSFULLY.. THE ACCOUNT "+accountNumber+ " HAS BEEN CLOSED</h2>");
			response.sendRedirect("loan");
		}
		else {
			request.getSession().setAttribute("message", message);
			response.sendRedirect("payLoan?accountNumber="+accountNumber);
		}
	}

}
