package bank.hdfc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import bank.hdfc.function.Customer;
import bank.hdfc.function.Verification;

public class LoginFilter extends HttpFilter implements Filter  {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Verification verification=new Verification();
		int Id = Integer.valueOf(req.getParameter("customerId"));
		String password = req.getParameter("password");
		Customer customer = verification.verifyCustomer(Id, password);
		if (customer != null) {
			((HttpServletRequest)req).getSession().setAttribute("customer", customer);
			chain.doFilter(req, res);
		} else {
			((HttpServletRequest)req).getSession().setAttribute("message", "INVALID CREDENTIALS");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("indexPage");
			requestDispatcher.include(req, res);
		}
		
	}

}
