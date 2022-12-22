package bank.hdfc.servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.pack.BankDefinition;

public class PayBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		customer.loadCurrentAccounts();
		customer.loadSavingAccount();
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int amount=Integer.valueOf(request.getParameter("amount"));
		String description=request.getParameter("description");
		String billType=request.getParameter("billType");
		
		String fullDescription="["+billType+"] <br> description : "+description;
		int messageNumber=0;
		if(customer.getCurrentAccount() !=null && customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			messageNumber=customer.getCurrentAccount().debitMoney(-amount,amount,fullDescription);	
		}
		else if( customer.getSavingAccounts() !=null && customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
			messageNumber=customer.getSavingAccounts().get(accountNumber).debitMoney(-amount,amount ,fullDescription);
		}
		String message;
		if(messageNumber==1) {
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS SUCCESSFULLY PAID</h2>";
		}
		else {
			message=BankDefinition.accountMessage(messageNumber);
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("payBill?accountNumber="+accountNumber);
	
	}

}
