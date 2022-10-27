package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bank.hdfc.pack.BankDefinition;

public class ManagerDao {

	public int addEmployee(int personId, int branchId, int role) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("addEmployee"));
			preparedStatement.setInt(1, branchId);
			preparedStatement.setInt(2, personId);
			preparedStatement.setInt(3, role);
			preparedStatement.execute();
			preparedStatement.close();
			return new EmployeeDao().getId("viewEmployeeId", personId);
		} catch (Exception e) {
			System.out.println("ERROR IN ADDING EMPLOYEE");
			e.printStackTrace();
		}
		return 0;
	}

	public void giveLoan(int customerId, int accountNumber, int amount, int branchId, int loanType) {
		try (Connection connection = ConnectionTool.getConnection()) {
			new EmployeeDao().createAccount(customerId, 3, amount, branchId);
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("giveLoan"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, loanType);
			preparedStatement.setInt(3, accountNumber);
			preparedStatement.setInt(4, amount);
			preparedStatement.setInt(5, BankDefinition.getDue(loanType));
			preparedStatement.setInt(6, loanType );
			preparedStatement.setInt(7, amount);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("ERROR IN GIVING LOAN");
			e.printStackTrace();
		}
		
	}

}
