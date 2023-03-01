package pwDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUser {
	
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = "1977";
	
	static String create_USERTABLE =
			"create table USERTABLE" 
			+ "(USER_ID integer NOT NULL, "
			+ "USERNAME varchar(40) NOT NULL, "
			+ "PASSWORD varchar(40) NOT NULL, "
			+ "EMAIL varchar(60) NOT NULL UNIQUE, "
			+ "PRIMARY KEY (USER_ID))";
	
	static String insert_USERTABLE = "INSERT INTO USERTABLE (USER_ID, USERNAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?)";
	
	public static void main(String[] args) {
		checkDB();
		
	}
	
	private static boolean tableExists(Connection conn, String tableName) throws SQLException {
	    DatabaseMetaData meta = conn.getMetaData();
	    ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
	    return resultSet.next();
	}
	
	private static boolean schemaExists(Connection conn, String schemaName) throws SQLException {
		ResultSet rs = conn.getMetaData().getCatalogs();
		while (rs.next()) {
			String result = rs.getString("TABLE_CAT");
			if (result.compareTo(schemaName) == 0) {
				return true;
			}
		}
		return false;
	}

	private static void checkDB() {
		try {
			Connection con = DriverManager.getConnection(urlRoot, user, password);
			boolean dbExists = schemaExists(con, "mydb");
			System.out.println("DB is " + dbExists);
			
			if (!dbExists) {
				Statement statement = con.createStatement();
	            statement.executeUpdate("CREATE SCHEMA mydb");
			}
			
			con = DriverManager.getConnection(url, user, password);
			boolean tableExists = tableExists(con, "USERTABLE");
			
			if (!tableExists) {
				Statement statement = con.createStatement();
				statement = con.createStatement();
				statement.executeUpdate(create_USERTABLE);
				DBUsers(con);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}


	private static void DBUsers(Connection con) throws SQLException {
	    PreparedStatement statement = con.prepareStatement(insert_USERTABLE);
	    
	    statement.setInt(1, 1);
	    statement.setString(2, "Polywertz");
	    statement.setString(3, "123456");
	    statement.setString(4, "Polywertz@gmail.com");
	    statement.executeUpdate();
	    
	    statement.setInt(1, 2);
	    statement.setString(2, "Polywertz2");
	    statement.setString(3, "123456");
	    statement.setString(4, "Polywertz2@gmail.com");
	    statement.executeUpdate();
	    
	    statement.setInt(1, 3);
	    statement.setString(2, "Polywertz23");
	    statement.setString(3, "123456");
	    statement.setString(4, "Polywertz23@gmail.com");
	    statement.executeUpdate();
	}
	
	

}
