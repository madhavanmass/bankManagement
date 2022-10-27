package bank.hdfc.function;

import bank.hdfc.dao.BeneficiaryDao;

public class Beneficiary {
	private int beneficiaryId;
	private int accountNumber;
	private int connectedAccount;
	private int transactionLimit;
	private int transferAmount;

	private BeneficiaryDao beneficiaryDao = new BeneficiaryDao();

	public Beneficiary(int beneficiaryId, int accountNumber, int connectedAccount, int transactionLimit,
			int transferAmount) {

		this.beneficiaryId = beneficiaryId;
		this.accountNumber = accountNumber;
		this.connectedAccount = connectedAccount;
		this.transactionLimit = transactionLimit;
		this.transferAmount = transferAmount;
	}

	public int getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getConnectedAccount() {
		return connectedAccount;
	}

	public void setConnectedAccount(int connectedAccount) {
		this.connectedAccount = connectedAccount;
	}

	public int getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(int transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public int getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}

	public boolean limitChecker(int amount) {
		if (transactionLimit > (transferAmount + amount)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String benificiaryDetails = "\nCONNECTED ACCOUNT NUMBER " + connectedAccount + "\nBENIFICIARY ID : "
				+ beneficiaryId + "\nTRANSACTION LIMIT : " + transactionLimit + "\nTRASFER AMOUNT : " + transferAmount;
		return benificiaryDetails;
	}

	// changing beneficiary amount
	public boolean changeBeneficiaryAmount(int amount) {
		return beneficiaryDao.changeBeneficiaryAmount(amount, beneficiaryId);
	}
	
	//Update Transfer amount
	public boolean updateTransfer(int amount) {
		return beneficiaryDao.beneficaryEntry(beneficiaryId, amount);
	}

}
