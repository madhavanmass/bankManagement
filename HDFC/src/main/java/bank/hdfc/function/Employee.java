package bank.hdfc.function;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
	
	//check if 
	public boolean checkAccount(ConcurrentHashMap<Integer, ArrayList<Object>> concurrentHashMap) {
		for(ArrayList<Object> arr :concurrentHashMap.values()) {
			if(((Integer)arr.get(0)) == 2) {
				return false;
			}
		}
		return true;
	}

	// creating account for the user
	public int createAccount(int customerId, int accountChoice, int initialDeposit) {
		branch.loadCustomers();
		int resultInt = 3;
		branch.loadCustomers();
		
		if (!branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (accountChoice == 2 && checkAccount(branch.getCustomerDetails().get(customerId).getAccounts()) ){//branch.getCustomerDetails().get(customerId).getAccounts().containsValue(2))) {
				resultInt= employeeDao.createCurrentAccount(customerId, accountChoice, initialDeposit, branchId);
				branch.setCustomerDetails(null);
			} else if (accountChoice == 1) {
				resultInt= employeeDao.createSavingAccount(customerId, accountChoice, initialDeposit,
						BankDefinition.savingAccountInterest(initialDeposit), branchId);
				branch.setCustomerDetails(null);
			}
			else {
				resultInt=4;
			}
		}
		return resultInt;
	}

	// function to deposit user given money
	public int customerDeposit(int customerId, int accountNumber, int amount,int transferAmount,String description) {
		branch.setCustomerDetails(null);
		branch.loadCustomers();
		int resultInt = 3;
		if (!branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (branch.getCustomerDetails().get(customerId).getAccounts().containsKey(accountNumber)) {
				
				branch.doDeposit(accountNumber,0,amount,transferAmount,2,description);
			}
			else{
				resultInt=2;
			}
		}
		return resultInt;

	}

	public int issueDebitCard(int customerId, int accountNumber, int mPin) {
		branch.loadCustomers();
		int resultInt = 3;
		if (!branch.getCustomerDetails().containsKey(customerId)) {
			resultInt = 1;
		} else {
			if (branch.getCustomerDetails().get(customerId).getAccounts().containsKey(accountNumber)) {
				boolean cardCheck = employeeDao.issueDebitCard(accountNumber, mPin);
				if(!cardCheck) {
					resultInt=4;
				}

			} else {
				resultInt = 2;
			}
		}
		return resultInt;
	}
	
	@Override
	public String toString() {
		String details=super.toString()
				+branch.toString()+
				"<tr><td> ROLE </td><td>"+BankDefinition.roleName(role)+ "</td></tr>"
				;
		if(role==1) {
			details="<tr><td> EMPLOYEE ID </td><td>"+employeeId+ "</td></tr>"+details;
		}
		return details;
				
	}



}
