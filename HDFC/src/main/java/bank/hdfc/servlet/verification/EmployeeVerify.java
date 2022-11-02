package bank.hdfc.servlet.verification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Admin;
import bank.hdfc.function.Employee;
import bank.hdfc.function.Manager;
import bank.hdfc.function.Verification;

public class EmployeeVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Verification verification=new Verification();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		int userId=Integer.valueOf(request.getParameter("userId"));
		String password=request.getParameter("password");
		int role=Integer.valueOf(request.getParameter("role"));
		switch (role) {
		case 1:
			Employee employee = (Employee) verification.verifyEmployee(userId, password, role);
			if (employee != null) {
				request.getSession().setAttribute("employee", employee);
				request.getRequestDispatcher("profilePage").forward(request, response);
				
			} else {
				request.getSession().setAttribute("message", "<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">INVALID CREDENTIALS</h2>");
				request.getRequestDispatcher("employeeLogin").forward(request, response);
			}
			break;
		case 2:
			Manager manager = verification.verifyEmployee(userId, password, role);
			if (manager != null) {
				request.getSession().setAttribute("employee", manager);
				request.getRequestDispatcher("profilePage").forward(request, response);
				
			} else {
				request.getSession().setAttribute("message", "<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">INVALID CREDENTIALS</h2>");
				request.getRequestDispatcher("employeeLogin").forward(request, response);
			}
			
			break;
		case 3:
			Admin admin = verification.verifyAdmin(userId, password);
			if (admin != null) {
				request.getSession().setAttribute("admin", admin);
				request.getRequestDispatcher("profilePage").forward(request, response);
			} else {
				request.getSession().setAttribute("message", "<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">INVALID CREDENTIALS</h2>");
				request.getRequestDispatcher("employeeLogin").forward(request, response);
				
			}
			break;
		}
	}
}
