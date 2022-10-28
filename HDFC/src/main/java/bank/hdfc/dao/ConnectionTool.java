package bank.hdfc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import bank.hdfc.pack.BankRoutine;

public class ConnectionTool implements ServletContextListener {

	public static DataSource dataSource = null;
	public static ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
//		Timer timer =new Timer(true);
//		BankRoutine bankRoutine=new BankRoutine();
//		timer.scheduleAtFixedRate(bankRoutine,0,86400000 );
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");

		} catch (Exception e) {
			System.out.println("ERROR IN CONNECTION POOL");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException, NamingException {
		return dataSource.getConnection();
	}
}
