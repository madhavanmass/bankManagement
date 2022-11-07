package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Employee;
import bank.hdfc.function.Manager;
import bank.hdfc.pack.BankDefinition;

public class GiveLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.valueOf(request.getParameter("customerId"));
        int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
        int amount=Integer.valueOf(request.getParameter("amount"));
        int loanType=Integer.valueOf(request.getParameter("loanType"));
        Manager manager=(Manager)request.getSession().getAttribute("employee");
        int messageInt=manager.customerDeposit(customerId, accountNumber, amount,0);
        String message;
        if(messageInt==3) {
        	manager.giveLoan(customerId, amount, accountNumber,loanType);
        	message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS BEEN SUCCESSFULLY GIVE AS A LOAN TO CUSTOMER WITH ID "+customerId+"</h2>";
        }
        else {
        	message=BankDefinition.employeeMessage(messageInt);
        }
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("giveLoan").forward(request, response);
	}

}
