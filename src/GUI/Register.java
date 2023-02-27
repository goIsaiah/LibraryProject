package GUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DomainObjects.User;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(44, 61, 100, 13);
		contentPane.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(104, 58, 134, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(44, 121, 100, 13);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 118, 134, 19);
		contentPane.add(passwordField);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(44, 181, 100, 13);
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBounds(104, 178, 134, 19);
		contentPane.add(emailField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(44, 228, 85, 21);
		contentPane.add(btnNewButton);

		setContentPane(contentPane);
		
		if (checkUser(usernameField.getText(), String.valueOf(passwordField.getPassword()), emailField.getText()) == true) {
			// add user to database
			User user = new User(usernameField.getText(), String.valueOf(passwordField.getPassword()), emailField.getText());
			//new Template(user);
			this.dispose();
		}
	}
	
	public boolean checkUser (String username, String password, String email) {
		// Checks the database to see if the account already exists
		return true;
	}

}