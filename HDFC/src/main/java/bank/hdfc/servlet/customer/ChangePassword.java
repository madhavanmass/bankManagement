package bank.hdfc.servlet.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.hdfc.function.Customer;
import bank.hdfc.function.Employee;

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		HttpSession session= request.getSession();
		boolean check=false;
		if(session.getAttribute("customer")!=null) {
			check=((Customer)session.getAttribute("customer")).changePassword(oldPassword, newPassword);
		}
		else if(session.getAttribute("emplyee")!=null){
			check=((Employee)session.getAttribute("employee")).changePassword(oldPassword, newPassword);
		}
		request.getSession().setAttribute("message", check?"YOUR PASSWORD HAS BEEN CHANGES SUCCESSFULLY":"PLEASE RECHECK THE PASSWORD YOPU HAVE ENTERED");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("changePasswordPage");
		requestDispatcher.forward(request, response);
	}
}
