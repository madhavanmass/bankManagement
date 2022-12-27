package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
				employee.put(resultset.getInt(1),new EmployeeDetail(resultset.getInt(5)==1?resultset.getInt(1):resultset.getInt(6), resultset.getString(2), resultset.getString(3), resultset.getString(4),BankDefinition.roleName(resultset.getInt(5)) , branchId));
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

	private ConcurrentHashMap<Integer, ArrayList<Object>> getAccountDetails(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			ConcurrentHashMap<Integer, ArrayList<Object>> account = new ConcurrentHashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getAccountDetail"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				ArrayList<Object> details =new ArrayList<Object>();
				details.add(resultset.getInt(2));//account type
				details.add(resultset.getInt(3));//branch id
				details.add(resultset.getString(4));// branch name
				account.put(resultset.getInt(1),details);
			}
			resultset.close();
			preparedStatement.close();
			return account;

		} catch (Exception e) {
			System.out.println("Error in getting accounts");
		}

		return null;
	}

	public CustomerDetail getACustomerDetail(int customerId) {
		try(Connection connection = ConnectionTool.getConnection()) {
		CustomerDetail customerDetail=null;
		PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("getACustomerDetail"));
		preparedStatement.setInt(1, customerId);
		ResultSet resultset = preparedStatement.executeQuery();
		if(resultset.next()) {
			customerDetail= new CustomerDetail(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), getAccountDetails(resultset.getInt(1)));
		}
		resultset.close();
		preparedStatement.close();
		return customerDetail;
		}
		catch(Exception e) {
			System.out.print("error in getting a customer detail");
			e.printStackTrace();
		}
		return null;
	}
	
	public int checkPerson(String aadharNumber, String panNumber,int option) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("checkPerson"));
			preparedStatement.setString(1, aadharNumber);
			preparedStatement.setString(2, panNumber);
			
			ResultSet resultSet= preparedStatement.executeQuery();
			int userId=0;
			while(resultSet.next()) {
				
				switch (option) {
				case 1:
					userId=resultSet.getInt(1);
					break;
				case 2:
					userId=resultSet.getInt(2);
					break;
				case 3:
					userId=resultSet.getInt(3);
					break;
				}
				
			}
			
			if(userId!=0) {
				preparedStatement.close();
				resultSet.close();
				return userId;
			}
			else {
				preparedStatement.close();
				resultSet.close();
				return 0;
			}

		} catch (Exception e) {
			System.out.println("ERROR IN CHECKING EXISTANCE");
			e.printStackTrace();
		}
		
		return 0;
	}


	
}


