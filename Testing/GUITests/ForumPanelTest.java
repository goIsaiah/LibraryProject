package GUITests;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;

import DomainObjects.User;
import GUI.ForumPanel;
import GUI.LibraryUI;

public class ForumPanelTest {
	
	public static void main(String[] args) {
		User user = new User("JohnDoe", "1234", "vincegab@my.yorku.ca"  );
		String book_title = "1984"; 
		
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter mysql password: " );
			LibraryUI.sqlpassword = input.next();
		}
		ForumPanel forum = null;
		try {
			forum = new ForumPanel(user, book_title, 2);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		JFrame frame = new JFrame(); 
		frame.setPreferredSize(new Dimension(350, 500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.add(forum);
	}
}
