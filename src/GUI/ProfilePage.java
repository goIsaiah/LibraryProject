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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;

import Databases.DBFollowers;
import Databases.DBUser;
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
    	JScrollPane friendList = new FriendsList(currUser); 
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



class FriendsList extends JScrollPane {
	/*
	 * TODO get all friend_id of loggedin_iser 
	 * TODO initialize all the MiniProfiles using the friend_id's
	 * TODO add event listener 
	 */
	private JPanel panel; 
    private Template parentTemplate;
    
	private User user; 
	public FriendsList(User user) {
		this.user = user; 
		panel = makeContentPanel(); 
		try {
			addContentToPanel();
			init(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void init() {
		this.setViewportView(panel);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(900, 300));
	}
	
	private JPanel makeContentPanel() {
		panel = new JPanel(); 
		panel.setPreferredSize(new Dimension(900, 300));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new MigLayout("", "[]", "[] [] [] "));
		return panel; 
	}
	
	private void addContentToPanel() throws SQLException {
		DBFollowers db_f = new DBFollowers(); 
		DBUser db = new DBUser(); 
	   DBUserInfo info = new DBUserInfo();
	   
		ArrayList<Integer> ids = db_f.getAllFriend_id(db.getUserId(this.user));
		//TODO get list of the userinfo
		Hashtable<Integer , String> set = getInfo(ids);
	 parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
		
		
		int size = ids.size(); 
		for(int i =0 ; i<size ; i++ ) {
			int id = ids.get(i);
			String value = set.get(id); 
			MiniProfile profile = new MiniProfile(value); 
			profile.setPhoto(info.getPhotoUrl(id));
            profile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	User friend = db.getUser(id);
                	parentTemplate.userF = friend; 
                	System.out.println(friend.getUsername());
//                	parentTemplate.showPanel("fProfile");
                	parentTemplate.showPanel("OtherProfile");
                    System.out.println("Yay you clicked me");
                }
            });
            panel.add(profile);
			
		}
	}
	
	public Hashtable<Integer, String> getInfo(ArrayList<Integer> keys) throws SQLException{
		DBUser db = new DBUser(); 
		Hashtable<Integer , String> allset = db.getUserInfo();
		Hashtable<Integer , String> set = new Hashtable<>(); 
		int size = keys.size(); 
		for(int i = 0 ; i<size ; i++) {
			int id = keys.get(i);
			if(allset.containsKey(id)) {
				set.put(id, allset.get(id));
			}
		}
		
		
		return set; 
	}
	
	
}









