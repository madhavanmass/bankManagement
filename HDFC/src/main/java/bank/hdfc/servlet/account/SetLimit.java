package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;


public class SetLimit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int userSetLimit=Integer.valueOf(request.getParameter("userSetLimit"));
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		customer.getCurrentAccount().resetUserLimt(userSetLimit);
		customer.getCurrentAccount().setUserSetLimit(userSetLimit);
		request.getRequestDispatcher("accountDetail?accountNumber="+accountNumber).forward(request, response);
	}

}
