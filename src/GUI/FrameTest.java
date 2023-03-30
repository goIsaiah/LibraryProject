package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Databases.DBUser;
import net.miginfocom.swing.MigLayout;

public class FrameTest {
	static String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9609229";
	static String user = "sql9609229";
	static String password = "yUHweNy2bX";
	
	public static void main(String[] a) {
		JFrame frame = new JFrame(); 
		frame.setBounds(0, 0, 800, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(new FriendList()); 
		frame.pack();
		
		
		try {
			ArrayList<String> list = getUserInfo();
			for(int i = 0 ; i< list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			System.out.println("password not correct"); 
		} 
		
	}
	
	public static ArrayList<String> getUserInfo() throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, password); 
		String query = "Select USERNAME, EMAIL from USERTABLE;"; 
		Statement statement= conn.createStatement(); 
		ResultSet set = statement.executeQuery(query); 
		ArrayList<String> list = new ArrayList<>(); 
		
		while(set.next()) {
			list.add((set.getString(1) + "\n" + set.getString(2) + "\n")); 
		}
		return list; 
	}
	
}

class FriendProfile extends JPanel{
	private JLabel label; 
	public FriendProfile() {
		label = new JLabel("Name"); 
		setPreferredSize(new Dimension(300, 40));
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		add(label, BorderLayout.WEST); 
	}
	
}


class FriendList extends JPanel{
	
	public FriendList() {
		this.setPreferredSize(new Dimension(300 , 500));
		this.setLayout(new MigLayout("", "[][]", "[][][]"));
		addContent();
	}
	
	private void addContent() {
		FriendProfile label1 = new FriendProfile(); 

		label1.setFont(new Font("Arial", Font.BOLD, 20));
		
		this.add(label1, "cell 0 0"); 

	}
	
	
}




