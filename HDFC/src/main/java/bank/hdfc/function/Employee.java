package bank.hdfc.function;

import bank.hdfc.dao.AccountDao;
import bank.hdfc.dao.EmployeeDao;
import bank.hdfc.pack.BankDefinition;

public class Employee extends Person {
	private int employeeId;
	private int role;
	private int branchId;
	private EmployeeDao employeeDao=new EmployeeDao();
	protected Branch branch = null;
	
	public Employee(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber,
			String password, String dateOfBirth, String mailId, int employeeId, int role, int branchId,
			String addressLine1, String addressLine2, String pinCode) {
		super(name, profileId, phoneNumber, aadharNumber, panNumber, password, dateOfBirth, mailId, addressLine1,
				addressLine2, pinCode);
		this.employeeId = employeeId;
		this.role = role;
		this.branchId = branchId;
		branch = new AccountDao().getBranch(branchId);
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	
	// add customer to the branch
	public int addCustomer(int personId) {
		return employeeDao.addCustomer(personId);
	}

	// creating account for the user
	public int createAccount(int customerId,int accountChoice,int initialDeposit) {
		branch.loadCustomers();
		int resultInt=0;
		if (!Branch.customerDetails.containsKey(customerId)) {
			resultInt=1;
		} 
		else {
			if (accountChoice == 2 && (!Branch.customerDetails.get(customerId).getAccounts().containsValue(2))) {
				employeeDao.createCurrentAccount(customerId, accountChoice, initialDeposit, branchId);
				Branch.customerDetails.replace(customerId, branch.getCustomerDetail(customerId));
				resultInt=2;
			} else if (accountChoice == 1) {
				employeeDao.createSavingAccount(customerId, accountChoice, initialDeposit,BankDefinition.savingAccountInterest(initialDeposit), branchId);
				Branch.customerDetails.replace(customerId, branch.getCustomerDetail(customerId));
				resultInt=3;
			} else {
				resultInt=4;
			}
		}
		return resultInt;
	}

	// function to deposit user given money
	public int customerDeposit(int customerId,int accountNumber,int amount) {
		branch.loadCustomers();
		int resultInt=0;
		if (!Branch.customerDetails.containsKey(customerId)) {
			resultInt=5;
		} else {
			if (Branch.customerDetails.get(customerId).getAccounts().containsKey(accountNumber)) {
				branch.doDeposit(accountNumber, amount);
				resultInt=6;
			} else {
				resultInt=7;
			}
		}
		return resultInt;

	}

	public int issueADebitCard(int customerId,int accountNumber,int mPin) {
		branch.loadCustomers();
		int resultInt=0;
		if (!Branch.customerDetails.containsKey(customerId)) {
			resultInt=8;
		} else {
			if (Branch.customerDetails.get(customerId).getAccounts().containsKey(accountNumber)) {
				boolean cardCheck = employeeDao.issueDebitCard(accountNumber, mPin);
				if (cardCheck) {
					resultInt=8;
				} else {
					resultInt=8;
				}

			} else {
				resultInt=8;
			}
		}
		return resultInt;
	}

}