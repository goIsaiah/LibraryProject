package Databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import GUI.LibraryUI;

public class DBUtil {
	
	// authentication details for online database
	private static String onlineUserName = "sql9609229";
	private static String online = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9609229";
	private static String onlinePassword = "yUHweNy2bX";
	
	// authentication details for local server
	
	private static String offlineUserName = "root"; 
	private static String offline = "jdbc:mysql://localhost:3306/myDB"; 
	private static String offlinePassword; 
	
	
	
	
	public static Connection getConnection(DBType_enum dbType) throws SQLException{
		offlinePassword = LibraryUI.sqlpassword;
		switch(dbType) {
		
		case ONLINE:
			return DriverManager.getConnection(online, onlineUserName, onlinePassword); 
		case OFFLINE:
			return DriverManager.getConnection(offline, offlineUserName, offlinePassword); 
		default:
			return null; 
		}
		
		
		
	}
	
}


