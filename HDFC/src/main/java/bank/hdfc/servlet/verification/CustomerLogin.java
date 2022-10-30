package bank.hdfc.servlet.verification;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.function.Verification;

public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Verification verification=new Verification();
		request.getSession().invalidate();
		int Id = Integer.valueOf(request.getParameter("customerId"));
		String password = request.getParameter("password");
		Customer customer = verification.verifyCustomer(Id, password);
		if (customer != null) {
			request.getSession().setAttribute("customer", customer);
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("homePage");
			requestDispatcher.forward(request, response);
			
		} else {
			request.getSession().setAttribute("message", "INVALID CREDENTIALS");
			RequestDispatcher requestuestDispatcher = request.getRequestDispatcher("indexPage");
			requestuestDispatcher.include(request, response);
		}
	
	}
}
