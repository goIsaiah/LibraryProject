package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import Databases.DBMain;
import DomainObjects.Book;
import net.miginfocom.swing.MigLayout;

public class MainPage extends JPanel{
	static int numOfResults = 7;
	private Template parentTemplate;
	
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
		
		//Book Information
		framePanel(book.getTitle());
		JLabel author = new JLabel("By: " + book.getAuthor());
        author.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
        add(author, "cell 0 1");
        JLabel isbn = new JLabel("ISBN-13: "  + book.getIsbn());
        isbn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(isbn, "cell 0 2");
        JLabel yearPub = new JLabel("Published: "  + book.getYearPublished());
        yearPub.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(yearPub, "cell 0 3");
        
        //Buttons
        JButton forumButton = new JButton("   Open Comments    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        parentTemplate.setBook(book);
        forumButton.addActionListener(e -> parentTemplate.showPanel("Comment"));
        add(forumButton, "cell 0 4");
        JButton criticButton = new JButton("   Open Critics    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        parentTemplate.setBook(book);
        criticButton.addActionListener(e -> parentTemplate.showPanel("Critic"));
        add(criticButton, "cell 0 5");
        JButton citationButton = new JButton("   Citation Machine    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        citationButton.addActionListener(e -> parentTemplate.showPanel("Citation"));
        add(citationButton, "cell 0 6");
	}

}
