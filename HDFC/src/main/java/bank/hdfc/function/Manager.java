package bank.hdfc.function;

import bank.hdfc.dao.ManagerDao;

public class Manager extends Employee {
	private ManagerDao managerDao=new ManagerDao();
	public Manager(String name, int profileId, String phoneNumber, String aadharNumber, String panNumber, String password, String dateOfBirth, String mailId, int employeeId, int role,int branchId, String addressLine1,String addressLine2,String pinCode) {
		super(name, profileId, phoneNumber, aadharNumber, panNumber, password, dateOfBirth, mailId, employeeId, role,branchId,addressLine1,addressLine2,pinCode);
	}
	
	public int addEmployee(int personId) {
		return managerDao.addEmployee(personId,getBranchId(), 1);
		
	}
	public int giveLoan(int customerId,int amount,int accountNumber,int loanType) {
		branch.loadCustomers();
		int resultInt=0;
		if(Branch.customerDetails.containsKey(customerId)) {
			if(Branch.customerDetails.get(customerId).getAccounts().containsKey(accountNumber)) {
				managerDao.giveLoan(customerId, accountNumber, amount,getBranchId(), loanType);
				Branch.customerDetails.replace(customerId, branch.getCustomerDetail(customerId));
				resultInt=1;
			}
			else {
				resultInt=2;
			}
		}
		else {
			resultInt=3;
		}
		return resultInt;
	}
}
