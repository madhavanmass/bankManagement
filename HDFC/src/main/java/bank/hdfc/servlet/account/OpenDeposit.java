package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class OpenDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int amount=Integer.valueOf(request.getParameter("amount")) ;
		int type=Integer.valueOf(request.getParameter("depositType"));
		int policy=Integer.valueOf(request.getParameter("depositPolicy"));
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().debitMoney(amount, "OPENED A DEPOSIT ");	
			customer.getCurrentAccount().openDeposit(type, amount, policy);
		}
		else {
			customer.getSavingAccounts().get(accountNumber).debitMoney(amount, "OPENED A DEPOSIT ");
			customer.getSavingAccounts().get(accountNumber).openDeposit(type, amount, policy);
		}
		response.sendRedirect("openDeposit?accountNumber="+accountNumber);
	}

}
