package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class RegisterFrame extends Template {

	private JPanel contentPane;
	private Container container = getContentPane();
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		
		super();
		
    	//CONTENT_FRAME
        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        content.setBackground(Color.white);
        container.add(content, BorderLayout.CENTER);
        content.setLayout(null);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setBounds(57, 39, 146, 39);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label);
        
        //SEARCH_BAR
        content.add(searchBar(), "cell 1 0");
        
        //USERNAME_FIELD
        usernameField = new JTextField();
        //usernameField.setColumns(10);
        usernameField.setPreferredSize(new Dimension(338, 41));
        Dimension userSize = usernameField.getPreferredSize();
        usernameField.setBounds(57, 109, 338, 41);
        content.add(usernameField);
        
        //PASSWORD_FIELD
        passwordField = new JTextField();
        //passwordField.setColumns(10);
        passwordField.setPreferredSize(new Dimension(338, 41));
        Dimension passwordSize = passwordField.getPreferredSize();
        
        //EMAIL_FIELD
        emailField = new JTextField();
        //emailField.setColumns(10);
        emailField.setPreferredSize(new Dimension(338, 41));
        Dimension emailSize = emailField.getPreferredSize();
        emailField.setBounds(57, 299, 338, 41);
        content.add(emailField);
        passwordField.setBounds(57, 489, 338, 41);
        content.add(passwordField);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(57, 158, 338, 131);
        content.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(57, 365, 1, 2);
        content.add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(57, 350, 338, 131);
        content.add(separator_2);
        
        closeOP();
		
		/*
		setTitle("BookMate");
        
        //ICON
        URL iconUrl = getClass().getResource("/Icon.png");
        ImageIcon icon = new ImageIcon(iconUrl);
        setIconImage(icon.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//SIDEBAR
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(75, 0));
        sidebar.setBackground(Color.decode("#0B6BCC"));
        container.add(sidebar, BorderLayout.WEST);
        
        JPanel content = new JPanel(new MigLayout("", "[]30[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");
        container.add(content, BorderLayout.CENTER);

		setContentPane(contentPane);
		closeOP();
		*/
	}
}