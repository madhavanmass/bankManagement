package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Employee;

public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.valueOf(request.getParameter("customerId"));
        int accountChoice=Integer.valueOf(request.getParameter("accountChoice"));
        int initialDeposit=Integer.valueOf(request.getParameter("amount"));
        Employee employee=(Employee)request.getSession().getAttribute("employee");
        int messageInt=employee.createAccount(customerId, accountChoice, initialDeposit);
        request.getSession().setAttribute("message", messageInt);
        request.getRequestDispatcher("createAccount").forward(request, response);
	}

}
