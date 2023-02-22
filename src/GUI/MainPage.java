package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Databases.bookmain;
import DomainObjects.Book;
import net.miginfocom.swing.MigLayout;

public class MainPage extends JPanel{
	
	public MainPage(Book book) {
		String padding = "               ";
		//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        JLabel label3 = new JLabel(padding + book.getTitle()+ " - Author: " + book.getAuthor() + " - Rated: " + book.getRating() + " stars " + padding);
        JLabel label4 = new JLabel("   Add    ");
        border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        label3.setBorder(border);
        label4.setBorder(border);
        label3.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
        add(label3, "cell 0 1");
        add(label4, "cell 1 1");
        
	}
    public MainPage() {
    	String padding = "               ";
    	bookmain books = new bookmain();
    	ArrayList<Book> booklist = books.getList();
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        //MAIN_PAGE_RESULTS
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        for (int i = 0; i < booklist.size(); i++) {
        	String title = booklist.get(i).getTitle();
        	String author = booklist.get(i).getAuthor();
        	int rating = booklist.get(i).getRating();
        	JLabel label3 = new JLabel(padding + title + " - " + author);
        	JLabel label4 = new JLabel("   Add    ");
        	border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            label3.setBorder(border);
            label4.setBorder(border);
            label3.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
            label3.setPreferredSize(new Dimension(600, 50));
            String cell = "cell 0 " + (i+1);
            String cell2 = "cell 1 " + (i+1);
            add(label3, cell);
            add(label4, cell2);
        }
    }

}
