package bank.hdfc.function;

import bank.hdfc.dao.PersonDao;
import bank.hdfc.pack.CipherTextGenerator;

public class Person {
	private String name;
	private int profileId;
	private String phoneNumber;
	private String aadharNumber;
	private String panNumber;
	private String password;
	private String dateOfBirth;
	private String mailId;
	private String addressLine1;
	private String addressLine2;
	private String pinCode;

	public Person(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber,
			String password, String dateOfBirth, String mailId, String addressLine1, String addressLine2,
			String pinCode) {
		this.name = name;
		this.profileId = profileId;
		this.phoneNumber = phoneNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.mailId = mailId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pinCode = pinCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	@Override
	public String toString() {
		String details = 
				"<tr><td> NAME </td><td>"+name+ "</td></tr>"
				+ "<tr><td> DATE OF BIRTH </td><td>"+dateOfBirth+ "</td></tr>"
				+ "<tr><td> AADHAR NUMBER </td><td>"+aadharNumber+ "</td></tr>"
				+ "<tr><td> PAN NUMBER </td><td>"+panNumber+ "</td></tr>"
				+ "<tr><td> PHONE NUMBER </td><td>"+phoneNumber+ "</td></tr>"
				+ "<tr><td> MAIL ID </td><td>"+mailId+ "</td></tr>"
				+ "<tr><td> ADDRESS LINE 1 </td><td>"+addressLine1+ "</td></tr>"
				+ "<tr><td> ADDRESS LINE 2 </td><td>"+addressLine2+ "</td></tr>"
				+ "<tr><td> PINCODE </td><td>"+pinCode+ "</td></tr>";
			
		return details;
	}
	
	PersonDao personDao=new PersonDao();
	
	public boolean changePassword(String oldPass, String newPassword) {
		//System.out.println(password+"  "+new CipherTextGenerator().encrypt(oldPass, oldPass.charAt(0)));
		if (password.equals(new CipherTextGenerator().encrypt(oldPass, oldPass.charAt(0)))) {
			personDao.changepassword(new CipherTextGenerator().encrypt(newPassword, newPassword.charAt(0)), getProfileId());
			password=new CipherTextGenerator().encrypt(newPassword, newPassword.charAt(0));
			return true;

		}
		return false;
	}
	
	public int createPerson(String customerName,String dateOfBirth,String aadharNumber,String panNumber,String phoneNumber,String mailId,String addressline1,String addressline2,String pincode, String password) {
		return personDao.createPerson(customerName, dateOfBirth, aadharNumber, panNumber, phoneNumber, mailId, addressline1, addressline2, pincode, password);
	}
}
