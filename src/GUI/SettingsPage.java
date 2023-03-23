package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Databases.DBUserInfo;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

public class SettingsPage extends JPanel{
    User currUser;

    public SettingsPage(User user) throws SQLException{
        this.currUser=user;
        framePanel("Settings");
        
    }

    private void framePanel(String labelString) throws SQLException{
        setLayout(new MigLayout("", "[][grow]", "[]30[][][][]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);

        // BOOKMATE_LABEL
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(label, "cell 0 0");

        // FIRST_NAME_FIELD
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(firstNameLabel, "cell 0 1");

        JTextField firstNameField = new JTextField(currUser.getFirstName());
        firstNameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        add(firstNameField, "cell 1 1, width 200, growx");

        // LAST_NAME_FIELD
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(lastNameLabel, "cell 0 2");

        JTextField lastNameField = new JTextField(currUser.getLastName());
        lastNameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        add(lastNameField, "cell 1 2, width 200, growx");

        // BIO_FIELD
        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(bioLabel, "cell 0 3");

        JTextArea bioField = new JTextArea(currUser.getBio());
        bioField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        JScrollPane bioScrollPane = new JScrollPane(bioField);
        bioScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bioScrollPane.setPreferredSize(new Dimension(200, 100));
        add(bioScrollPane, "cell 1 3, grow, gapbottom 20");

        // USER_URL_FIELD
        JLabel userUrlLabel = new JLabel("Profile URL:");
        userUrlLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(userUrlLabel, "cell 0 4");
        JTextField userUrlField = new JTextField(currUser.getURL());
        userUrlField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        add(userUrlField, "cell 1 4, width 200, growx, gapbottom 20");
        
        //SUBMIT_BUTTON
        JButton submit = new JButton("   Submit Changes    ");
        add(submit, "cell 0 5");
        
        submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String bio = bioField.getText();
				String url = userUrlField.getText();
				currUser.setBio(bio);
				currUser.setFirstName(firstName);
				currUser.setLastName(lastName);
				currUser.setURL(url);
				DBUserInfo db;
				try {
					db = new DBUserInfo();
					db.addUserInfo(currUser);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
        });
        
    }


}

