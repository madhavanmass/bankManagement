package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Employee;
import bank.hdfc.pack.BankDefinition;

public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.valueOf(request.getParameter("customerId"));
        int accountChoice=Integer.valueOf(request.getParameter("accountChoice"));
        int initialDeposit=Integer.valueOf(request.getParameter("initialDeposit"));
        Employee employee=(Employee)request.getSession().getAttribute("employee");
        int messageInt=employee.createAccount(customerId, accountChoice, initialDeposit);
        String message;
        System.out.println(messageInt);
        if(messageInt<5) {
        	message=BankDefinition.employeeMessage(messageInt);
        }
        else {
        	message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">AN "+BankDefinition.accountName(accountChoice)+ " : "+messageInt+" HAS BEEN CREATE FOR THE CUSTOMER WITH ID "+customerId+" IN BRANCH ID "+employee.getBranchId()+"</h2>";
        }
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("createAccount").forward(request, response);
	}

}
