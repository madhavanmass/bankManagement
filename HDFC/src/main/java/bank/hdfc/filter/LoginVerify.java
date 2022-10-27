package bank.hdfc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class LoginVerify extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Customer customer=(Customer)((HttpServletRequest)request).getSession().getAttribute("customer");
		if(customer!=null) {
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse)response).sendRedirect("customerErrorPage");
		}
	}

}
