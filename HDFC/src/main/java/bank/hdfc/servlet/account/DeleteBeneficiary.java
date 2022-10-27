package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class DeleteBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int beneficiaryId=Integer.valueOf(request.getParameter("beneficiaryId"));
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		if(customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().deleteBeneficiary(beneficiaryId);
		}
		else {
			customer.getSavingAccounts().get(accountNumber).deleteBeneficiary(beneficiaryId);
		}
		response.sendRedirect("beneficiary?accountNumber="+accountNumber);
	}
}
