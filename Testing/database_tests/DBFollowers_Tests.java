package database_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Databases.DBFollowers;
import Databases.DBType_enum;
import Databases.DBUser;
import Databases.DBUtil;
import DomainObjects.User;
import GUI.LibraryUI;

class DBFollowers_Tests 
{
	public Connection con; 
	@BeforeEach
	public void init() {
		try {
			con = DBUtil.getConnection(DBType_enum.ONLINE);
			LibraryUI.conn = con; 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	

	@Test
	public void getAllFriendId_test_01() {
		String query = "SELECT * FROM FOLLOWERS where user_id = 2";
		try {
			Statement m = con.createStatement();
			ResultSet resultset = m.executeQuery(query); 
			ArrayList<Integer> expected = new ArrayList<>(); 
			
			while(resultset.next()) {
				expected.add(resultset.getInt(2));
			}
			
			DBFollowers db = new DBFollowers(); 
			ArrayList<Integer> actual = db.getAllFriend_id(2);
			
			if(actual.size() == expected.size()) {
				int size = actual.size(); 
				boolean flag = true; 
				for(int i = 0 ; i<size ; i++) {
					flag = flag && actual.get(i) == expected.get(i);
				}
				assertTrue(flag); 
			}else {
				fail("getAllFriend_id_test failed"); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
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


	@Test
	public void getAllFriendId_test_02() {
		String query = "SELE"
				+ "CT * FROM FOLLOWERS where user_id = 3";
		try {
			Statement m = con.createStatement();
			ResultSet resultset = m.executeQuery(query); 
			ArrayList<Integer> expected = new ArrayList<>(); 
			
			while(resultset.next()) {
				expected.add(resultset.getInt(2));
			}
			
			DBFollowers db = new DBFollowers(); 
			ArrayList<Integer> actual = db.getAllFriend_id(3);
			
			if(actual.size() == expected.size()) {
				int size = actual.size(); 
				boolean flag = true; 
				for(int i = 0 ; i<size ; i++) {
					flag = flag && actual.get(i) == expected.get(i);
				}
				assertTrue(flag); 
			}else {
				fail("getAllFriend_id_test failed"); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

