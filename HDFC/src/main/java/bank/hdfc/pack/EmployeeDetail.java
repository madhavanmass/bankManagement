package bank.hdfc.pack;

public class EmployeeDetail {
	private int employeeId;
	private String name;
	private String phoneNumber;
	private String mailId;
	private String role;
	private int branchId;

	public EmployeeDetail(int employeeId, String name, String phoneNumber, String mailId, String role, int branchId) {
		this.employeeId = employeeId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.mailId = mailId;
		this.role = role;
		this.branchId = branchId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		String details =
				"<tr><td> EMPLOYEE ID </td><td>"+employeeId+ "</td></tr>"
				+ "<tr><td> EMPLOYEE NAME </td><td>"+name+ "</td></tr>"
				+ "<tr><td> PHONE NUMBER </td><td>"+phoneNumber+ "</td></tr>"
				+ "<tr><td> MAIL ID </td><td>"+mailId+ "</td></tr>"
				+ "<tr><td> ROLE </td><td>"+role+ "</td></tr>"
				;
		return details;
	}
}
