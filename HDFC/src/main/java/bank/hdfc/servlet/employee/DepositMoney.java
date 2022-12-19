package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
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
        if(employee==null) {
        	System.out.println("lsjnfioasdnoano");
        }
        int messageInt=employee.customerDeposit(customerId, accountNumber, amount,0,"BANK DEPOSIT");
        String message;
        if(messageInt==3) {
        	message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS BEEN SUCCESSFULLY DEPOSITED TO YOUR ACCOUNT "+accountNumber+"</h2>";
        }
        else {
        	message=BankDefinition.employeeMessage(messageInt);
        }
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("depositMoney").forward(request, response);
	}

}
