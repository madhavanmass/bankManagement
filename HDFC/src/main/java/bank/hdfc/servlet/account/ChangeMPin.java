package bank.hdfc.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class ChangeMPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean checker;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int oldMPin=Integer.valueOf(request.getParameter("oldMPin")) ;
		int newMPin=Integer.valueOf(request.getParameter("newMPin")) ;
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			checker=customer.getCurrentAccount().getDebitCard().changeMPin(oldMPin, newMPin);
			customer.getCurrentAccount().getDebitCard().setmPin(newMPin);
		}
		else {
			checker=customer.getSavingAccounts().get(accountNumber).getDebitCard().changeMPin(oldMPin, newMPin);
			customer.getSavingAccounts().get(accountNumber).getDebitCard().setmPin(newMPin);
		}
		request.getSession().setAttribute("message", checker);
		response.sendRedirect("debitCard?accountNumber="+accountNumber);
	}

}
