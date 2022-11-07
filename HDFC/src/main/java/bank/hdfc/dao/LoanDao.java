package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoanDao {
	public void payLoan(int amount, int loanId) {
		try{
			
			Connection connection=ConnectionTool.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("payLoan"));
            preparedStatement.setInt(1,amount);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, loanId);
            preparedStatement.execute();
            preparedStatement.close();
            
        } catch (Exception e) {
            System.out.println("Error in paying loan");
            e.printStackTrace();
        }
	}
}
