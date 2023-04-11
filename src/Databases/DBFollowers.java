package Databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import GUI.LibraryUI;

public class DBFollowers {
	
	protected static Connection conn; 
	
	public DBFollowers() throws SQLException{
		conn = DBUtil.getConnection(DBType_enum.ONLINE);
	}
	
	
	public ArrayList<Integer> getAllFriend_id(int user_id) throws SQLException{
		ArrayList<Integer> list = new ArrayList<>(); 
		String query = "Select * from FOLLOWERS"; 
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query); 
		
		while(r.next()) {
			if(r.getInt(1) == user_id) {
				list.add(r.getInt(2)); 
			}
		}
		
		return list;
	}
	
	
}
