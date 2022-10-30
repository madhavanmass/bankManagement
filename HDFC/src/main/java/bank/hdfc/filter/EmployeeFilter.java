package bank.hdfc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Admin;
import bank.hdfc.function.Employee;

public class EmployeeFilter extends HttpFilter implements Filter {
       
	
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Employee employee=(Employee)((HttpServletRequest)request).getSession().getAttribute("employee");
		if(employee!=null) {
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse)response).sendRedirect("employeeErrorPage");
		}
	}


}
