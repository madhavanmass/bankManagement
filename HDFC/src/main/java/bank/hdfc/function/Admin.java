package bank.hdfc.function;

import java.util.HashMap;

import bank.hdfc.dao.AdminDao;

public class Admin extends Person {
	private String bankName;
	private HashMap<Integer, Branch> branchDetails = null;
	private AdminDao adminDao = new AdminDao();

	public Admin(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber, String password,
			String dateOfBirth, String mailId, String bankName, String addressLine1, String addressLine2,
			String pinCode) {
		super(name, profileId, phoneNumber, aadharNumber, panNumber, password, dateOfBirth, mailId, addressLine1,
				addressLine2, pinCode);
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public HashMap<Integer, Branch> getBranchDetails() {
		return branchDetails;
	}

	public void setBranchDetails(HashMap<Integer, Branch> branchDetails) {
		this.branchDetails = branchDetails;
	}

	public void loadBranch() {
		if (branchDetails == null) {
			branchDetails = adminDao.getBranchDetails();
		}
	}

	// assigning manager to branch
	public int assignManager(int branchId, int personId) {
		loadBranch();
		if (branchDetails.containsKey(branchId)) {
			branchDetails = null;
			return adminDao.addManager(personId, branchId, 2);
		}
		return 0;
	}

	// creating a branch
	public int createBranch(String branchName, String addressLine1, String addressLine2, String pinCode) {
		branchDetails = null;
		return adminDao.addBranch(branchName, addressLine1, addressLine2, pinCode);

	}
}
