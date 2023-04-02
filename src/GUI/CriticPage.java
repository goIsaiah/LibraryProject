package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.*;
import Databases.DBCriticZone;
import DomainObjects.*;
import net.miginfocom.swing.*;


public class CriticPage extends JPanel
{
	DBCriticZone criticZone; //intializing DB
	User currentUser;
	Book currentBook;

	public CriticPage(User currentUser, Book currentBook) throws SQLException
	{
		this.currentUser = currentUser;
		this.currentBook = currentBook;
		this.criticZone = new DBCriticZone();

		frame(currentBook.getTitle());
		addCritics();
		showCritics();


	}

	private void frame(String label)
	{
		//content frame
		setLayout(new MigLayout("", "[]30[]", "[]30[]"));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
		setBackground(Color.white);

		//book title label
		JLabel bookName = new JLabel(label);
		bookName.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		add(bookName, "cell 0 0");


	}

	private void addCritics() {
    // rating form panel
    JPanel ratingPanel = new JPanel();
    ratingPanel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
    ratingPanel.setBackground(Color.white);

    // star rating system
    JPanel starRatingPanel = new JPanel();
    starRatingPanel.setBackground(Color.white);

    ButtonGroup starGroup = new ButtonGroup();
    JRadioButton[] starButtons = new JRadioButton[5];

    for (int i = 0; i < 5; i++) {
        starButtons[i] = new JRadioButton();
        starButtons[i].setActionCommand(String.valueOf(i + 1));
        starGroup.add(starButtons[i]);
        starRatingPanel.add(starButtons[i]);
    }

    ratingPanel.add(new JLabel("Add rating (1-5):"));
    ratingPanel.add(starRatingPanel, "wrap");

    // review text field
    JTextField reviewTextField = new JTextField(25);
    ratingPanel.add(new JLabel("Add review:"));
    ratingPanel.add(reviewTextField, "wrap");

    // add post button
    JButton rrButton = new JButton("Post Rating and Review");
    ratingPanel.add(rrButton, "span");
    rrButton.addActionListener(e -> {
        try {
            String rating = starGroup.getSelection().getActionCommand();
            createCritic(rating, reviewTextField.getText());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    });

    add(ratingPanel, "cell 0 2");
}

	private void createCritic(String rating, String review) throws SQLException
	{
		//creating rating
		Integer r = Integer.valueOf(rating);
		int ra = (int)r;
		Rating rate = new Rating(this.currentUser, this.currentBook);
		rate.setRating(ra);

		//creating review
		Review rev = new Review(review, this.currentBook, this.currentUser);
		//creating critic
		Critic c = new Critic(currentBook, currentUser, rev, rate, currentBook.getId());
		criticZone.addCritic(c);
		showCritics();

	}

	private void showCritics() throws SQLException
	{
		clearPanel();
		frame(currentBook.getTitle());
		addCritics();
		
		//critics panel
		JPanel criticPanel = new JPanel();
		criticPanel.setLayout(new BoxLayout(criticPanel, BoxLayout.Y_AXIS));
		criticPanel.setBackground(Color.white);
		
		//critics label
		JLabel criticLabel = new JLabel("Critics:");
		criticLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		criticLabel.setBackground(Color.white);
		criticPanel.add(criticLabel);
		
		//get list of critics
		ArrayList<Critic> criticList = criticZone.getCriticList(currentBook.getId());
		
		for (int i = 0; i < criticList.size(); i++)
		{
			Critic c = criticList.get(i);
			String criticSummary = c.toString();
			
			//output the summary of the critic
			JPanel panel = new JPanel();
			panel.setLayout(new MigLayout("", "[]20[]", "[]5[grow]"));
			panel.setBackground(Color.white);
			
			//create a label to display the summary
			JLabel summary = new JLabel();
			summary.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			summary.setPreferredSize(new Dimension(300, 50));
			
			//wrap the text 
			String wrappedSummary = String.format("<html> %s </html>", criticSummary.replaceAll("\n", "<br/>"));
			summary.setText(wrappedSummary);
			panel.add(summary, "cell 0 1");
			
			criticPanel.add(panel);
		}
		
		//scroll panel
		
	    JScrollPane scrollPane = new JScrollPane(criticPanel);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setPreferredSize(new Dimension(400, 300));
	    
	    //critic section
	    JPanel criticSection = new JPanel(new BorderLayout());
	    criticSection.setBackground(Color.white);
	    criticSection.add(criticLabel, BorderLayout.NORTH);
	    criticSection.add(scrollPane, BorderLayout.CENTER);

	    add(criticSection, "cell 0 1");
	    

		
	}

	private void clearPanel() 
	{
		removeAll();
		revalidate();
		repaint();
	}
	
	private boolean validRating(String rating)
	{
		boolean result;
		if (rating.equals("1") || rating.equals("2") || rating.equals("3") || rating.equals("4") || rating.equals("5"))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
}
