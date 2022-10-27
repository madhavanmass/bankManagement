package bank.hdfc.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int age=0;
		if(request.getParameter("remember")!=null) {
			age=1800;
		}
		if(request.getSession().getAttribute("customer")!=null) {
			Customer customer=(Customer) request.getSession().getAttribute("customer");
			Cookie customerId=new Cookie("customerId",age==0?"":String.valueOf(customer.getCustomerId()));
			customerId.setMaxAge(0);
			Cookie customerPassword=new Cookie("customerPassword",age==0?"":customer.getPassword());
			customerPassword.setMaxAge(0);
			response.addCookie(customerId);
			response.addCookie(customerPassword);
			request.getSession().removeAttribute("customer");
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);
		response.sendRedirect("indexPage");
		
	}

}
