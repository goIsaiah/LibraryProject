package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.net.URL;

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
        sidebar.setPreferredSize(new Dimension(75, 0));
        sidebar.setBackground(Color.decode("#0B6BCC"));
        container.add(sidebar, BorderLayout.WEST);
        
        //HAMBURGER
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
        
        //TITLE
//        JPanel content = new JPanel(new MigLayout("gap 0 0", "[grow]", "[grow, center]"));
//        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
//        JLabel label = new JLabel("BookMate");
//        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
//        content.add(label, "dock north");
//        container.add(content, BorderLayout.CENTER);
//       
//        for (int i = 0; i < 5; i++) {
//        	JLabel label3 = new JLabel("    “This is a title of a book “  - “Author” - “Genre”- “ Rating” -”ISBN”(Optional)    ");
//            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
//            label3.setBorder(border);
//            label3.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
//            label3.setPreferredSize(new Dimension(600, 50));
//            content.add(label3, "span, wrap");
//        }
        
        JPanel content = new JPanel(new MigLayout("", "[]30[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");
        container.add(content, BorderLayout.CENTER);
        
        
        JTextField field = new JTextField("                                     ");
        field.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        field.setBorder(border);
        content.add(field, "cell 1 0");
       
        for (int i = 1; i < 7; i++) {
        	JLabel label3 = new JLabel("    “This is a title of a book “  - “Author” - “Genre”- “ Rating” -”ISBN”(Optional)    ");
        	JLabel label4 = new JLabel("   Add    ");
        	border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            label3.setBorder(border);
            label4.setBorder(border);
            label3.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
            label3.setPreferredSize(new Dimension(600, 50));
            String cell = "cell 0 " + i;
            String cell2 = "cell 1 " + i;
            content.add(label3, cell);
            content.add(label4, cell2);
        }
        
        content.setBackground(Color.white);
        
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
