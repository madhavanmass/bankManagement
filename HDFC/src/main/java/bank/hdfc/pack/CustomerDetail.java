package bank.hdfc.pack;

import java.util.concurrent.ConcurrentHashMap;

public class CustomerDetail {
	private int customerId;
	private String name;
	private String phoneNumber;
	private String mailId;
	private ConcurrentHashMap<Integer, Integer> accounts = null;

	public CustomerDetail(int customerId, String name, String phoneNumber, String mailId,
			ConcurrentHashMap<Integer, Integer> accounts) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.mailId = mailId;
		this.accounts = accounts;
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

	public ConcurrentHashMap<Integer, Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(ConcurrentHashMap<Integer, Integer> accounts) {
		this.accounts = accounts;
	}

	private String accounts() {
		String account = "";
		for (int accountNumber : accounts.keySet()) {
			account += "\n  -->ACCOUNT NUMBER : " + accountNumber + ", ACCOUNTTYPE : "
					+ BankDefinition.accountName(accounts.get(accountNumber));
		}
		return account;
	}

	@Override
	public String toString() {
		String details = "\nCUSTOMER ID : " + customerId + "\nNAME : " + name + "\nPHONE NUMBER : " + phoneNumber
				+ "\nMAIL ID : " + mailId + accounts();
		return details;
	}
}
