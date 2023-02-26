package pwDB;

import java.sql.*;

public class DBMain {
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String user = "root";
	static String password = "1977";
	static String query = "show tables;";
	static String createString =
			"create table LIBRARY " + "(LIB_ID integer NOT NULL, " +
		    "SUP_NAME varchar(40) NOT NULL, " + "STREET varchar(40) NOT NULL, " +
		    "CITY varchar(20) NOT NULL, " + "STATE char(2) NOT NULL, " +
		    "ZIP char(5), " + "PRIMARY KEY (SUP_ID))";
			
	
	
	public static void main(String args[]) {
		batchInsert();
	}

	private static void batchInsert() {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			statement.executeUpdate(createString);
			
			
			
			
			
			String selectString = "SELECT * FROM SUPPLIERS";
			ResultSet resultSet = statement.executeQuery(selectString);
			
			
			
			
			while (resultSet.next()) {
			    int id = resultSet.getInt("SUP_ID");
			    String name = resultSet.getString("SUP_NAME");
			    String street = resultSet.getString("STREET");
			    String city = resultSet.getString("CITY");
			    String state = resultSet.getString("STATE");
			    String zip = resultSet.getString("ZIP");
			    System.out.printf("%d %s %s %s %s %s%n", id, name, street, city, state, zip);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
