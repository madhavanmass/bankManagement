package bank.hdfc.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.pack.BankDefinition;

public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int otherAccount=Integer.valueOf(request.getParameter("otherAccount"));
		int amount=Integer.valueOf(request.getParameter("amount"));
		String description= request.getParameter("description");
		int transferedAmount=amount;
		int messageNumber=0;
		if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			messageNumber=customer.getCurrentAccount().transfer(otherAccount,-amount, transferedAmount ,description);	
		}
		else if(customer.getSavingAccounts().size()!=0) {
			messageNumber=customer.getSavingAccounts().get(accountNumber).transfer(otherAccount,-amount , transferedAmount , description);
		}
		
		String message;
		if(messageNumber==1) {
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS SUCCESSFULLY PAID</h2>";
		}
		else {
			message=BankDefinition.accountMessage(messageNumber);
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("transfer?accountNumber="+accountNumber);
	}

}
