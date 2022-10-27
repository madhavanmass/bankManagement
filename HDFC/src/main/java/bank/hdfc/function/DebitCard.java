package bank.hdfc.function;

import bank.hdfc.dao.CardDao;
import bank.hdfc.pack.BankDefinition;

public class DebitCard {
	private int cardNumber;
	private int accountNumber;
	private String expireDate;
	private int mPin;
	private String cardIssuedDate;
	private int status;
	private CardDao cardDB = new CardDao();

	public DebitCard(int cardNumber, int accountNumber, String expireDate, int mPin, String cardIssuedDate,
			int status) {
		this.cardNumber = cardNumber;
		this.accountNumber = accountNumber;
		this.expireDate = expireDate;
		this.mPin = mPin;
		this.cardIssuedDate = cardIssuedDate;
		this.status = status;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public int getmPin() {
		return mPin;
	}

	public void setmPin(int mPin) {
		this.mPin = mPin;
	}

	public String getCardIssuedDate() {
		return cardIssuedDate;
	}

	public void setCardIssuedDate(String cardIssuedDate) {
		this.cardIssuedDate = cardIssuedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "<br>CARDNUMBER : " + cardNumber + " <br>EXPIREDATE : " + expireDate + " <br>M-PIN : " + mPin
				+ " <br>CARDISSUEDDATE : " + cardIssuedDate + " <br>STATUS : " + BankDefinition.status(status);
	}


	public boolean changeMPin(int oldMPin,int newMPin) {
		if (oldMPin == mPin) {
			cardDB.updateMPin(cardNumber, newMPin);
			mPin = newMPin;
			return true;
		}
		return false;
	}

	public boolean activateOrLock(int choice) {
		if (status != choice) {
			cardDB.updateCardStatus(cardNumber, choice);
			status = choice;
			return true;
		} 
		return false;
		
	}
}
