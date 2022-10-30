package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.Statement;

public class BankDao {
	public void resetTransfers() {
		try (Connection connection = ConnectionTool.getConnection()) {
			Statement statement = connection.createStatement();
			statement.addBatch(ConnectionTool.resourceBundle.getString("upadateCurrentAccountTransfer"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("updateBeneficiaryLimit"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("updateSavingAccountTransfer"));
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println("error in transfer update daily");
		}
	}

	public void updateSavingAccount() {
		try (Connection connection = ConnectionTool.getConnection()) {
			Statement statement = connection.createStatement();
			statement.addBatch(ConnectionTool.resourceBundle.getString("savingAccountUpdate1"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("savingAccountUpdate2"));
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println("error in savingAccount routine");
		}
	}

	public void updateDeposits() {
		try (Connection connection = ConnectionTool.getConnection()) {
			Statement statement = connection.createStatement();
			statement.addBatch(ConnectionTool.resourceBundle.getString("depositUpdate1"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("depositUpdate2"));
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println("error in deposits routine");
		}
	}
	
	public void updateLoanAccounts() {
		try (Connection connection = ConnectionTool.getConnection()) {
			Statement statement = connection.createStatement();
			statement.addBatch(ConnectionTool.resourceBundle.getString("loanUpdate1"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("loanUpdate2"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("loanUpdate3"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("loanUpdate4"));
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println("error in loan updates routine");
		}
		
	}
	public void checkPenality() {
		try (Connection connection = ConnectionTool.getConnection()) {
			Statement statement = connection.createStatement();
			statement.addBatch(ConnectionTool.resourceBundle.getString("penalityAccount1"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("penalityAccount2"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("penalityAccount3"));
			statement.addBatch(ConnectionTool.resourceBundle.getString("penalityAccount4"));
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println("error in penality");
		}
		
	}

}
