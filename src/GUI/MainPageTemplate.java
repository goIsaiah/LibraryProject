package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

import DomainObjects.Book;
import net.miginfocom.swing.MigLayout;

public class MainPageTemplate extends JPanel {
    public MainPageTemplate() {
    
    }
    
    public MainPageTemplate(Component component) {
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
      //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
      //SEARCH_BAR
        add(component, "cell 2 0");
    }
}
