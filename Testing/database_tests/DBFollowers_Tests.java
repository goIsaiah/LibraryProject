package database_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Databases.DBType_enum;
import Databases.DBUser;
import Databases.DBUtil;
import DomainObjects.User;
import GUI.LibraryUI;

class DBFollowers_Tests 
{
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
	
//	@Test
//	void getAllFriendId_test_01() {
//		String query = "SELECT * FROM FOLLOWERS where user_id = 2";
//		try {
//			Statement m = con.createStatement();
//			ResultSet resultset = m.executeQuery(query); 
//			while(resultset.next()) {
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		
//		
//	}
	
	@Test
	void getFriendsIDList() throws SQLException
	{
		ArrayList<Integer> friendsList = new ArrayList<>();
		Databases.DBFollowers db = new Databases.DBFollowers();
		User u = new User("abbey22", "1224", "a.jarmillan@gmail.com");
		DBUser dbUser = new DBUser(); 
		
		try
		{
			friendsList = db.getAllFriend_id(dbUser.getUserId(u));
			assertEquals(friendsList.get(0), 2);
		}
		catch (SQLException e)
		{
			fail("friendsList not properly returned");
		}
	}

}
