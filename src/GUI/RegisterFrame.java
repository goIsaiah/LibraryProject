package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
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
        JPanel content = new JPanel(new MigLayout("", "[]30[grow]", "[]30[][][][][]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        content.setBackground(Color.white);
        container.add(content, BorderLayout.CENTER);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");
        
        //SEARCH_BAR
        content.add(searchBar(), "cell 1 0");
        
        //USERNAME_FIELD
        usernameField = new JTextField();
        content.add(usernameField, "cell 1 5,growx");
        usernameField.setColumns(10);
        usernameField.setPreferredSize(new Dimension(338, 41));
        Dimension userSize = usernameField.getPreferredSize();
        usernameField.setBounds(101, 229, userSize.width, userSize.height);
        
        //EMAIL_FIELD
        emailField = new JTextField();
        content.add(emailField, "cell 1 5,growx");
        emailField.setColumns(10);
        emailField.setPreferredSize(new Dimension(338, 41));
        Dimension emailSize = emailField.getPreferredSize();
        emailField.setBounds(101, 291, emailSize.width, emailSize.height);
        
        //PASSWORD_FIELD
        passwordField = new JTextField();
        content.add(emailField, "cell 1 5,growx");
        passwordField.setColumns(10);
        passwordField.setPreferredSize(new Dimension(338, 41));
        Dimension passwordSize = passwordField.getPreferredSize();
        passwordField.setBounds(101, 346, passwordSize.width, passwordSize.height);
        
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
