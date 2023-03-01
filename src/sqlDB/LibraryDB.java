package sqlDB;

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
		con = DriverManager.getConnection(url, user, password); //creates a connection to the local server
		statement = con.createStatement(); //creates a statement
		//query is whatever sql statement you would like to pass into sql
		//checks to see whether or not the database exists in the server
		query = "SELECT IF(EXISTS(SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'database_name'), true, false) AS db_exists;\n";
		//runs the command
		rs = statement.executeQuery(query); 

		//iterates through the set as long as the next element in the set exists / is not null
		if(rs.next()) {
			//boolean to show whether or not the database exists
			
			dbExists = rs.getBoolean("db_exists");
			//if the database does not exist, create the data 
			if(!dbExists) {	
				
				query = "CREATE DATABASE IF NOT EXISTS librarydb;";
				//updating the url to go directly to the database we want 
				statement.execute(query);
				url = url + "librarydb";
				
				//initalize the tables for the books and the user
				/*
				 * TODO 
				 * -make tables for BookDB
				 * -make tables for UserDB
				 */
			}
			
			else { //if the database exists, do nothing
				
				//creates the database if it doesnt exist
				query = "CREATE DATABASE IF NOT EXISTS librarydb;";
				statement.execute(query);
				url = url + "librarydb";
			}

		} // end of if

	}

=======
	
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
	
>>>>>>> refs/remotes/origin/pwDB
}
