package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Databases.DBType_enum;
import Databases.DBUserInfo;
import Databases.DBUtil;
import DomainObjects.User;

public class FrameTest {

	
	public static void main(String[] a) {
		JFrame frame = new JFrame(); 
		frame.setBounds(0, 0, 300, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		FriendPanel panel = new FriendPanel();
		frame.add(panel.getPane() ); 
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
		Connection conn = DBUtil.getConnection(DBType_enum.ONLINE); 
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
	private JPanel photo; 
	
	public FriendProfile(String text) {
		init(); 
		label = new JLabel();
		label.setText("<html>"+ text.replaceAll("\n", "<br/>") +"</html>");
	   	 setPhoto(); 
		add(photo, BorderLayout.WEST);
		add(label, BorderLayout.CENTER);
	}
	
	private void init() {
		setFont(new Font("Arial", Font.BOLD, 20));
		BorderLayout layout = new BorderLayout(); 
		layout.setHgap(5);
		setPreferredSize(new Dimension(300, 50));
		this.setLayout(layout);
	}
	
	private void setPhoto() {
		photo = new JPanel();
		ImageIcon profilePic = null;
    	try {
    		profilePic = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"));
    		JLabel picLabel = photoLabelConfig(profilePic);
    		photo.add(picLabel);
		} catch (MalformedURLException e1) {
		}
		
	}
	private JLabel photoLabelConfig(ImageIcon profilePic) {
	       Image scaledImage = profilePic.getImage().getScaledInstance(30, -1, Image.SCALE_SMOOTH); // scale the image
	        ImageIcon scaledIcon = new ImageIcon(scaledImage);
	        JLabel picLabel = new JLabel(scaledIcon);
	        picLabel.setPreferredSize(new Dimension(30, 30)); // set fixed size
	        picLabel.setMinimumSize(new Dimension(30, 30)); // set minimum size to prevent distortion
	        picLabel.setMaximumSize(new Dimension(30, 30)); // set maximum size to prevent stretching
	        
		return picLabel; 
	}
	
   public void setPhoto(String url)  {
	   photo.removeAll();
	   photo.revalidate();
	   photo.repaint();
		
        ImageIcon profilePic;
        try {
            profilePic = new ImageIcon(new URL(url));
            photo.add(  photoLabelConfig(profilePic) );
          
        } catch (MalformedURLException e) {
        	try {
				profilePic = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"));
			    photo.add(  photoLabelConfig(profilePic) );
        	} catch (MalformedURLException e1) {
			}
        }
        
     

       
	}
	


}







