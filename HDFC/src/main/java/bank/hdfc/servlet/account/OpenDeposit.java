package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.pack.BankDefinition;

public class OpenDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int amount=Integer.valueOf(request.getParameter("amount")) ;
		int type=Integer.valueOf(request.getParameter("depositType"));
		int policy=Integer.valueOf(request.getParameter("depositPolicy"));
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		boolean checker=false;
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			
			if(customer.getCurrentAccount().debitMoney(amount, "OPENED A DEPOSIT ")==1) {
				checker=customer.getCurrentAccount().openDeposit(type, amount, policy);
			}
		}
		else {
			
			if(customer.getSavingAccounts().get(accountNumber).debitMoney(amount, "OPENED A DEPOSIT ")==1) {
				checker=customer.getSavingAccounts().get(accountNumber).openDeposit(type, amount, policy);
			}
		}
		customer.setFixedDeposits(null);
		customer.setRecurrsiveDeposits(null);
		String message;
		if(checker) {
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">A NEW "+(type==1?"FIXED":"RECURRING")+" HAS BEEN OPEN PLEASE CHECK ON DEPOSIT TAB<h2>";
		}
		else {
			message="<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">INSUFFICIENT BALANCE PLEASE TYPE IN OTHER ACCOUNT<h2>";
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("openDeposit?accountNumber="+accountNumber);
	}

}
