package bank.hdfc.function;

import java.sql.Date;
import bank.hdfc.pack.BankDefinition;


public class Transaction {
	private int transactionId;
	private int accountNumber;
	private int transferAccountNumber;
	private String description;
	private Date date;
	private String time;
	private int action;
	private int amount; 
	private int remainingBalance;
	private String  transactionMode;
	
	
	
	public Transaction(int transactionId, int accountNumber, int transferAccountNumber, String description, Date date,
			String time, int action, int amount, int remainingBalance, String transactionMode) {
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.transferAccountNumber = transferAccountNumber;
		this.description = description;
		this.date = date;
		this.time = time;
		this.action = action;
		this.amount = amount;
		this.remainingBalance = remainingBalance;
		this.transactionMode = transactionMode;
	}



	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getTransferAccountNumber() {
		return transferAccountNumber;
	}
	public void setTransferAccountNumber(int transferAccountNumber) {
		this.transferAccountNumber = transferAccountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(int remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	@Override
	public String toString(){
        return BankDefinition.actionType(action)+remainingBalance+(transferAccountNumber==0?"NIL":String.valueOf(transferAccountNumber))+description;
    }
	
	
	
}
