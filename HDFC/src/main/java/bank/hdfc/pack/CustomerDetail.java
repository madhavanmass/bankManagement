package bank.hdfc.pack;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerDetail {

	private int customerId;
	private String name;
	private String phoneNumber;
	private String mailId;
	private ConcurrentHashMap<Integer, ArrayList<Object>> accounts = null;  // key: accountnumber value: accounttype,accountbranch,branchName
	
	public CustomerDetail(int customerId, String name, String phoneNumber, String mailId,
			ConcurrentHashMap<Integer, ArrayList<Object>> account) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.mailId = mailId;
		this.accounts = account;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public ConcurrentHashMap<Integer, ArrayList<Object>> getAccounts() {
		return accounts;
	}

	public void setAccounts(ConcurrentHashMap<Integer, ArrayList<Object>> accounts) {
		this.accounts = accounts;
	}

	
	
	

	private String accounts() {
		String account = "";
		for (int accountNumber : accounts.keySet()) {
			account +="<tr><td> ACCOUNT NUMBER ["+accountNumber+"] "+BankDefinition.accountName((Integer)accounts.get(accountNumber).get(0))+" </td><td>"+(String)accounts.get(accountNumber).get(2)+" - branch id : "+(Integer)accounts.get(accountNumber).get(1)+ "</td></tr>";
		}
		return account;
	}

	@Override
	public String toString() {
		String details = 
		"<tr><td style=\"width:50%\" > CUSTOMER ID </td><td>"+customerId+ "</td></tr>"
		+ "<tr><td> NAME </td><td>"+name+ "</td></tr>"
		+ "<tr><td> PHONE NUMBER </td><td>"+phoneNumber+ "</td></tr>"
		+ "<tr><td> MAIL ID </td><td>"+mailId+ "</td></tr>"
		+ accounts()
		;
		return details;
	}
}
