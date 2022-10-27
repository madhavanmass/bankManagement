package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int choice=Integer.valueOf(request.getParameter("oldMPin")) ;
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().getDebitCard().activateOrLock(choice==1?2:1);	
		}
		else {
			customer.getSavingAccounts().get(accountNumber).getDebitCard().activateOrLock(choice==1?2:1);
		}
		response.sendRedirect("debitCard?accountNumber="+accountNumber);
	}

}
