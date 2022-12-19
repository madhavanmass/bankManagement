package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class AddBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int connectAccountNumber=Integer.valueOf(request.getParameter("connectedAccount"));
		int transactionLimit=Integer.valueOf(request.getParameter("transactionLimit"));
		
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().addBeneficiary(connectAccountNumber, transactionLimit);
		}
		else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
			customer.getSavingAccounts().get(accountNumber).addBeneficiary(connectAccountNumber, transactionLimit);
		}
		response.sendRedirect("beneficiary?accountNumber="+accountNumber);
	}

}
