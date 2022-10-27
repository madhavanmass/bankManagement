package bank.hdfc.servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int messageNumber=0;
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			messageNumber=customer.getCurrentAccount().transfer(Integer.valueOf(request.getParameter("otherAccount")),Integer.valueOf(request.getParameter("amount")), request.getParameter("description"));	
		}
		else {
			messageNumber=customer.getSavingAccounts().get(accountNumber).transfer(Integer.valueOf(request.getParameter("otherAccount")),Integer.valueOf(request.getParameter("amount")), request.getParameter("description"));
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("transfer?accountNumber="+accountNumber);
		request.setAttribute("message", messageNumber);
		requestDispatcher.forward(request, response);
	}

}
