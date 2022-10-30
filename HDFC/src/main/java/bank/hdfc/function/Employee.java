package bank.hdfc.function;

import bank.hdfc.dao.AccountDao;
import bank.hdfc.dao.EmployeeDao;
import bank.hdfc.pack.BankDefinition;

public class Employee extends Person {
	private int employeeId;
	private int role;
	private int branchId;
	private EmployeeDao employeeDao = new EmployeeDao();
	private Branch branch = null;

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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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
	public int createAccount(int customerId, int accountChoice, int initialDeposit) {
		branch.loadCustomers();
		int resultInt = 3;
		if (!Branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (accountChoice == 2 && (!Branch.getCustomerDetails().get(customerId).getAccounts().containsValue(2))) {
				resultInt= employeeDao.createCurrentAccount(customerId, accountChoice, initialDeposit, branchId);
				Branch.setCustomerDetails(null);
			} else if (accountChoice == 1) {
				resultInt= employeeDao.createSavingAccount(customerId, accountChoice, initialDeposit,
						BankDefinition.savingAccountInterest(initialDeposit), branchId);
				Branch.setCustomerDetails(null);
			}
		}
		return resultInt;
	}

	// function to deposit user given money
	public int customerDeposit(int customerId, int accountNumber, int amount) {
		branch.loadCustomers();
		int resultInt = 3;
		if (!Branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (Branch.getCustomerDetails().get(customerId).getAccounts().containsKey(accountNumber)) {
				branch.doDeposit(accountNumber, amount);
				resultInt = 2;
			}
		}
		return resultInt;

	}

	public int issueDebitCard(int customerId, int accountNumber, int mPin) {
		branch.loadCustomers();
		int resultInt = 3;
		if (!Branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (Branch.getCustomerDetails().get(customerId).getAccounts().containsKey(accountNumber)) {
				boolean cardCheck = employeeDao.issueDebitCard(accountNumber, mPin);

			} else {
				resultInt = 2;
			}
		}
		return resultInt;
	}

}
