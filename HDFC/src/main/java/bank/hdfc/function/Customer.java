package bank.hdfc.function;

import java.util.HashMap;

import bank.hdfc.dao.CustomerDao;

public class Customer extends Person {

	private int customerId;
	private int profileId;
	private CurrentAccount currentAccount = null;
	private HashMap<Integer, SavingAccount> savingAccounts = null;
	private HashMap<Integer, LoanAccount> loanAccounts = null;
	private HashMap<Integer, FixedDeposit> fixedDeposits = null;
	private HashMap<Integer, RecurssiveDeposit> recurrsiveDeposits = null;

	public Customer(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber,
			String password, String dateOfBirth, String mailId, int customerId, String addressLine1,
			String addressLine2, String pinCode) {
		super(name, profileId, phoneNumber, aadharNumber, panNumber, password, dateOfBirth, mailId, addressLine1,
				addressLine2, pinCode);
		this.customerId = customerId;
		this.profileId = profileId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public HashMap<Integer, SavingAccount> getSavingAccounts() {
		return savingAccounts;
	}

	public void setSavingAccounts(HashMap<Integer, SavingAccount> savingAccounts) {
		this.savingAccounts = savingAccounts;
	}

	public HashMap<Integer, LoanAccount> getLoanAccounts() {
		return loanAccounts;
	}

	public void setLoanAccounts(HashMap<Integer, LoanAccount> loanAccounts) {
		this.loanAccounts = loanAccounts;
	}
	
	public HashMap<Integer, FixedDeposit> getFixedDeposits() {
		return fixedDeposits;
	}

	public void setFixedDeposits(HashMap<Integer, FixedDeposit> fixedDeposits) {
		this.fixedDeposits = fixedDeposits;
	}

	public HashMap<Integer, RecurssiveDeposit> getRecurrsiveDeposits() {
		return recurrsiveDeposits;
	}

	public void setRecurrsiveDeposits(HashMap<Integer, RecurssiveDeposit> recurrsiveDeposits) {
		this.recurrsiveDeposits = recurrsiveDeposits;
	}

	@Override
	public String toString() {
		return "<tr><td> CUSTOMER ID </td><td>"+customerId+ "</td></tr>" 
				+super.toString();
	}

	private CustomerDao customerDao = new CustomerDao();

	public void loadCurrentAccounts() {
		if (currentAccount == null)
			currentAccount = customerDao.getCurrentAccount(customerId);
	}

	public void loadSavingAccount() {
		if (savingAccounts == null) {
			savingAccounts = customerDao.getSavingAccounts(customerId);
		}
	}
	
	public void loadLoanAccount() {
		if(loanAccounts==null) {
			loanAccounts=customerDao.getLoanAccounts(customerId);
			
		}
	}
	
	public void loadFixedDeposit() {
		if(fixedDeposits==null) {
			fixedDeposits=customerDao.getFixedDeposits(customerId);
		}
	}
	
	public void loadRecurrsiveDeposit() {
		if(recurrsiveDeposits==null) {
			recurrsiveDeposits=customerDao.getRecurringDeposits(customerId);
		}
	}

	

}
