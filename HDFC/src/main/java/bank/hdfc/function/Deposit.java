package bank.hdfc.function;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.text.SimpleDateFormat;  
import bank.hdfc.dao.DepositDao;

public class Deposit {
	private int depositId;
	private int accountNumber;
	private int amount;
	private Date dateOfCreation;
	private String depositType;
	private String policy;
	private Date depositEndDate;
	private float interestRate;

	private DepositDao depositDao = new DepositDao();

	public int getDepositId() {
		return depositId;
	}

	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Date getDepositEndDate() {
		return depositEndDate;
	}

	public void setDepositEndDate(Date depositEndDate) {
		this.depositEndDate = depositEndDate;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public Deposit(int depositId, int accountNumber, int amount, Date dateOfCreation, String depositType, String policy,
			Date depositEndDate, float interestRate) {
		this.depositId = depositId;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.dateOfCreation = dateOfCreation;
		this.depositType = depositType;
		this.policy = policy;
		this.depositEndDate = depositEndDate;
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		String details = "<br>DEPOSIT ID : " + depositId + "<br>AMOUNT : " + amount + "<br>DEPOSIT TYPE : "
				+ depositType + "<br>DATE OF CREATION : " + dateOfCreation + "<br>POLICY : " + policy
				+ "<br>INTEREST RATE : " + interestRate;
		return details;
	}
	
	public boolean cancelDeposit() {
		return depositDao.cancelDeposit(depositId);
	}
	
	public int getDepositAmount() {
		float amount=(float)this.amount;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Period difference=Period.between(LocalDate.parse(formatter.format(dateOfCreation)), LocalDate.now());
		amount=difference.getMonths()==0?amount:(amount+(difference.getMonths()*amount*interestRate));
		return (int)amount;
	
	}
	
	public int getTimeDifferenceInMonths(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Period difference=Period.between(LocalDate.parse(formatter.format(dateOfCreation)),LocalDate.parse(formatter.format(depositEndDate)));
		return difference.getMonths();
	}
}
