package bank.hdfc.function;

import java.sql.Date;

public class RecurssiveDeposit extends Deposit {
	private int monthlyDeduction;

	public int getMonthlyDeduction() {
		return monthlyDeduction;
	}

	public void setMonthlyDeduction(int monthlyDeduction) {
		this.monthlyDeduction = monthlyDeduction;
	}

	public RecurssiveDeposit(int depositId, int accountNumber, int amount, Date dateOfCreation, String depositType,
			String policy, Date depositEndDate, float interestRate, int monthlyDeduction) {
		super(depositId, accountNumber, amount, dateOfCreation, depositType, policy, depositEndDate, interestRate);
		this.monthlyDeduction = monthlyDeduction;
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"<br>MONTHLY DEDUCTION : "+monthlyDeduction;
	}
}
