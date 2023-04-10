package database_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import Databases.DBType_enum;
import Databases.DBUtil;
import GUI.LibraryUI;

class DBFollowers_Tests {
	public Connection con; 
	@Test
	public void init() {
		try {
			con = DBUtil.getConnection(DBType_enum.ONLINE);
			LibraryUI.conn = con; 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	void getAllFriendId_test_01() {
		String query = "SELECT * FROM FOLLOWERS where user_id = 2";
		try {
			Statement m = con.createStatement();
			ResultSet resultset = m.executeQuery(query); 
			while(resultset.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}

}
