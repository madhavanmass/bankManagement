package bank.hdfc.function;

import bank.hdfc.dao.ManagerDao;

public class Manager extends Employee {

	private int managerId;
	private ManagerDao managerDao = new ManagerDao();

	public Manager(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber,
			String password, String dateOfBirth, String mailId, int employeeId, int role, int branchId,
			String addressLine1, String addressLine2, String pinCode, int managerId) {
		super(name, profileId, phoneNumber, aadharNumber, panNumber, password, dateOfBirth, mailId, employeeId, role,
				branchId, addressLine1, addressLine2, pinCode);
		this.managerId = managerId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int addEmployee(int personId) {
		return managerDao.addEmployee(personId, getBranchId(), 1);

	}

	public int giveLoan(int customerId, int amount, int accountNumber, int loanType) {
		getBranch().loadCustomers();
		int resultInt = 0;
		if (Branch.getCustomerDetails().containsKey(customerId)) {
			if (Branch.getCustomerDetails().get(customerId).getAccounts().containsKey(accountNumber)) {
				managerDao.giveLoan(customerId, accountNumber, amount, getBranchId(), loanType);
				Branch.setCustomerDetails(null);
				resultInt = 1;
			} else {
				resultInt = 2;
			}
		} else {
			resultInt = 3;
		}
		return resultInt;
	}

	@Override
	public String toString() {
		return "<tr><td> MANAGER ID </td><td>"+managerId+ "</td></tr>"+super.toString();
	}
}
