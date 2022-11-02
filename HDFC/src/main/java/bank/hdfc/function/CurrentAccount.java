package bank.hdfc.function;

import java.sql.Date;


public class CurrentAccount extends Account {
	private int currentAccountId;
	private int userSetLimit;
	private int transferredAmount;
	private int balance;

	public CurrentAccount(int accountNumber, int customerId, int branchId, int accountType, Date dateOfCreattion,
			 int currentAccountId, int userSetLimit, int transferredAmount, int balance) {
		super(accountNumber, customerId, branchId, accountType, dateOfCreattion);
		this.currentAccountId = currentAccountId;
		this.userSetLimit = userSetLimit;
		this.transferredAmount = transferredAmount;
		this.balance = balance;
	}

	public int getCurrentAccountId() {
		return currentAccountId;
	}

	public void setCurrentAccountId(int currentAccountId) {
		this.currentAccountId = currentAccountId;
	}

	public int getUserSetLimit() {
		return userSetLimit;
	}

	public void setUserSetLimit(int userSetLimit) {
		this.userSetLimit = userSetLimit;
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

	@Override
	public String toString() {
		return super.toString()
				+ "<tr><td> BALANCE </td><td>"+balance+ "</td></tr>"
				+"<tr><td> USER SET LIMIT </td><td>"+userSetLimit+ "</td></tr>"
				+ "<tr><td> TRANSFER AMOUNT </td><td>"+transferredAmount+ "</td></tr>"
				;
	}
	
	
	public boolean resetUserLimt(int userLimit) {
		return accountDao.changeUserSetLimit(getAccountNumber(),userLimit);
	}
	private int check(int amount) {
		if(balance-amount < 0) {
			return 4;
		}
		else if(transferredAmount+amount > userSetLimit) {
			return 5;
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
