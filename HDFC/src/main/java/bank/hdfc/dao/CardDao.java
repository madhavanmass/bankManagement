package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CardDao {

	public void updateMPin(int cardNumber, int newMPin) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("resetMPin"));
			preparedStatement.setInt(1, newMPin);
			preparedStatement.setInt(2, cardNumber);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("ERROR IN updating mPin");
			e.printStackTrace();
		}
	}

	public void updateCardStatus(int cardNumber, int choice) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("updateCardStatus"));
			preparedStatement.setInt(1, choice);
			preparedStatement.setInt(2, cardNumber);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("ERROR IN updating card Status");
			e.printStackTrace();
		}
	}
	

}
