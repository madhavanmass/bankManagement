package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import bank.hdfc.function.CurrentAccount;
import bank.hdfc.function.FixedDeposit;
import bank.hdfc.function.LoanAccount;
import bank.hdfc.function.RecurssiveDeposit;
import bank.hdfc.function.SavingAccount;

public class CustomerDao {


	public HashMap<Integer, SavingAccount> getSavingAccounts(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, SavingAccount> savingAccount = new HashMap<Integer, SavingAccount>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getSavingAccounts"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				savingAccount.put(resultset.getInt(1),new SavingAccount(resultset.getInt(1), resultset.getInt(2), resultset.getInt(3), resultset.getInt(4),resultset.getDate(5), resultset.getInt(6), resultset.getFloat(7), resultset.getInt(8), resultset.getInt(9), resultset.getInt(10), resultset.getDate(11)));
			}
			resultset.close();
			preparedStatement.close();
			return savingAccount;

		} catch (Exception e) {
			System.out.println("Error in getting saving accounts");
		}

		return null;
		
	}

	public CurrentAccount getCurrentAccount(int customerId) {
		try (Connection connection=ConnectionTool.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("getCurrentAccount"));
            preparedStatement.setInt(1, customerId);
            ResultSet resultset=preparedStatement.executeQuery();
            if(resultset.next()) {
            	return new CurrentAccount(resultset.getInt(1), resultset.getInt(2),resultset.getInt(3), resultset.getInt(4), resultset.getDate(5), resultset.getInt(6), resultset.getInt(7), resultset.getInt(8), resultset.getInt(9));
            }
            resultset.close();
            preparedStatement.close();
            
        } catch (Exception e) {
            System.out.println("Error in getting current account");
        }
		
		return null;
	}

	public HashMap<Integer, LoanAccount> getLoanAccounts(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, LoanAccount> loanAccounts = new HashMap<Integer, LoanAccount>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getLoanAccounts"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				loanAccounts.put(resultset.getInt(1),new LoanAccount(resultset.getInt(1), resultset.getInt(2), resultset.getInt(3), resultset.getInt(4), resultset.getDate(5), resultset.getInt(6), resultset.getInt(7), resultset.getString(8), resultset.getInt(9), resultset.getDate(10), resultset.getFloat(11), resultset.getInt(12), resultset.getInt(13)));
			}
			resultset.close();
			preparedStatement.close();
			return loanAccounts;

		} catch (Exception e) {
			System.out.println("Error in getting loan accounts");
		}

		return null;
	}
	
	public HashMap<Integer, FixedDeposit> getFixedDeposits(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, FixedDeposit> fixedDeposits = new HashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getFixedDeposit"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				fixedDeposits.put(resultset.getInt(1),new FixedDeposit(resultset.getInt(1), resultset.getInt(2), resultset.getInt(3), resultset.getDate(4), resultset.getString(5), resultset.getString(6),resultset.getDate(7), resultset.getFloat(8)));
			}
			resultset.close();
			preparedStatement.close();
			return fixedDeposits;

		} catch (Exception e) {
			System.out.println("Error in getting fixed deposit");
		}

		return new HashMap<>();
	}
	
	public HashMap<Integer, RecurssiveDeposit> getRecurringDeposits(int customerId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			HashMap<Integer, RecurssiveDeposit> recurrsiveDeposits = new HashMap<>();
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("getRecurringDeposit"));
			preparedStatement.setInt(1, customerId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				recurrsiveDeposits.put(resultset.getInt(1),new RecurssiveDeposit(resultset.getInt(1), resultset.getInt(2), resultset.getInt(3), resultset.getDate(4), resultset.getString(5), resultset.getString(6),resultset.getDate(7), resultset.getFloat(8), resultset.getInt(9)));
			}
			resultset.close();
			preparedStatement.close();
			return recurrsiveDeposits;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in getting recurssive deposit");
		}

		return new HashMap<>();
	}
	
	
	
}
