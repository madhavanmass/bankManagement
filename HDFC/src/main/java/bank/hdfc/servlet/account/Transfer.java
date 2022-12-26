package bank.hdfc.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Customer;
import bank.hdfc.pack.BankDefinition;

public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("customer");
		int accountNumber=Integer.valueOf(request.getParameter("accountNumber"));
		int otherAccount=Integer.valueOf(request.getParameter("otherAccount"));
		int amount=Integer.valueOf(request.getParameter("amount"));
		String userDescription= request.getParameter("description");
		
		String accountHolderName=request.getParameter("accountHolderName");
		int bankName=Integer.valueOf(request.getParameter("bankName"));
		String IFSCCode=request.getParameter("ifsccode");
		String mode=request.getParameter("mode");
		String description="Receiver : "+accountHolderName +"["+BankDefinition.getBankName(bankName)+"]<br> mode : "+mode+"<br>Remarks : "+userDescription;
		int transferedAmount=amount;
		int messageNumber=0;
		String message="";
		if(otherAccount==accountNumber ) {
			message="<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">SAME ACCOUNT NUMBER AND TRANSFER ACCOUNT</h2>";
		}
		
		else if( customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==accountNumber) {
			messageNumber=customer.getCurrentAccount().transfer(otherAccount,-amount, transferedAmount ,description);	
		}
		else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(accountNumber)){
			messageNumber=customer.getSavingAccounts().get(accountNumber).transfer(otherAccount,-amount , transferedAmount , description);
		}
		if(messageNumber==1) {
			if(customer.getCurrentAccount()!=null && customer.getCurrentAccount().getAccountNumber()==otherAccount) {
				customer.getCurrentAccount().setBalance(customer.getCurrentAccount().getBalance()+amount);	
			}
			else if(customer.getSavingAccounts().size()!=0 && customer.getSavingAccounts().containsKey(otherAccount)) {
				customer.getSavingAccounts().get(otherAccount).setBalance(amount + customer.getSavingAccounts().get(otherAccount).getBalance());
			}
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">THE AMOUNT "+amount+" HAS SUCCESSFULLY TRANSFER TO "+otherAccount+"</h2>";
		}
		else if(messageNumber!=0){
			message=BankDefinition.accountMessage(messageNumber);
		}
		if(bankName!=1) {
			message="<br><h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">THE CHOOSEN BANK TRANSFER WILL BE PROCESSED ..<br>THE MONEY WILL BE TRANSFERRED FROM YOUR ACCOUNT</h2>";
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("transfer?accountNumber="+accountNumber);
	}

}
