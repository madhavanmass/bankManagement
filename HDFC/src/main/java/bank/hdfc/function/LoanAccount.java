package bank.hdfc.function;

import java.sql.Date;

import bank.hdfc.dao.LoanDao;

public class LoanAccount extends Account {
	private int loanId;
	private int totalAmount;
	private String loanType;
	private int supportAccount;
	private Date loanEndDate;
	private float interestRate;
	private int balance;
	private int amountPaid;
	private LoanDao loanDao = new LoanDao();

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getSupportAccount() {
		return supportAccount;
	}

	public void setSupportAccount(int supportAccount) {
		this.supportAccount = supportAccount;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LoanAccount(int accountNumber, int customerId, int branchId, int accountType, Date dateOfCreattion,
			int loanId, int totalAmount, String loanType, int supportAccount, Date loanEndDate, float interestRate,
			int balance,int amountPaid) {
		super(accountNumber, customerId, branchId, accountType, dateOfCreattion);
		this.loanId = loanId;
		this.totalAmount = totalAmount;
		this.loanType = loanType;
		this.supportAccount = supportAccount;
		this.loanEndDate = loanEndDate;
		this.interestRate = interestRate;
		this.balance = balance;
		this.amountPaid=amountPaid;
	}

	@Override
	public String toString() {
		return super.toString()+
				"<tr><td> LOAN TYPE </td><td>"+loanType+ "</td></tr>"+
				"<tr><td> TOTAL LOAN AMOUNT </td><td>"+totalAmount+ "</td></tr>"+
				"<tr><td> LOAN END DATE </td><td>"+loanEndDate.toString()+ "</td></tr>"+
				"<tr><td> LOAN BALANCE </td><td>"+balance+ "</td></tr>"+
				"<tr><td> AMOUNT PAID </td><td>"+amountPaid+ "</td></tr>"+
				"<tr><td> LOAN INTEREST RATE </td><td>"+interestRate+ "</td></tr>"
				;
	}

	private int check(int amount) {
		if (balance - amount < 0 && totalAmount - amountPaid <= 0 && balance==0) {
			return 1;
		} else {
			return 0;
		}
	}

	public void payLoan(int amount) {
		
		if (check(amount) == 0 ) {
			balance -= amount;
			amountPaid +=amount;
			loanDao.payLoan(amount, loanId);
		}
	}

	public void closeLoanAccount() {
		loanDao.closeLoanAccount(loanId);
	}

}
