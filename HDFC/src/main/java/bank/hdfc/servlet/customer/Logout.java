package bank.hdfc.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("customer", "");
		request.getSession().removeAttribute("customer");
		request.getSession().setAttribute("employee", "");
		request.getSession().removeAttribute("employee");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);
		response.sendRedirect("indexPage");
		
	}

}
