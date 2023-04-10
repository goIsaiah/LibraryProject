package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.*;

import Databases.DBUser;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

public class WelcomePage extends JPanel{
	 private Template parentTemplate;
	 
    public WelcomePage() throws SQLException {
	
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        framePanel("Welcome to BookMate!");

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 50)); // set preferred size
        add(registerButton, "cell 0 1, align left"); // below the label, in column 0

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50)); // set preferred size
        add(loginButton, "cell 0 2, align left");
        
        registerButton.addActionListener(e -> {
			try {
				showRegisterPanel();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
        
        loginButton.addActionListener(e -> {
			try {
				showLoginPanel();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
        
    }

	private void showLoginPanel() throws SQLException{
		removeAll();
		revalidate();
		repaint();
		framePanel("Login");
		JLabel usernameLabel = new JLabel("Username:");
	    JTextField usernameField = new JTextField(20);
	    add(usernameLabel, "cell 0 1");
	    add(usernameField, "cell 1 1");
		
	    JLabel passwordLabel = new JLabel("Password:");
	    JPasswordField passwordField = new JPasswordField(20);
	    add(passwordLabel, "cell 0 2");
	    add(passwordField, "cell 1 2");
	    
	    JButton loginButton = new JButton("Login");
	    add(loginButton, "cell 0 3");
	    
	    parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
	    loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		logUserIn(usernameField, passwordField);
        	}
        });
	 
	    
	    passwordField.addKeyListener(new KeyAdapter()
		{

			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					logUserIn(usernameField, passwordField);
				}
			}
		});
	}

	private void showRegisterPanel() throws SQLException{
		removeAll();
		revalidate();
		repaint();
		framePanel("Register");
		JLabel emailLabel = new JLabel("Email:");
	    JTextField emailField = new JTextField(20);
	    add(emailLabel, "cell 0 1");
	    add(emailField, "cell 1 1");

	    JLabel usernameLabel = new JLabel("Username:");
	    JTextField usernameField = new JTextField(20);
	    add(usernameLabel, "cell 0 2");
	    add(usernameField, "cell 1 2");

	    JLabel passwordLabel = new JLabel("Password:");
	    JPasswordField passwordField = new JPasswordField(20);
	    add(passwordLabel, "cell 0 3");
	    add(passwordField, "cell 1 3");

	    JButton registerButton = new JButton("Register");
	    add(registerButton, "cell 0 4");
	    
	    parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				boolean check;
				try {
					DBUser db = new DBUser();
					check = db.checkUserExists(usernameField.getText(), emailField.getText());
					if (!check) {
						db.addUser(usernameField.getText(),passwordField.getText(), emailField.getText());
						User user = new User(usernameField.getText(),passwordField.getText(), emailField.getText());
						parentTemplate.setUser(user);
						parentTemplate.showPanel("Profile");
					}

				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
        	}
        });
	}

	private void framePanel(String labelString) {
		setLayout(new MigLayout("", "[]30[]", "[]30[]")); // set the layout with constraints
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(label, "cell 0 0");
		
	}
	
	private void logUserIn(JTextField usernameField, JTextField passwordField)
	{
		boolean check;
		try {
			DBUser db = new DBUser();
			check = db.checkLogin(usernameField.getText(), passwordField.getText());
			if (check) {
				String email = db.getEmail(usernameField.getText());
				User user = new User(usernameField.getText(),passwordField.getText(), email);
				System.out.println(user.getEmail());
				parentTemplate.setUser(user);
				parentTemplate.showPanel("Profile");
			}
			else
			{
				//add incorrect password popup
				JFrame incorrectinput = new JFrame();
				JLabel incorrectinputlabel = new JLabel("Incorrect username or password", SwingConstants.CENTER);
				JButton ok = new JButton();
				ok.setText("OK");
				incorrectinputlabel.setFont(incorrectinputlabel.getFont().deriveFont(Font.BOLD, 14f));
				incorrectinput.setPreferredSize(new Dimension(300 , 150));
				incorrectinput.setSize(new Dimension(300 , 150));
				incorrectinput.setLayout(new MigLayout("", "[]", "[]10[][]" ));
				incorrectinput.add(incorrectinputlabel);
				incorrectinput.add(ok, "cell 0 3");
				incorrectinput.setLocationRelativeTo(null);
				incorrectinput.setVisible(true);
				incorrectinput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ok.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						incorrectinput.dispose();
					}

				});
				ok.addKeyListener(new KeyAdapter()
				{

					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode()==KeyEvent.VK_ENTER) {
							incorrectinput.dispose();
						}
					}
				});
			}

		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	

}
