package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends Template {
	private Container container = getContentPane();
    public MainPage() {
    	super();	
    	
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
        
    }

	public static void main(String[] args) {
        // Create an instance of the frame
        new MainPage();
    }
}
