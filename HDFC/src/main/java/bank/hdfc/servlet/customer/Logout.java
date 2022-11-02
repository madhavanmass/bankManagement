package bank.hdfc.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect="indexPage";
		HttpSession session=request.getSession();
		if(session.getAttribute("admin")!=null || session.getAttribute("employee")!=null) {
			redirect="employeeLogin";
		}
		
		request.getSession().setAttribute("customer", "");
		request.getSession().removeAttribute("customer");
		request.getSession().setAttribute("employee", "");
		request.getSession().removeAttribute("employee");
		request.getSession().setAttribute("admin", "");
		request.getSession().removeAttribute("admin");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);
		request.getSession().invalidate();
		response.sendRedirect(redirect);
	}

}
