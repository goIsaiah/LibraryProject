package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainPageTemplate extends Template {
	private Container container = getContentPane();
    public MainPageTemplate() {
    	super();

    	//CONTENT_FRAME
        JPanel content = new JPanel(new MigLayout("", "[]600[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 0));
        content.setBackground(Color.white);
        container.add(content, BorderLayout.CENTER);

        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");

        //SEARCH_BAR
        content.add(searchBar(), "cell 1 0");

        //CLOSING_OPERATIONS
        closeOP();
    }

//	public static void main(String[] args) {
//        new MainPage();
//    }
}
