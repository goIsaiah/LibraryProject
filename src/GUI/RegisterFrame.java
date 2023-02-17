package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterFrame extends JPanel {

	private JTextField usernameField;
	private JTextField emailField;
	private JTextField passwordField;
	
	public RegisterFrame() {
		
	}
	
	public RegisterFrame(Component component) {
		
    	//CONTENT_FRAME
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        setLayout(null);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setBounds(57, 39, 146, 39);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label);
        
        //SEARCH_BAR
        add(component, "cell 1 0");
        
        //USERNAME_FIELD
        usernameField = new JTextField();
        usernameField.setText("Username");
        //usernameField.setColumns(10);
        usernameField.setPreferredSize(new Dimension(338, 41));
        Dimension userSize = usernameField.getPreferredSize();
        usernameField.setBounds(57, 109, 338, 41);
        add(usernameField);
        
        //PASSWORD_FIELD
        passwordField = new JTextField();
        passwordField.setText("Password");
        //passwordField.setColumns(10);
        passwordField.setPreferredSize(new Dimension(338, 41));
        Dimension passwordSize = passwordField.getPreferredSize();
        
        //EMAIL_FIELD
        emailField = new JTextField();
        emailField.setText("Email");
        //emailField.setColumns(10);
        emailField.setPreferredSize(new Dimension(338, 41));
        Dimension emailSize = emailField.getPreferredSize();
        emailField.setBounds(57, 299, 338, 41);
        add(emailField);
        passwordField.setBounds(57, 489, 338, 41);
        add(passwordField);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(57, 158, 338, 131);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(57, 365, 1, 2);
        add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(57, 350, 338, 131);
        add(separator_2);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		File file = new File("userDatabase.txt");
        		PrintWriter printWriter;
        		try {
        			printWriter = new PrintWriter(new FileWriter(file, true));
        			printWriter.println(usernameField.getText());
            		printWriter.println(emailField.getText());
            		printWriter.println(passwordField.getText());
            		printWriter.close();
        		} catch (IOException ex) {
        			
        		}
        	}
        });
        registerButton.setBounds(57, 682, 85, 21);
        add(registerButton);
        
        JSeparator separator_2_1 = new JSeparator();
        separator_2_1.setBounds(57, 540, 338, 131);
        add(separator_2_1);
        
		
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
