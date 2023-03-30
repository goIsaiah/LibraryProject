package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Databases.DBUser;
import GUI.LibraryUI;

class DBUser_FriendList_Test {
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String user = "root";
	static String password = "Fade2black";

	@Test
	void test() {
		DBUser usr = new DBUser();
		
//		ArrayList<String> list = new ArrayList<>(); 
//		
//		list.add("Polywertz Polywertz@gmail.com ");
//		list.add("JohnDoe john_doe@gmail.com ");
//		list.add("janewest jane@gmail.com");
		try {
			ArrayList<String> list = usr.getUserInfo();
			for(int i = 0 ; i< list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			System.out.println("incorrect password"); 
			System.out.println(e.getSQLState()); 
			System.out.println(e.getMessage()); 
		} 
		
		
	}

}
