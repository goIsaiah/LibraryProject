package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

public class MainPage extends JPanel{
	
	public MainPage() {
		
	}
    public MainPage(Component component) {
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);

        //SEARCH_BAR
        add(component, "cell 2 0");
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        //MAIN_PAGE_RESULTS
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        for (int i = 1; i < 7; i++) {
        	JLabel label3 = new JLabel("    �This is a title of a book �  - �Author� - �Genre�- � Rating� -�ISBN�(Optional)    ");
        	JLabel label4 = new JLabel("   Add    ");
        	border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            label3.setBorder(border);
            label4.setBorder(border);
            label3.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
            label3.setPreferredSize(new Dimension(600, 50));
            String cell = "cell 0 " + i;
            String cell2 = "cell 1 " + i;
            add(label3, cell);
            add(label4, cell2);
        }
    }

}
