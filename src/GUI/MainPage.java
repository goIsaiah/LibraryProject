package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

public class MainPage {
	private Template temp = new Template();
	private Container container = temp.getContentPane();
    public MainPage() {
    	//CONTENT_FRAME
        JPanel content = new JPanel(new MigLayout("", "[]30[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        content.setBackground(Color.white);
        container.add(content, BorderLayout.CENTER);

        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");

        //SEARCH_BAR
        content.add(temp.searchBar(), "cell 1 0");

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
            content.add(label3, cell);
            content.add(label4, cell2);
        }

        //CLOSING_OPERATIONS
        temp.closeOP();
    }

	public static void main(String[] args) {
        new MainPage();
    }
}
