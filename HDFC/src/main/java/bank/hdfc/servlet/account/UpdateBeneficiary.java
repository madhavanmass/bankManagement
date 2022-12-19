package bank.hdfc.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;

public class UpdateBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int beneficiaryId=Integer.valueOf(request.getParameter("beneficiaryId"));
		int transactionLimit=Integer.valueOf(request.getParameter("transactionLimit"));
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		if(customer.getCurrentAccount() !=null && customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			customer.getCurrentAccount().getBeneficiary().get(beneficiaryId).changeBeneficiaryAmount(transactionLimit);
			customer.getCurrentAccount().setBeneficiary(null);
		}
		else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
			customer.getSavingAccounts().get(accountNumber).getBeneficiary().get(beneficiaryId).changeBeneficiaryAmount(transactionLimit);
			customer.getSavingAccounts().get(accountNumber).setBeneficiary(null);
		}
		response.sendRedirect("beneficiary?accountNumber="+accountNumber);
	}

}
