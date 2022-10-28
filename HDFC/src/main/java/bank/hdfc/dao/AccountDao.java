package bank.hdfc.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

import bank.hdfc.function.Beneficiary;
import bank.hdfc.function.Branch;
import bank.hdfc.function.DebitCard;
import bank.hdfc.function.Transaction;

public class AccountDao {
	// getting BranchId
	public Branch getBranch(int branchId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getBranch"));
			preparedStatement.setInt(1, branchId);
			preparedStatement.setInt(2, branchId);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				return new Branch(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7));
			}
			resultset.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("Error in getting branches");
		}
		return null;
	}

	// Adding a entry in transaction history
	public boolean transactionEntry(int accountNumber, int otherAccountNUmber, String description, int amount,
			int action, int modeOfTransaction) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("transcationEntry"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, otherAccountNUmber);
			preparedStatement.setString(3, description);
			preparedStatement.setInt(4, Math.abs(amount));
			preparedStatement.setInt(5, action);
			preparedStatement.setInt(6, modeOfTransaction);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;

		} catch (Exception e) {
			System.out.print("Error in transactionentry");
			e.printStackTrace();
		}
		return false;
	}

	// Getting the transaction History of the account
	public HashMap<Integer, Transaction> transactionHistory(int accountNumber, int balance) {

		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("transactionHistory"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, accountNumber);
			LinkedHashMap<Integer, Transaction> transaction = new LinkedHashMap<>();
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				// Calculating the remaining balance and action for a Transaction History
				int action = resultset.getInt(6);
				int amount = resultset.getInt(7);
				int transferedAccount = resultset.getInt(3);
				if (transferedAccount == 0) {
					balance -= (action == 1 ? amount : (amount * -1));
				} else if (transferedAccount == accountNumber) {
					action = 1;
				} else {
					balance -= amount;
				}
				transaction.put(resultset.getInt(1),
						new Transaction(resultset.getInt(1), resultset.getInt(2), transferedAccount,
								resultset.getString(4), resultset.getDate(5), resultset.getTime(5).toString(), action,
								resultset.getInt(7), balance, resultset.getString(8)));
			}
			resultset.close();
			preparedStatement.close();
			return transaction;

		} catch (Exception e) {
			System.out.println("Error in transactionhistory");
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	// Adding or Debiting money from account
	public int updateAccount(int accountNumber, int amount, String query) {

		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString(query));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, Math.abs(amount));
			preparedStatement.setInt(3, accountNumber);
			int checker = preparedStatement.executeUpdate();
			preparedStatement.close();
			return checker;

		} catch (Exception e) {
			System.out.println("there was an error while updating your account");
			e.printStackTrace();
		}
		return 0;
	}

	// Getting all the beneficiary account for account
	public HashMap<Integer, Beneficiary> getBeneficiaryAccount(int accountNumber) {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, Beneficiary> beneficiary = new HashMap<Integer, Beneficiary>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getBeneficiary"));
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				beneficiary.put(resultset.getInt(1), new Beneficiary(resultset.getInt(1), resultset.getInt(2),
						resultset.getInt(3), resultset.getInt(4), resultset.getInt(5)));
			}
			resultset.close();
			preparedStatement.close();
			return beneficiary;

		} catch (Exception e) {
			System.out.println("Error in getting beneficiary");
		}

		return null;

	}

	public boolean addBeneficiary(int accountNumber, int otherAccountNumber, int transactionLimit) {

		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("addBenificiary"));
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, otherAccountNumber);
			preparedStatement.setInt(3, transactionLimit);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("there was an error while adding beneficiary");
			e.printStackTrace();
		}
		return false;
	}

	// delete a beneficiary
	public boolean deleteBeneficiary(int beneficiaryId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("deleteBeneficiary"));
			preparedStatement.setInt(1, beneficiaryId);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("there was an error while deleting beneficiary");
			e.printStackTrace();
		}
		return false;
	}

	public DebitCard getDebitCard(int accountNumber) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getDebitCard"));
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				return new DebitCard(resultset.getInt(1), resultset.getInt(2), resultset.getString(3),
						resultset.getInt(4), resultset.getString(5), resultset.getInt(6));
			}
			resultset.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("Error in getting debitcard");
		}

		return null;
	}

	public int openDeposit(int accountNumber, int amount, int type, int policy,int numberOfMonth) {
		try (Connection connection = ConnectionTool.getConnection()) {
			int depositId=0;
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("openDeposit"),PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, amount);
			preparedStatement.setInt(3, type);
			preparedStatement.setInt(4, policy);
			preparedStatement.setInt(5, policy);
			preparedStatement.setInt(6, numberOfMonth);
			boolean checker = preparedStatement.execute();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
			if(resultSet!=null && resultSet.next()) {
				depositId=resultSet.getInt("deposit_id");
			}
			resultSet.close();
			preparedStatement.close();
			return depositId;
		} catch (Exception e) {
			System.out.println("there was an error in open deposit");
			e.printStackTrace();
		}
		return 0;
		
		
	}

	public boolean openFixedDeposit(int accountNumber, int amount, int type, int policy,int numberOfMonth) {
		int depositId=openDeposit(accountNumber, amount, type, policy,numberOfMonth);
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("openFixedDeposit"));
			preparedStatement.setInt(1, depositId);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("there was an error fixed deposit");
			e.printStackTrace();
		}
		return false;
		
	}

	public boolean openRecurrsiveDeposit(int accountNumber, int amount, int type, int policy,int numberOfMonth) {
		int depositId=openDeposit(accountNumber, amount, type, policy,numberOfMonth);
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("openRecurringDeposit"));
			preparedStatement.setInt(1, depositId);
			preparedStatement.setInt(2, amount);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("there was an error recurrsive deposit");
			e.printStackTrace();
		}
		return false;
		
		
	}

	public boolean changeUserSetLimit(int accountNumber, int userLimit) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("userSetLimit"));
			preparedStatement.setInt(1, userLimit);
			preparedStatement.setInt(2, accountNumber);
			boolean checker = preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("there was an error recurrsive deposit");
			e.printStackTrace();
		}
		return false;
	}
	

	

}
