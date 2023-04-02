package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import Databases.DBMain;
import Databases.DBForum;
import DomainObjects.Book;
import DomainObjects.Comment;
import DomainObjects.User;
import Logic.KeysUtil;
import net.miginfocom.swing.MigLayout;

public class CommentPage extends JPanel{
	DBForum forum = new DBForum(); 
	User currUser;
	Book currBook;
	
	public CommentPage(Book book, User user) throws SQLException {
		currUser = user;
		currBook = book;
    	framePanel(book.getTitle());
    	addComments();
    	showComments();
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
	
	private void addComments() {
		//COMMENT_FORM_PANEL
		JPanel commentFormPanel = new JPanel();
		commentFormPanel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
		commentFormPanel.setBackground(Color.white);
		
		//COMMENT_TEXT_FIELD
		JTextField commentTextField = new JTextField(20);
		KeysUtil.CCP(commentTextField);
		commentFormPanel.add(new JLabel("Add Comment:"));
		commentFormPanel.add(commentTextField, "wrap");
		
		//COMMENT_BUTTON
		JButton commentButton = new JButton("Post Comment");
		commentFormPanel.add(commentButton, "span");
		commentButton.addActionListener(e -> {
			try {
				createComment(commentTextField.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}); 

		add(commentFormPanel, "cell 0 1");
	}
	
	private void createComment(String text) throws SQLException {
		Comment comment = new Comment(currUser, text, currBook.getTitle(), currBook.getId());
		forum.addComment(comment);
		showComments();
	}

	private void showComments() throws SQLException {
		clearPanel();
		framePanel(currBook.getTitle());
		addComments();
		
		// COMMENTS_PANEL
		JPanel commentsPanel = new JPanel();
		commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
		commentsPanel.setBackground(Color.white);

		// COMMENTS_LABEL
		JLabel commentsLabel = new JLabel("Comments:");
		commentsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		commentsLabel.setBackground(Color.white);
		commentsPanel.add(commentsLabel);
		
		ArrayList<Comment> commentsList = forum.getCommentList(currBook.getId());
		for (int i = 0; i < commentsList.size(); i++) {
			Comment comment = commentsList.get(i);
			String commentString = comment.getMessage();
			
			// Add comment in commented-out style
			JPanel commentPanel = new JPanel();
			commentPanel.setLayout(new MigLayout("", "[]20[]", "[]5[grow]"));
			commentPanel.setBackground(Color.white);
			
			JLabel commentUserLabel = new JLabel(comment.getUserName() + ":");
			commentUserLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
			commentPanel.add(commentUserLabel, "cell 0 0");

			// Create a label to display the comment text
			JLabel commentTextLabel = new JLabel();
			commentTextLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			commentTextLabel.setPreferredSize(new Dimension(300, 50)); // set maximum width and height for the label

			// Wrap the comment text with HTML line breaks to create multiple lines
			String wrappedCommentText = "<html>" + commentString.replaceAll("\n", "<br/>") + "</html>";
			commentTextLabel.setText(wrappedCommentText);
			commentPanel.add(commentTextLabel, "cell 0 1");
			
			commentsPanel.add(commentPanel);
		}

	    //SCROLL_PANE
	    JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
	    commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    commentsScrollPane.setPreferredSize(new Dimension(400, 300));

	    //COMMENTS_SECTION
	    JPanel commentSection = new JPanel(new BorderLayout());
	    commentSection.setBackground(Color.white);
	    commentSection.add(commentsLabel, BorderLayout.NORTH);
	    commentSection.add(commentsScrollPane, BorderLayout.CENTER);

	    add(commentSection, "cell 0 2");
	}

	private void clearPanel() {
		removeAll();
		revalidate();
		repaint();
	}
}
