package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;



import Databases.DBType_enum;
import Databases.DBUser;
import Databases.DBUserInfo;
import Databases.DBUtil;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

public class OtherProfile extends ProfilePage {
	
	public static User other_user; 
	public static User user; 
	private DBUser db; 
	private JLabel label; 
	
	public static void main(String[] a) {
		JFrame frame = new JFrame(); 
		DBUser userdb;
		OtherProfile profile; 
		try {
			LibraryUI.conn = DBUtil.getConnection(DBType_enum.ONLINE); 
			userdb = new DBUser();
			
			 user = userdb.getUser(1); 
			other_user = userdb.getUser(3);
			profile = new OtherProfile(user); 
			frame.setLocationRelativeTo(null);
			frame.setSize(new Dimension(1920, 1080));
		
			frame.getContentPane().add(profile); 
			frame.setVisible(true);
		} catch (SQLException e) {
		} 	
		
		
	}
	
	public OtherProfile(User user) throws SQLException {
		super(user); 
		other_user = Template.userF;
		this.user = Template.user ;
		db = new DBUser(); 
		addFriendBtn();
		removeBtn(); 
		setTextPrompt("");
		
		
	}
	


	
	
	private void addFriendBtn() {
		JPanel add = makeBtn("add friend"); 
		add.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				if(!db.isFriend(user , other_user)) {
					setTextPrompt("Added");
					System.out.println("added"); 
					
					try {
					int	currId = db.getUserId(user);
					System.out.printf("currId = %d\n", currId); 
						db.beFriendUser(currId, db.getUserId(other_user)); 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					setTextPrompt("Already a friend");
					System.out.println("already a friend"); 
				}
				
				
			}

		});
		
		add(add, "cell 0 6");
		
	}
	private void removeBtn() {
		JPanel add = makeBtn("remove Friend"); 
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(db.isFriend(user, other_user)) {
					try {
						db.unFriendUser(db.getUserId(OtherProfile.user), db.getUserId(other_user));
						setTextPrompt("Removed");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					System.out.println("Not a friend"); 
					setTextPrompt("Not a friend");
				}
			}
			
		});
		
		add(add, "cell 0 6");
	}
	
	private void setTextPrompt(String msg) {
		
		if(label == null) {
			label = new JLabel(msg);	
			add(label, "cell 1 6");
		}else {
			label.setText(msg);
		}
		
		 
	}
	
	private JPanel makeBtn(String name ) {
		
		JPanel panel = new JPanel(); 
		JLabel label = new JLabel(name); 
		
		panel.setPreferredSize(new Dimension(50, 30));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(label); 
		
		return panel; 
	}
	
	
	
	
}
