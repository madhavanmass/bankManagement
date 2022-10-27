package bank.hdfc.function;

import bank.hdfc.dao.VerifyDao;

public class Verification {
	VerifyDao verifyDao = new VerifyDao();
	
	public Customer verifyCustomer(int id, String password) {
		return verifyDao.verifyCustomer(id, password);
	}
	
	public Manager verifyEmployee(int id,String password,int role) {
		return verifyDao.verifyEmployee(id,password,role);
	}
	
	public Admin verifyAdmin(int id,String password) {
		return verifyDao.verifyAdmin(id,password);
	}
	
	
}
