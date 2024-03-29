package bank.hdfc.function;

import java.sql.Date;
import java.util.HashMap;

import bank.hdfc.dao.AccountDao;
import bank.hdfc.pack.BankDefinition;

public class Account {

	private int accountNumber;
	private int customerId;
	private int branchId;
	private int accountType;
	private Date dateOfCreattion;
	private Branch branch = null;
	private HashMap<Integer, FixedDeposit> fixedDeposit = null;
	private HashMap<Integer, RecurssiveDeposit> recurssiveDeposit = null;
	private HashMap<Integer, Beneficiary> beneficiary = null;
	private HashMap<Integer, Transaction> transactions = null;
	private DebitCard debitCard = null;
	protected AccountDao accountDao = new AccountDao();

	public Account(int accountNumber, int customerId, int branchId, int accountType, Date dateOfCreattion) {

		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.branchId = branchId;
		this.accountType = accountType;
		this.dateOfCreattion = dateOfCreattion;
		branch = accountDao.getBranch(branchId);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public Date getDateOfCreattion() {
		return dateOfCreattion;
	}

	public void setDateOfCreattion(Date dateOfCreattion) {
		this.dateOfCreattion = dateOfCreattion;
	}

	public HashMap<Integer, FixedDeposit> getFixedDeposit() {
		return fixedDeposit;
	}

	public void setFixedDeposit(HashMap<Integer, FixedDeposit> fixedDeposit) {
		this.fixedDeposit = fixedDeposit;
	}

	public HashMap<Integer, RecurssiveDeposit> getRecurssiveDeposit() {
		return recurssiveDeposit;
	}

	public void setRecurssiveDeposit(HashMap<Integer, RecurssiveDeposit> recurssiveDeposit) {
		this.recurssiveDeposit = recurssiveDeposit;
	}

	public HashMap<Integer, Beneficiary> getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(HashMap<Integer, Beneficiary> beneficiary) {
		this.beneficiary = beneficiary;
	}

	public HashMap<Integer, Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(HashMap<Integer, Transaction> transactions) {
		this.transactions = transactions;
	}

	public DebitCard getDebitCard() {
		return debitCard;
	}

	public void setDebitCard(DebitCard debitCard) {
		this.debitCard = debitCard;
	}
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

	@Override
	public String toString() {
		
		String details =branch.toString()+
				"<tr><td> ACCOUNT NUMBER </td><td>"+accountNumber+ "</td></tr>"
				+ "<tr><td> ACCOUNT TYPE </td><td>"+BankDefinition.accountName(accountType)+ "</td></tr>"
				+ "<tr><td> DATE OF CREATION </td><td>"+dateOfCreattion+ "</td></tr>";
		return details;
	}
	
	

	// debit amount from account
	protected int debitMoney(int amount,int transferedAmount, String description) {
		accountDao.updateAccount(accountNumber, amount,transferedAmount,getAccountType() == 1 ? "updateSavingAccount" : "updateCurrentAccount");
		int otherAccount=1;
		accountDao.transactionEntry(getAccountNumber(), otherAccount, description, amount, 2, 1);
		transactions = null;
		return 1;
	}

	// transfer money from bank
	protected int transfer(int otherAccount, int amount,int transferedAmount, String description) {
		loadBenificary();
		boolean check = true;
		int resultInt = 1;
		int beneficiaryId = isBeneficiary(otherAccount);
		if (beneficiaryId != 0) {
			check = beneficiary.get(beneficiaryId).limitChecker(transferedAmount);
			if(check) {
				beneficiary.get(beneficiaryId).updateTransfer(transferedAmount);
				beneficiary.get(beneficiaryId).setTransferAmount(transferedAmount+beneficiary.get(beneficiaryId).getTransferAmount());
			}
		}
		if (check) {
			accountDao.updateAccount(accountNumber, amount , transferedAmount, getAccountType() == 1 ? "updateSavingAccount" : "updateCurrentAccount");
			int checker=branch.doDeposit(otherAccount,accountNumber,-amount,0,amount>0?1:2,description);
			if(checker==0) {
				resultInt=3;
			}
			transactions = null;
		} else {
			resultInt = 2;
		}

		return resultInt;

	}

	private int isBeneficiary(int otherAccount) {
		for (Beneficiary beneficiaryDetail : beneficiary.values()) {
			if (beneficiaryDetail.getConnectedAccount() == otherAccount) {
				return beneficiaryDetail.getBeneficiaryId();
			}
		}
		return 0;
	}

	// adding a new Beneficiary account
	public int addBeneficiary(int otherAccountNumber, int transactionLimit,String accountHolderName,int bankName,String IFSCCode) {
		if (isBeneficiary(otherAccountNumber) == 0) {
			accountDao.addBeneficiary(accountNumber, otherAccountNumber, transactionLimit,accountHolderName,bankName,IFSCCode);
			beneficiary = null;
			return 9;
		} else {
			return 8;
		}
	}

	public boolean deleteBeneficiary(int beneficiaryId) {
		beneficiary=null;
		return accountDao.deleteBeneficiary(beneficiaryId);
		
	}

	// Function to load transaction history
	public void loadTransactions(int balance) {
		transactions = null;
		transactions = accountDao.transactionHistory(accountNumber, balance);
	}

	// Function to load beneficiary, transactions, card details
	public void loadBenificary() {
		if (beneficiary == null) {
			beneficiary = accountDao.getBeneficiaryAccount(accountNumber);
		}
	}

	// Function to load card
	public void loadDebitCard() {
		if (debitCard == null) {
			debitCard = accountDao.getDebitCard(accountNumber);
		}
	}

	// Open Deposit in this account
	public boolean openDeposit(int type, int amount, int policy) {
		boolean resultbool=false;
		if (type == 1) {
			resultbool=accountDao.openFixedDeposit(accountNumber, amount, type, policy,BankDefinition.getDepositTime(policy));
			fixedDeposit = null;
		} else {
			resultbool=accountDao.openRecurrsiveDeposit(accountNumber, amount, type, policy,BankDefinition.getDepositTime(policy));
			recurssiveDeposit = null;
		}
		return resultbool;
	}

}
