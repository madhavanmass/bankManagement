package bank.hdfc.function;

import java.util.concurrent.ConcurrentHashMap;

import bank.hdfc.dao.AccountDao;
import bank.hdfc.dao.BranchDao;
import bank.hdfc.pack.CustomerDetail;
import bank.hdfc.pack.EmployeeDetail;

public class Branch {
	private int branchId;
	private String branchName;
	private String ifscCode;
	private String location;
	private String manager;
	private String addressLine1;
	private String addressLine2;
	private String pinCode;
	
	private static ConcurrentHashMap<Integer, CustomerDetail> customerDetails = null;
	private static ConcurrentHashMap<Integer, EmployeeDetail> employeeDetails = null;

	public Branch(int branchId, String branchName, String ifscCode, String manager, String addressLine1,
			String addressLine2, String pinCode) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		this.manager = manager;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pinCode = pinCode;
		this.location=addressLine1+addressLine2+pinCode;
	}
	
	
	public static ConcurrentHashMap<Integer, CustomerDetail> getCustomerDetails() {
		return customerDetails;
	}
	public static void setCustomerDetails(ConcurrentHashMap<Integer, CustomerDetail> customerDetails) {
		Branch.customerDetails = customerDetails;
	}
	public static ConcurrentHashMap<Integer, EmployeeDetail> getEmployeeDetails() {
		return employeeDetails;
	}
	public static void setEmployeeDetails(ConcurrentHashMap<Integer, EmployeeDetail> employeeDetails) {
		Branch.employeeDetails = employeeDetails;
	}
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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
		return "\nBRANCH ID : " + branchId + "\nBRANCH NAME : " + branchName + "\nIFSC CODE : " + ifscCode
				+ "\nLOCATION : " + location + "\nMANAGER : " + (manager == null ? "NOT ASSIGNED" : manager);
	}

	private BranchDao branchDao = new BranchDao();

	public Account getAccount(int accountNumber) {
		return branchDao.getAccount(accountNumber);
	}

	public int doDeposit(int accountNumber, int amount) {
		Account account = getAccount(accountNumber);
		int checker = new AccountDao().updateAccount(accountNumber, amount * -1,
				account.getAccountType() == 1 ? "updateSavingAccount" : "updateCurrentAccount");
		return checker;
	}

	public void loadEmployee() {
		if (employeeDetails == null) {
			employeeDetails = branchDao.getEmployees(branchId);
		}
	}

	public void loadCustomers() {
		if (customerDetails == null) {
			customerDetails = branchDao.getCustomerDetails();
		}
	}

	public CustomerDetail getCustomerDetail(int customerId) {
		return branchDao.getACustomerDetail(customerId);
		
	}
}
