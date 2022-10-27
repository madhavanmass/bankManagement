package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BeneficiaryDao {

	public boolean changeBeneficiaryAmount(int amount, int beneficiaryId) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement(ConnectionTool.resourceBundle.getString("benificiaryUpdate"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, beneficiaryId);
			boolean checker=preparedStatement.execute();
			preparedStatement.close();
			return checker;

		} catch (Exception e) {
			System.out.println("Error in updating beneficiary");
		}
		return false;
	}
	
	public boolean beneficaryEntry(int beneficiaryId, int amount) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("beneficiaryEntry"));
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, beneficiaryId);
			boolean checker=preparedStatement.execute();
			preparedStatement.close();
			return checker;
			
		} catch (Exception e) {
			System.out.println("Error in beneficiary entry");
		}
		return false;
	}

}
