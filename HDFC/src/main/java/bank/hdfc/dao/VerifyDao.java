package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bank.hdfc.function.Admin;
import bank.hdfc.function.Customer;
import bank.hdfc.function.Manager;
import bank.hdfc.pack.CipherTextGenerator;

public class VerifyDao {
	
	CipherTextGenerator cipher=new CipherTextGenerator();
	
	//check if Customer exist
	public Customer verifyCustomer(int Id, String password) {
        try{
        	Connection connection=ConnectionTool.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("verifyCustomer"));
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, cipher.encrypt(password, password.charAt(0)));
            ResultSet resultset=preparedStatement.executeQuery();
            if(resultset.next()){
                Customer customer= new Customer(resultset.getString(1), resultset.getInt(2),resultset.getString(3) ,resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8), resultset.getInt(9),resultset.getString(10),resultset.getString(11),resultset.getString(12));
                return customer;
            }
            resultset.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Error in customer verification");
            e.printStackTrace();
        }
        return null;
    }
	
	//checking if employee exist in database
    public Manager verifyEmployee(int Id, String password,int role) {
        try(Connection connection=ConnectionTool.getConnection()) {
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString(role==1?"verifyEmployee":"verifyMaganer"));
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, cipher.encrypt(password, password.charAt(0)));
            ResultSet resultset=preparedStatement.executeQuery();
            Manager manager=null;
            if(resultset.next()){
            	manager=new Manager(resultset.getString(1),resultset.getInt(2) , resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8), resultset.getInt(9), resultset.getInt(10), resultset.getInt(11),resultset.getString(12),resultset.getString(13),resultset.getString(14),role==1?0:resultset.getInt(15));
            }
            resultset.close();
            preparedStatement.close();
            return manager;
            
        } catch (Exception e) {
            System.out.println("Error in verifing the employee");
            e.printStackTrace();
        }
        return null;
    }
    
    
    //checking if admin exist in database
    public Admin verifyAdmin(int Id,String password) {
    	try(Connection connection=ConnectionTool.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(ConnectionTool.resourceBundle.getString("verifyAdmin"));
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, cipher.encrypt(password, password.charAt(0)));
            ResultSet resultset=preparedStatement.executeQuery();
            if(resultset.next()){
                Admin admin=new Admin(resultset.getString(1),resultset.getInt(2), resultset.getString(3), resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getString(7),resultset.getString(8), resultset.getString(9), resultset.getString(10),resultset.getString(11),resultset.getString(12));
                return admin;
            }
            resultset.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Error in verifing the admin");
            e.printStackTrace();
        }
        return null;
    	
    }
    
}
