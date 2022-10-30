package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Employee;
import bank.hdfc.pack.BankDefinition;


public class DepositMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.valueOf(request.getParameter("customerId"));
        int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
        int amount=Integer.valueOf(request.getParameter("amount"));
        Employee employee=(Employee)request.getSession().getAttribute("employee");
        int messageInt=employee.customerDeposit(customerId, accountNumber, amount);
        String message;
        if(messageInt==3) {
        	message="THE AMOUNT "+amount+" HAS BEEN SUCCESSFULLY DEPOSITED TO YOUR ACCOUNT "+accountNumber;
        }
        else {
        	message=BankDefinition.employeeMessage(messageInt);
        }
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("depositMoney").forward(request, response);
	}

}
