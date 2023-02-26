package sqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class LibraryDB {
	protected String url = "jdbc:mysql://localhost:3306/"; 
	final String user = "root"; 
	protected String password; 
	protected Connection con; 
	protected Statement statement; 
	protected String query; 
	protected ResultSet rs;
	private boolean dbExists; 
	
	/**
	 * This constructor initializes the database if it doesn't exists yet. 
	 * @param str is a password to be set by the user
	 * @throws SQLException when statement results in a syntax error with mysql. 
	 */
	public LibraryDB(String str) throws SQLException {
		password = str; 
		 con = DriverManager.getConnection(url, user, password);
		 statement = con.createStatement();
		 query = "SELECT IF(EXISTS(SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'database_name'), true, false) AS db_exists;\n";
		rs = statement.executeQuery(query); 
		
		if(rs.next()) {
			 dbExists = rs.getBoolean("db_exists");
			if(dbExists) {	
				query = "CREATE DATABASE IF NOT EXISTS librarydb;";
				statement.execute(query);
				url = url + "librarydb";
				/*
				 * TODO 
				 * -make tables for BookDB
				 * -make tables for UserDB
				 */
			}
			else {
				query = "CREATE DATABASE IF NOT EXISTS librarydb;";
				statement.execute(query);
				url = url + "librarydb";
			}
				
		} // end of if
		
	}
	
}
