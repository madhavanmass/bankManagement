package bank.hdfc.function;

import java.sql.Date;

public class FixedDeposit extends Deposit {

	public FixedDeposit(int depositId, int accountNumber, int amount, Date dateOfCreation, String depositType,
			String policy, Date depositEndDate, float interestRate) {
		super(depositId, accountNumber, amount, dateOfCreation, depositType, policy, depositEndDate, interestRate);
	}

}
