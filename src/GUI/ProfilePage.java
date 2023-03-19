package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.*;

import DomainObjects.User;
import GUI.Template;
import net.miginfocom.swing.MigLayout;

public class ProfilePage extends JPanel{
	
	public ProfilePage(User user) throws SQLException{
		setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);

        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        // PROFILE PHOTO
        JPanel photo = addProfilePhoto();
        add(photo, "cell 0 1");
        JPanel userInfo = addProfileInfo(user); 
        add(userInfo , "cell 1 1");
	}


	public JPanel addContentPanel() {
		return new JPanel(new MigLayout("", "[]20[]200[]", "[]30[]"));
	}
	
	public JPanel addProfilePhoto() {
		JPanel photo = new JPanel(); 
		photo.setPreferredSize(new Dimension(125, 125));
		photo.setBackground(Color.decode("#714545"));
		return photo; 
	}
	
	public JPanel addProfileInfo(User user) {
		MigLayout layout = new MigLayout("", "[]300[]250[]" , "[]10[]10[]20[][]") ; 
		JPanel info = new JPanel(layout);
		
		
		JLabel profileName = new JLabel(user.getUsername()) ; 
		JLabel email = new JLabel(user.getEmail()); 
		JLabel numRead = new JLabel("0"); 
		
		JLabel numFollowers = new JLabel("0");
		JLabel noRead = new JLabel("#Read");
		JLabel noFollowers  = new JLabel("#Followers");
		
		profileName.setFont(new Font(Font.SANS_SERIF, Font.BOLD,15));
		numRead.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,12));
		numFollowers.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,12));
		noFollowers.setFont(new Font(Font.SANS_SERIF, Font.BOLD,13));
		noFollowers.setFont(new Font(Font.SANS_SERIF, Font.BOLD,13));
		
		
		info.add(profileName, "cell 0 0");
		info.add(email, "cell 0 1");
		info.add(noRead, "cell 1 2");
		info.add(noFollowers, "cell 2 2");
		info.add(numRead, "cell 1 3 3 1");
		info.add(numFollowers, "cell 2 3 3 1");
		
		return info ; 
		
	}
	
	
}
