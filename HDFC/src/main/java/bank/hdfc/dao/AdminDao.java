package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import bank.hdfc.function.Branch;

public class AdminDao {

	public HashMap<Integer, Branch> getBranchDetails() {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, Branch> branch = new HashMap<>();
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("getBranchDetails"));
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				branch.put(resultset.getInt(1), new Branch(resultset.getInt(1), resultset.getString(2),
						resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7)));
			}
			resultset.close();
			preparedStatement.close();
			return branch;

		} catch (Exception e) {
			System.out.println("Error in getting branches");
			e.printStackTrace();
		}

		return null;
	}
	

	public int addManager(int personId, int branchId, int role) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int managerId=0;
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("addManager"),PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, personId);
			preparedStatement.execute();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet!=null && resultSet.next()) {
            	managerId=resultSet.getInt("employee_id");
            }
            resultSet.close();
			preparedStatement.close();
			return managerId;

		} catch (Exception e) {
			System.out.println("ERROR IN ADDING EMPLOYEE");
			e.printStackTrace();
		}
		return 0;
		
	}

	public int addBranch(String branchName, String addressLine1, String addressLine2, String pinCode) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int branchId=0;
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("createBranch"),PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, branchName);
			preparedStatement.setString(2, addressLine1);
			preparedStatement.setString(3, addressLine2);
			preparedStatement.setString(4, pinCode);
			preparedStatement.execute();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet!=null && resultSet.next()) {
            	branchId=resultSet.getInt("branch_id");
            }
            resultSet.close();
			preparedStatement.close();
			return branchId;

		} catch (Exception e) {
			System.out.println("ERROR IN CREATING A BRANCH");
			e.printStackTrace();
		}
		return 0;

	}


//	private int getBranchId(String branchName) {
//		try (Connection connection = ConnectionTool.getConnection()) {
//			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("getBranchId"));
//			preparedStatement.setString(1, branchName);
//			ResultSet resultset=preparedStatement.executeQuery();
//			resultset.next();
//			int branchId = resultset.getInt(1);
//			resultset.close();
//			preparedStatement.close();
//			return branchId;
//			
//
//		} catch (Exception e) {
//			System.out.println("ERROR IN CREATING A BRANCH");
//			e.printStackTrace();
//		}
//		return 0;
//	}


//	private String generateIfsc(String bankName) {
//		try (Connection connection = ConnectionTool.getConnection()) {
//			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("generateIfsc"));
//			ResultSet resultset = preparedStatement.executeQuery();
//			int count = 1;
//			if (resultset.next()) {
//				count = resultset.getInt(1) + 1;
//			}
//			resultset.close();
//			preparedStatement.close();
//			return bankName + String.format("%06d", count);
//
//		} catch (Exception e) {
//			System.out.println("Error in generating ifsc");
//		}
//		return null;
//	}

}
