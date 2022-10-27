package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDao {

	public boolean issueDebitCard(int accountNumber, int mPin) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("issueDebitCard"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, mPin);
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int addCustomer(int personId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("addCustomer"));
			preparedStatement.setInt(1, personId);
			preparedStatement.execute();
			preparedStatement.close();
			return getId("viewCustomerId", personId);

		} catch (Exception e) {
			System.out.println("ERROR IN ADDING CUSTOMER");
			e.printStackTrace();
		}
		return 0;
	}
	
	protected void createAccount(int customerId, int choice, int initialDeposit, int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createAccount"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, branchId);
			preparedStatement.setInt(3, choice);
			preparedStatement.setInt(4, initialDeposit);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("ERROR IN creating Account");
			e.printStackTrace();
		}
	}
	public void createCurrentAccount(int customerId, int accountChoice, int initialDeposit, int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			createAccount(customerId, 2, initialDeposit, branchId);
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createCurrentAccount"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("ERROR IN creating current Account");
			e.printStackTrace();
		}
	}

	

	public void createSavingAccount(int customerId, int accountChoice, int initialDeposit, float savingAccountInterest,
			int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			createAccount(customerId, 1, initialDeposit, branchId);
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createSavingAccount"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.setFloat(2, savingAccountInterest);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("ERROR IN creating saving account");
			e.printStackTrace();
		}
	}

	public int getId(String query, int id) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString(query));
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			int Id = resultset.getInt(1);
			resultset.close();
			preparedStatement.close();
			return Id;

		} catch (Exception e) {
			System.out.println("ERROR IN GETTING ID");
			e.printStackTrace();
		}
		return 0;
	}

}
