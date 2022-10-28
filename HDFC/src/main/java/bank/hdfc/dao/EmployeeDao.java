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
			int customerId=0;
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("addCustomer"),PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, personId);
			preparedStatement.execute();
			preparedStatement.close();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet!=null && resultSet.next()) {
            	customerId=resultSet.getInt("customer_id");
            }
            resultSet.close();
            preparedStatement.close();
			return customerId;

		} catch (Exception e) {
			System.out.println("ERROR IN ADDING CUSTOMER");
			e.printStackTrace();
		}
		return 0;
	}
	
	protected int createAccount(int customerId, int choice, int initialDeposit, int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int accountNumber=0;
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createAccount"),PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, branchId);
			preparedStatement.setInt(3, choice);
			preparedStatement.setInt(4, initialDeposit);
			preparedStatement.executeUpdate();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
			if(resultSet!=null && resultSet.next()) {
				accountNumber=resultSet.getInt("account_number");
			}
			resultSet.close();
			preparedStatement.close();
			return accountNumber;

		} catch (Exception e) {
			System.out.println("ERROR IN creating Account");
			e.printStackTrace();
		}
		return 0;
	}
	public int createCurrentAccount(int customerId, int accountChoice, int initialDeposit, int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int accountNumber=createAccount(customerId, 2, initialDeposit, branchId);
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createCurrentAccount"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, initialDeposit);
			preparedStatement.execute();
			preparedStatement.close();
			return accountNumber;

		} catch (Exception e) {
			System.out.println("ERROR IN creating current Account");
			e.printStackTrace();
		}
		return 0;
	}

	

	public int createSavingAccount(int customerId, int accountChoice, int initialDeposit, float savingAccountInterest,
			int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int accountNumber=createAccount(customerId, 1, initialDeposit, branchId);
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createSavingAccount"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, initialDeposit);
			preparedStatement.setFloat(3, savingAccountInterest);
			preparedStatement.execute();
			preparedStatement.close();
			return accountNumber;

		} catch (Exception e) {
			System.out.println("ERROR IN creating saving account");
			e.printStackTrace();
		}
		return 0;
	}

//	public int getId(String query, int id) {
//		try (Connection connection = ConnectionTool.getConnection()) {
//			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString(query));
//			preparedStatement.setInt(1, id);
//			ResultSet resultset = preparedStatement.executeQuery();
//			resultset.next();
//			int Id = resultset.getInt(1);
//			resultset.close();
//			preparedStatement.close();
//			return Id;
//
//		} catch (Exception e) {
//			System.out.println("ERROR IN GETTING ID");
//			e.printStackTrace();
//		}
//		return 0;
//	}

}
