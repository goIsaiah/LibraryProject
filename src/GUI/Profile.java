package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Component;
import net.miginfocom.swing.MigLayout;

public class Profile {

	
	
	
	//____________________________________________________________
	private JFrame frame;
	private JTextField txtBookmate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Frame 
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(236, 236, 236));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1080, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// sidebar
		JPanel panel = new JPanel();
		panel.setBackground(new Color(11, 106, 203));
		panel.setBounds(0, 0, 61, 607);
		frame.getContentPane().add(panel);
		
		// Hamburger icon 
        URL hamURL = getClass().getResource("/Ham.png");
        ImageIcon hamIcon = new ImageIcon(hamURL);
        Image img = hamIcon.getImage();
        img = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        hamIcon = new ImageIcon(img);
        JButton hamburger = new JButton();
        hamburger.setBounds(2, 5, 50, 50);
        hamburger.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        hamburger.setIcon(hamIcon);
        hamburger.setPreferredSize(new Dimension(50,50 ));
        hamburger.setBorder(null);
        hamburger.setContentAreaFilled(false);
        panel.add(hamburger);
        
   
        
        
        //
        // Sethings 
        URL sethingUrl = getClass().getResource("/settings.png"); 
        ImageIcon sethingIcon = new ImageIcon(sethingUrl); 
        Image sethingImg = sethingIcon.getImage();
        sethingImg = sethingImg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        sethingIcon = new ImageIcon(sethingImg);;
        
        JButton setting = new JButton();
        setting.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        panel.add(setting);
        setting.setIcon(sethingIcon);
        setting.setBorder(null); 
        setting.setContentAreaFilled(false);
        setting.setPreferredSize(new Dimension(25, 25));
        
        
        
		
		
		// Logo 
		txtBookmate = new JTextField();
		txtBookmate.setEditable(false);
		txtBookmate.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookmate.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		txtBookmate.setText("BookMate");
		txtBookmate.setBounds(112, 19, 140, 33); 
		frame.getContentPane().add(txtBookmate);
		txtBookmate.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 68, 69));
		panel_1.setBounds(154, 68, 125, 125);
		frame.getContentPane().add(panel_1);
		
		
		
		
		
		
		// Info 
		JLabel ProfileName = new JLabel("Profile Name");
		ProfileName.setBounds(309, 68, 90, 16);
		frame.getContentPane().add(ProfileName);
		
		JLabel Email = new JLabel("email@gmail.com");
		Email.setBounds(310, 90, 117, 16);
		frame.getContentPane().add(Email);
		
		JLabel clubName = new JLabel("Club Name");
		clubName.setBounds(309, 109, 90, 16);
		frame.getContentPane().add(clubName);
		
		JLabel noBooksRead = new JLabel("#Read");
		noBooksRead.setBounds(439, 142, 90, 16);
		frame.getContentPane().add(noBooksRead);
		
		JLabel noFollowers = new JLabel("#Followers");
		noFollowers.setBounds(654, 142, 90, 16);
		frame.getContentPane().add(noFollowers);
		
		JLabel numRead = new JLabel("10");
		numRead.setHorizontalAlignment(SwingConstants.CENTER);
		numRead.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		numRead.setBounds(429, 170, 61, 16);
		frame.getContentPane().add(numRead);
		
		JLabel numFollowers = new JLabel("10");
		numFollowers.setHorizontalAlignment(SwingConstants.CENTER);
		numFollowers.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		numFollowers.setBounds(660, 169, 61, 16);
		frame.getContentPane().add(numFollowers);
	}
}
