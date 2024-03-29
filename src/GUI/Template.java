package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Databases.DBBookStatus;
import Databases.DBMain;
import DomainObjects.Book;
import DomainObjects.User;
import Logic.KeysUtil;
import net.miginfocom.swing.MigLayout;

public class Template extends JFrame {
	private JPanel panelContainer;
	private Component searchPanel;
	public static User userF;
	public static User user;
	public static Book book;
	private Container container;
	private JPanel sidebar; 
	private  JTextField searchField; 
	private  JLabel searchIconLabel; 
	public Template() throws SQLException {
		
		// WINDOW_NAME
		setTitle("BookMate");
		
		setIcon(); 
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		// SIDEBAR
		setSideBar();
		
		// HAMBURGER_MENU
		setHamBurgerMenu();
		
		//Side Buttons
		setSideButtons();
		//Panel Container 
		setPanelContainer(); 
		closeOP();
		
		
		
	}
	
	private void setPanelContainer() throws SQLException {
		WelcomePage welPanel = new WelcomePage();
		panelContainer = new JPanel(new CardLayout());
		panelContainer.add(welPanel, "Welcome");
        container.add(panelContainer, BorderLayout.CENTER);
	}
	
	private void setSideButtons() {
		JButton home = sideButton("/home.png"); 
		JButton profile = sideButton("/profile.png"); 
		JButton settings = sideButton("/settings.png"); 
		JButton checkedOut = sideButton("/library.png");
		sidebar.add(home, "cell 0 1");
		sidebar.add(profile , "cell 0 2");
		sidebar.add(checkedOut, "cell 0 3");
		sidebar.add(settings , "cell 0 4");
		//Side Button on Click
		home.addActionListener(e -> showPanel("Main")); 
		profile.addActionListener(e -> showPanel("Profile")); 
		checkedOut.addActionListener(e -> showPanel("Library"));
		settings.addActionListener(e -> showPanel("Settings")); 
		
	}
	
	private void setHamBurgerMenu() {
		URL hamURL = getClass().getResource("/Ham.png");
		ImageIcon hamIcon = new ImageIcon(hamURL);
		Image img = hamIcon.getImage();
		img = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		hamIcon = new ImageIcon(img);
		JButton hamburger = new JButton();
		hamburger.setIcon(hamIcon);
		hamburger.setPreferredSize(new Dimension(40, 40));
		hamburger.setBorder(null);
		hamburger.setContentAreaFilled(false);
		sidebar.add(hamburger , "cell 0 0");
	}
	
	private void setSideBar() {
		sidebar = new JPanel();
		sidebar.setPreferredSize(new Dimension(75, 0));
		sidebar.setBackground(Color.decode("#0B6BCC"));
		sidebar.setLayout(new MigLayout("wrap", "[]", "[]push[]5[]5[]")); 
		sidebar.setBorder(BorderFactory.createEmptyBorder(0, 10, 30, 0));
		container.add(sidebar, BorderLayout.WEST);		
	}
	
	private void setIcon() {
		// ICON
		URL iconUrl = getClass().getResource("/Icon.png");
		ImageIcon icon = new ImageIcon(iconUrl);
		setIconImage(icon.getImage());
	}
	
	public void showPanel(String string) {
	    JPanel panel = null;

	    try {
	        switch (string) {
	            case "Main":
	            	if(user != null) {
	            		panel = new MainPage();
		                break;	
	            	}
	            case "Comment":
	            	if (user != null) {
	            		panel = new CommentPage(book, user);
		                break;
	            	}
	            case "Library":
	            	if(user != null) {
	            		DBBookStatus db = new DBBookStatus();
		            	ArrayList<Book> bookList= db.searchLibrary(user.getUsername());
		                panel = new MainPage(bookList);
		                break;
	            	}
	            case "Citation":
	            	if (user != null) {
	            		panel = new CitationPage(book);
		                break;
	            	}
	            case "Profile":
	            	if (user != null) {
	                panel = new ProfilePage(user);
	                break;
	            	}
	            case "Critic":
	            	if(user != null) {
	                panel = new CriticPage(user, book);
	                break;
	            	}
	            case "fProfile":
	            	if (user != null) {
	                panel = new ProfilePage(userF);
	                break;
	            	}
	            case "Settings":
	            	if (user != null) {
	            		panel = new SettingsPage(user);
	            		break;
	            	}
	                break;
	            case "Welcome":
	                    panel = new WelcomePage();
	                    string = "Profile";
	                break;
	            case "SearchMain":
	            	if (user != null) {
	            	CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
	    	        cardLayout.show(panelContainer, string);
	    	        add(searchPanel, BorderLayout.EAST);
	    	        break;
	            	}
	            case "OtherProfile":
	            	panel = new OtherProfile(userF); 
	            	break; 
	            default:
	                throw new IllegalArgumentException("Invalid panel identifier: " + string);
	        }
	        if (panel != null) {
	        	panelContainer.add(panel, string);
	        } 
        	CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
	        cardLayout.show(panelContainer, string);
	        
	        if (user != null) {
	        	add(searchPanel, BorderLayout.EAST);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}


	private JButton sideButton(String s) {
		
		URL homeUrl = getClass().getResource(s);
		ImageIcon homeIcon = new ImageIcon(homeUrl);
		Image img = homeIcon.getImage();
		img = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(img);
		JButton home = new JButton();
		home.setIcon(homeIcon);
		home.setPreferredSize(new Dimension(35, 35));
		home.setBorder(null);
		home.setContentAreaFilled(false);
		return home ; 
		
	}
	
 	public void closeOP() {
 		setSize(1280,800);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	public Component searchBar() {
		//SEARCH_FIELD
		setSearchField();
        //SEARCH_ICON
		searchIcon(); 

        //SEARCH_PANEL
        JPanel searchPanel = new JPanel(new MigLayout("wrap 2", "[]10[]", "[][][][]50[]"));
        searchPanel.add(searchIconLabel,"cell 0 0");
        searchPanel.add(searchField, "cell 1 0");
        searchPanel.setBackground(Color.WHITE);
        UsersList pane = new UsersList(); 
        searchPanel.add(pane.getPane() , "cell 1 4"); // adds FriendsList panel 
       
        //SEARCH_BUTTON
        JButton searchButton = new JButton("Search");
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchPanel.add(searchButton, "cell 2 0");
        searchButton.setEnabled(true);
        searchButton.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				searchforBook();
    		}; 
        });
        searchField.addKeyListener(new KeyAdapter()
		{

			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					searchforBook();
				}
			}
		});
      
		return searchPanel;
	}
	
	private void searchIcon() {
        URL searchURL = getClass().getResource("/searchIcon.png");
        ImageIcon searchIcon = new ImageIcon(searchURL);
        Image img = searchIcon.getImage();
        img = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(img);
        searchIconLabel = new JLabel(searchIcon);
	}
	
	private void setSearchField() {
        searchField = new JTextField(15);
        KeysUtil.CCP(searchField);
        searchField.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(0, 0, 0, 0) // Add left margin for the text
        ));
        searchField.setBackground(Color.WHITE);
	}
	

	public void setBook(Book book) {
		this.book=book;
	}
	
	public Book getBook() {
		return(book);
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
		searchPanel = searchBar();
		container.add(searchPanel, BorderLayout.EAST);
	}
	
	private void searchforBook()
	{
		String query =  searchField.getText();
		try {
			DBMain db = new DBMain();
			ArrayList<Book> bookList = db.searchLibrary(query);
			if (bookList.size() == 0) {
				bookList = db.getAPILibrary(query);
			}
			panelContainer.add(new MainPage(bookList), "SearchMain");
			if (user != null) {
				showPanel("SearchMain");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
