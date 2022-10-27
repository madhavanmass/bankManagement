package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;
import bank.hdfc.function.Account;
import bank.hdfc.pack.BankDefinition;
import bank.hdfc.pack.CustomerDetail;
import bank.hdfc.pack.EmployeeDetail;

public class BranchDao {

	public Account getAccount(int accountNumber) {
	try (Connection connection=ConnectionTool.getConnection()){
        PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("getAccount"));
        preparedStatement.setInt(1, accountNumber);
        ResultSet resultset=preparedStatement.executeQuery();
        if(resultset.next()) {
        	return new Account(resultset.getInt(1), resultset.getInt(2),resultset.getInt(3), resultset.getInt(4), resultset.getDate(5));
        }
        resultset.close();
        preparedStatement.close();
        
    } catch (Exception e) {
        System.out.println("Error in getting account");
    }
	
	return null;
	}

	public ConcurrentHashMap<Integer, EmployeeDetail> getEmployees(int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			ConcurrentHashMap<Integer, EmployeeDetail> employee = new ConcurrentHashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getEmployeeDetail"));
			preparedStatement.setInt(1, branchId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				employee.put(resultset.getInt(1),new EmployeeDetail(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4),BankDefinition.roleName(resultset.getInt(5)) , branchId));
			}
			resultset.close();
			preparedStatement.close();
			return employee;

		} catch (Exception e) {
			System.out.println("Error in getting employee");
		}

		return null;
	}

	public ConcurrentHashMap<Integer, CustomerDetail> getCustomerDetails() {
		try (Connection connection = ConnectionTool.getConnection()) {
			ConcurrentHashMap<Integer, CustomerDetail> customer = new ConcurrentHashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getCustomerDetail"));
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				customer.put(resultset.getInt(1),new CustomerDetail(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), getAccountDetails(resultset.getInt(1))));
			}
			resultset.close();
			preparedStatement.close();
			return customer;

		} catch (Exception e) {
			System.out.println("Error in getting customers");
		}

		return null;
	}

	private ConcurrentHashMap<Integer, Integer> getAccountDetails(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			ConcurrentHashMap<Integer, Integer> account = new ConcurrentHashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getAccountDetail"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				account.put(resultset.getInt(1),resultset.getInt(2));
			}
			resultset.close();
			preparedStatement.close();
			return account;

		} catch (Exception e) {
			System.out.println("Error in getting accounts");
		}

		return null;
	}

}
