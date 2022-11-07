package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Employee;
import bank.hdfc.pack.BankDefinition;

public class IssueDebitCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.valueOf(request.getParameter("customerId"));
        int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
        int mPin=Integer.valueOf(request.getParameter("mPin"));
        Employee employee=(Employee)request.getSession().getAttribute("employee");
        int messageInt=employee.issueDebitCard(customerId, accountNumber, mPin);
        String message;
        if(messageInt==3) {
        	message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\" >A DEBIT CARD HAS BEEN ISSUED TO THE CUSTOMER ID "+customerId+" TO THE ACCOUNT NUMBER "+accountNumber+"<h2>";
        }
        else {
        	message=BankDefinition.employeeMessage(messageInt);
        }
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("issueDebitCard").forward(request, response);
	}

}
