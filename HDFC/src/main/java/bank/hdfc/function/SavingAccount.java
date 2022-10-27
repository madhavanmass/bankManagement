package bank.hdfc.function;

import java.sql.Date;

public class SavingAccount extends Account {
	private int savingAccountId;
	private float interestRate;
	private int transferLimit;
	private int transferredAmount;
	private int balance;
	private Date lastDateOfUpdate;

	public SavingAccount(int accountNumber, int customerId, int branchId, int accountType, Date dateOfCreattion,
			int savingAccountId, float interestRate, int transferLimit, int transferredAmount,
			int balance, Date lastDateOfUpdate) {
		super(accountNumber, customerId, branchId, accountType, dateOfCreattion);
		this.savingAccountId = savingAccountId;
		this.interestRate = interestRate;
		this.transferLimit = transferLimit;
		this.transferredAmount = transferredAmount;
		this.balance = balance;
		this.lastDateOfUpdate = lastDateOfUpdate;
	}
	public int getSavingAccountId() {
		return savingAccountId;
	}
	public void setSavingAccountId(int savingAccountId) {
		this.savingAccountId = savingAccountId;
	}
	
	
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public int getTransferLimit() {
		return transferLimit;
	}
	public void setTransferLimit(int transferLimit) {
		this.transferLimit = transferLimit;
	}
	public int getTransferredAmount() {
		return transferredAmount;
	}
	public void setTransferredAmount(int transferredAmount) {
		this.transferredAmount = transferredAmount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Date getLastDateOfUpdate() {
		return lastDateOfUpdate;
	}
	public void setLastDateOfUpdate(Date lastDateOfUpdate) {
		this.lastDateOfUpdate = lastDateOfUpdate;
	}
	
	@Override
	public String toString() {
		return "<br>INTEREST RATE : "+interestRate
				+"<br>TRANSFER LIMIT : "+transferLimit
				+"<br>TRANFERRED AMOUNT : "+transferredAmount
				+"BALANCE : "+balance + super.toString();
	}
	
	private int check(int amount) {
		if(balance-amount < 0) {
			return 1;
		}
		else if(transferredAmount+amount > transferLimit) {
			return 2;
		}
		else {
			return 0;
		}
	}
	public int debitMoney(int amount, String description) {
		int resultInt=check(amount);
		balance-=amount;
		if(resultInt==0) {
			return super.debitMoney(amount, description);
		}
		else {
			return resultInt;
		}
	}
	
	public int transfer(int otherAccount, int amount, String description) {
		int resultInt=check(amount);
		balance-=amount;
		if(resultInt==0) {
			return super.transfer(otherAccount,amount, description);
		}
		else {
			return resultInt;
		}
	}
	
	
	

}
