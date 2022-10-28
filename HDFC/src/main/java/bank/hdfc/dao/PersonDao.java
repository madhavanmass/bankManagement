package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bank.hdfc.pack.CipherTextGenerator;

public class PersonDao {
	public void changepassword(String encrypt, int profileId) {
		try{
			Connection connection=ConnectionTool.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("updatePassword"));
            preparedStatement.setString(1,encrypt);
            preparedStatement.setInt(2, profileId);
            preparedStatement.execute();
            preparedStatement.close();
            
        } catch (Exception e) {
            System.out.println("Error in updating password");
        }
	}
	public int createPerson(String customerName,String dateOfBirth,String aadharNumber,String panNumber,String phoneNumber,String mailId,String addressline1,String addressline2,String pincode, String password) {
	
		try (Connection connection=ConnectionTool.getConnection()){
            int personId=0;
			PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("addPerson"),PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, aadharNumber);
            preparedStatement.setString(4, panNumber);
            preparedStatement.setString(5, new CipherTextGenerator().encrypt(password,password.charAt(0)));
            preparedStatement.setDate(6, Date.valueOf(dateOfBirth));
            preparedStatement.setString(7, mailId);
            preparedStatement.setString(8, addressline1);
            preparedStatement.setString(9, addressline2);
            preparedStatement.setString(10, pincode);
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet!=null && resultSet.next()) {
            	personId=resultSet.getInt("person_id");
            }
            resultSet.close();
            preparedStatement.close();
            return personId;

        } catch (Exception e) {
            System.out.println("ERROR IN CREATING PERSON");
            e.printStackTrace();
        }
		return 0;
	}
	private int getPersonId(String customerName, String password) {
		try (Connection connection = ConnectionTool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionTool.resourceBundle.getString("getPersonId"));
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2,new CipherTextGenerator().encrypt(password,password.charAt(0)));
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			int Id = resultset.getInt(1);
			resultset.close();
			preparedStatement.close();
			return Id;

		} catch (Exception e) {
			System.out.println("ERROR IN GETTING ID");
			e.printStackTrace();
		}
		return 0;
	}

}
