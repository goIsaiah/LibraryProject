package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

    public MainPage() {
        setTitle("BookMate");
        
        //ICON
        URL iconUrl = getClass().getResource("/Icon.png");
        ImageIcon icon = new ImageIcon(iconUrl);
        setIconImage(icon.getImage());
        
        //SIDEBAR
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(225, 0));
        sidebar.setBackground(Color.decode("#0B6BCC"));
        container.add(sidebar, BorderLayout.WEST);
        
        //HAMBURGER
        URL hamURL = getClass().getResource("/Ham.png");
        ImageIcon hamIcon = new ImageIcon(hamURL);
        Image img = hamIcon.getImage();
        img = img.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
        hamIcon = new ImageIcon(img);
        JButton hamburger = new JButton();
        hamburger.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        hamburger.setIcon(hamIcon);
        hamburger.setPreferredSize(new Dimension(75, 75));
        hamburger.setBorder(null);
        hamburger.setContentAreaFilled(false);
        sidebar.add(hamburger);
        
        //TITLE
        JPanel content = new JPanel(new MigLayout());
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("   BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "dock north");
        container.add(content, BorderLayout.CENTER);
        
        
        JLabel label2 = new JLabel("TITLE ");
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        label2.setBorder(border);
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label2, "span");
        
        
//        content.setBackground(Color.BLACK);
        
        //SIZE AND LOCATION
        setSize(1200, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the frame
        new MainPage();
    }
}
