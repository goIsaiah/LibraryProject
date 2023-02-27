package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Databases.bookmain;
import DomainObjects.Book;
import net.miginfocom.swing.MigLayout;
import pwDB.DBMain;

public class MainPage extends JPanel{
	static int numOfResults = 7;
	
    public MainPage() throws SQLException {
    	DBMain db = new DBMain();
    	ArrayList<Book> bookList = db.searchLibrary("");
    	
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        //MAIN_PAGE_RESULTS
        showBookList(bookList);
    }
    
    public MainPage(ArrayList<Book> bookList) {
		
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
        
        showBookList(bookList);
        
	}
    
    private void showBookList(ArrayList<Book> bookList) {
    	String padding = "        ";
    	Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
    	for (int i = 0; i < bookList.size() && i < numOfResults; i++) {
        	String title = bookList.get(i).getTitle();
        	String author = bookList.get(i).getAuthor();
        	int rating = bookList.get(i).getRating();
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
