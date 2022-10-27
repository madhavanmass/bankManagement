package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DepositDao {

	public boolean cancelDeposit(int depositId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("cancelDeposit"));
			preparedStatement.setInt(1, depositId);
			boolean checker=preparedStatement.execute();
			preparedStatement.close();
			return checker;
		} catch (Exception e) {
			System.out.println("error in canceling deposit");
			e.printStackTrace();
		}
		return false;
	}
	
}
