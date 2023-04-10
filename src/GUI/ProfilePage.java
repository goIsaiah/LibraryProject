package GUI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.*;

import Databases.DBUserInfo;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

public class ProfilePage extends JPanel{
    private User currUser;

    public ProfilePage(User user) throws SQLException{
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        this.currUser=user;
        framePanel(currUser.getUsername());
    }

    protected void framePanel(String labelString) throws SQLException {
        setLayout(new MigLayout("", "[][]", "[][][]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        DBUserInfo db = new DBUserInfo();
        currUser = db.getUserInfo(currUser);
        
        setBookMateLabel(labelString);   // BOOKMATE_LABEL
        setPhoto();   // PROFILE_PICTURE
        setProfileInfo();// USER_INFO
        addFriendsList();
    }
    
    private void addFriendsList() {
    	FriendsList friendList = new FriendsList(currUser); 
    	add(friendList, "cell 0 7");
    }
    
    private void setBookMateLabel(String labelString) {
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(label, "cell 0 0");
    }
    
    private void setProfileInfo() {
        JLabel nameLabel = new JLabel(currUser.getFirstName() + " " + currUser.getLastName());
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(nameLabel, "cell 0 1, aligny top");

        JTextArea bioArea = new JTextArea(currUser.getBio());
        bioArea.setColumns(30);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setEditable(false);
        bioArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        
        JScrollPane sp = new JScrollPane();
        sp.setPreferredSize(new Dimension(900, 100));
        sp.setViewportView(bioArea);
        add(sp, "cell 1 2");
        
        
    }
    
    
    private void setPhoto() {
    	 String profilePicURL = currUser.getURL();
         ImageIcon profilePic = null;
         try {
             profilePic = new ImageIcon(new URL(currUser.getURL()));
         } catch (MalformedURLException e) {
         	try {
 				profilePic = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"));
 			} catch (MalformedURLException e1) {
 			}
         }
         Image scaledImage = profilePic.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH); // scale the image
         ImageIcon scaledIcon = new ImageIcon(scaledImage);
         JLabel picLabel = new JLabel(scaledIcon);
         picLabel.setPreferredSize(new Dimension(150, 150)); // set fixed size
         picLabel.setMinimumSize(new Dimension(150, 150)); // set minimum size to prevent distortion
         picLabel.setMaximumSize(new Dimension(150, 150)); // set maximum size to prevent stretching
         add(picLabel, "cell 0 2");
    }

}









