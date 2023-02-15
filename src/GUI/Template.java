package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;

public class Template extends JFrame {

	public Template() {
		// WINDOW_NAME
		setTitle("BookMate");
		// ICON
		URL iconUrl = getClass().getResource("/Icon.png");
		ImageIcon icon = new ImageIcon(iconUrl);
		setIconImage(icon.getImage());
		// SIDEBAR
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		JPanel sidebar = new JPanel();
		sidebar.setPreferredSize(new Dimension(75, 0));
		sidebar.setBackground(Color.decode("#0B6BCC"));
		container.add(sidebar, BorderLayout.WEST);
		// HAMBURGER_MENU
		URL hamURL = getClass().getResource("/Ham.png");
		ImageIcon hamIcon = new ImageIcon(hamURL);
		Image img = hamIcon.getImage();
		img = img.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		hamIcon = new ImageIcon(img);
		JButton hamburger = new JButton();
		hamburger.setIcon(hamIcon);
		hamburger.setPreferredSize(new Dimension(75, 75));
		hamburger.setBorder(null);
		hamburger.setContentAreaFilled(false);
		sidebar.add(hamburger);
		
		JButton home = sideButton("/home.png"); 
		JButton profile = sideButton("/profile.png"); 
		sidebar.add(home);
		sidebar.add(profile);
		// 

	}
	
	private JButton sideButton(String s) {
		
		URL homeUrl = getClass().getResource(s);
		ImageIcon homeIcon = new ImageIcon(homeUrl);
		Image img = homeIcon.getImage();
		img = img.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(img);
		JButton home = new JButton();
		home.setIcon(homeIcon);
		home.setPreferredSize(new Dimension(35, 35));
		home.setBorder(null);
		home.setContentAreaFilled(false);
		
		return home ; 
		
		
	}
	
	

 	public void closeOP() {
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public Component searchBar() {
		//SEARCH_FIELD
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(0, 0, 0, 0) // Add left margin for the text
        ));
        searchField.setBackground(new Color(240, 240, 240));

        //SEARCH_ICON
        URL searchURL = getClass().getResource("/searchIcon.png");
        ImageIcon searchIcon = new ImageIcon(searchURL);
        Image img = searchIcon.getImage();
        img = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(img);
        JLabel searchIconLabel = new JLabel(searchIcon);

        //SEARCH_PANEL
        JPanel searchPanel = new JPanel(new MigLayout("", "[]10[]"));
        searchPanel.add(searchIconLabel,"cell 0 0");
        searchPanel.add(searchField, "cell 1 0");

        //SEARCH_BUTTON
        JButton searchButton = new JButton("Search");
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchPanel.add(searchButton, "cell 2 0");
		return searchPanel;

	}

}
