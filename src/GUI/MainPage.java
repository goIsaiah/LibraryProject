package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.json.JSONObject;

import Databases.DBBookStatus;
import Databases.DBMain;
import DomainObjects.Book;
import DomainObjects.User;
import Logic.GoogleJSON;
import net.miginfocom.swing.MigLayout;

public class MainPage extends JPanel{
	static int numOfResults = 7;
	private Template parentTemplate;
	
    public MainPage() throws SQLException {
    	DBMain db = new DBMain();
    	ArrayList<Book> bookList = db.searchLibrary("");
    	framePanel("BookMate", 0);
        //MAIN_PAGE_RESULTS
        showBookList(bookList);
    }
    
    public MainPage(ArrayList<Book> bookList) {
    	framePanel("BookMate", 0);
        showBookList(bookList);
	}
    
    private void framePanel(String labelString, int pos) {
    	//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 " + pos);
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
		
		//Book Cover
		GoogleJSON gApi = new GoogleJSON();
		JSONObject jObj = gApi.getSearch(book.getTitle());
		jObj = gApi.getSearchIndex(jObj, 0);
		String coverUrl = gApi.getSearchCoverURL(jObj);
		System.out.println(coverUrl);
		bookCover(coverUrl);
		
		//Book Information
		framePanel(book.getTitle(), 1);
		JLabel author = new JLabel("By: " + book.getAuthor());
        author.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
        add(author, "cell 0 2");
        JLabel isbn = new JLabel("ISBN-13: "  + book.getIsbn());
        isbn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(isbn, "cell 0 3");
        JLabel yearPub = new JLabel("Published: "  + book.getYearPublished());
        yearPub.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(yearPub, "cell 0 4");
        
        //Buttons
        JButton forumButton = new JButton("   Open Comments    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        parentTemplate.setBook(book);
        forumButton.addActionListener(e -> parentTemplate.showPanel("Comment"));
        add(forumButton, "cell 0 5");
        
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        parentTemplate.setBook(book);
        forumButton.addActionListener(e -> parentTemplate.showPanel("Comment"));
        
        
        JButton citationButton = new JButton("   Citation Machine    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        citationButton.addActionListener(e -> parentTemplate.showPanel("Citation"));
        add(citationButton, "cell 0 6");
        JButton critic = new JButton("   Critic Zone    ");
        parentTemplate = (Template)SwingUtilities.getWindowAncestor(this);
        critic.addActionListener(e -> parentTemplate.showPanel("Critic"));
        add(critic, "cell 0 7");
        
        DBBookStatus dbStatus = new DBBookStatus();
        boolean available = dbStatus.isBookAvailable(book);
        User user = parentTemplate.getUser();
        boolean isUsers = dbStatus.userHasBook(book, user);
        JButton checkOut = new JButton();
        System.out.println("CONSOLE LOG: " + available + isUsers);
        
        
        if (isUsers) {
        	JLabel dueDate = new JLabel(dbStatus.getDueDate(book));
        	add(dueDate, "cell 1 8");
        	checkOut.setText(" Return  ");
        	checkOut.addActionListener(e -> {
    			dbStatus.returnBook(book);
    			repaint();
    		});
        } else if (available && !isUsers) {
        	checkOut.setText("  Borrow  ");
        	checkOut.addActionListener(e -> {
    			dbStatus.checkOut(book, Template.user);
    		}); 
        } else {
        	checkOut.setText("  Taken out  ");
        }
        
        add(checkOut, "cell 0 8");
        
	}

	private void bookCover(String coverUrl) {
		 try {
		        // Download the cover image from the URL
		        URL url = new URL(coverUrl);
		        BufferedImage image = ImageIO.read(url);
		        
		        int maxWidth = 150;
		        int maxHeight = 300;
		        
		        // Calculate the scale factor to fit within the specified maximum width and height
		        double scale = Math.min((double) maxWidth / image.getWidth(), (double) maxHeight / image.getHeight());

		        // Scale the image
		        int scaledWidth = (int) (image.getWidth() * scale);
		        int scaledHeight = (int) (image.getHeight() * scale);
		        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

		        // Create a JLabel with the scaled image and add it to the panel in cell 0 1
		        JLabel label = new JLabel(new ImageIcon(scaledImage));
		        add(label, "cell 0 1");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

}