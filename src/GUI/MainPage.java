package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
    	framePanel("BookMate");
        //MAIN_PAGE_RESULTS
        showBookList(bookList);
    }
    
    public MainPage(ArrayList<Book> bookList) {
    	framePanel("BookMate");
        showBookList(bookList);
	}
    
    private void framePanel(String labelString) {
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
    }
    
    private void showBookList(ArrayList<Book> bookList) {
    	String padding = "        ";
    	Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
    	for (int i = 0; i < bookList.size() && i < numOfResults; i++) {
        	String title = bookList.get(i).getTitle();
        	String author = bookList.get(i).getAuthor();
        	JLabel label = new JLabel(padding + title + " - " + author);
        	JButton bookButton = new JButton("   Open    ");
        	border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            label.setBorder(border);
            bookButton.setBorder(border);
            label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
            label.setPreferredSize(new Dimension(600, 50));
            String cell = "cell 0 " + (i+1);
            String cell2 = "cell 1 " + (i+1);
            int bookIndex = i;
            bookButton.addActionListener(e -> showBookPanel(bookList.get(bookIndex)));
            add(label, cell);
            add(bookButton, cell2);
        }
    }

	private void showBookPanel(Book book) {
		removeAll();
		revalidate();
		repaint();
		framePanel(book.getTitle());
		
	}

}
