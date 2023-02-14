package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Template extends JFrame{
	
	public Template() {
		//WINDOW_NAME
		setTitle("BookMate");
		//ICON
		URL iconUrl = getClass().getResource("/Icon.png");
	    ImageIcon icon = new ImageIcon(iconUrl);
	    setIconImage(icon.getImage());	
	    //SIDEBAR
	    Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(75, 0));
        sidebar.setBackground(Color.decode("#0B6BCC"));
        container.add(sidebar, BorderLayout.WEST);
        //HAMBURGER_MENU
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
        //CLOSING_OPERATIONS
        setSize(1280, 720);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	
}
