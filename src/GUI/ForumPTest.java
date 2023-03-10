package GUI;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

import DomainObjects.User;

public class ForumPTest {
	
	
	public static void main(String[] args) throws SQLException {
		
		User user = new User("JohnDoe", "1234", "vincegab@my.yorku.ca"  );
		String book_title = "1984"; 
		
		ForumPanel forum = new ForumPanel(user, book_title, 2); 
		JFrame frame = new JFrame(); 
		frame.setPreferredSize(new Dimension(350, 500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.add(forum);
	}
}