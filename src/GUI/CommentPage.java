package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Databases.DBForum;
import DomainObjects.Book;
import DomainObjects.Comment;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

public class CommentPage extends JPanel {
    private DBForum forum = new DBForum();
    private User currUser;
    private Book currBook;

    public CommentPage(Book book, User user) throws SQLException {
        currUser = user;
        currBook = book;
        framePanel(book.getTitle());
        addComments();
        showComments();
    }

    private void framePanel(String labelString) {
        // Set up frame panel
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);

        // Add Bookmate label
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(label, "cell 0 0");
    }

    private void addComments() {
        // Set up comment form panel
        JPanel commentFormPanel = new JPanel();
        commentFormPanel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
        commentFormPanel.setBackground(Color.white);

        // Add comment text field
        JTextField commentTextField = new JTextField(20);
        commentFormPanel.add(new JLabel("Add Comment:"));
        commentFormPanel.add(commentTextField, "wrap");

        // Add "Post Comment" button and its action listener
        JButton commentButton = new JButton("Post Comment");
        commentFormPanel.add(commentButton, "span");
        commentButton.addActionListener(e -> {
            try {
                createComment(commentTextField.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        add(commentFormPanel, "cell 0 1");
    }

    private void createComment(String text) throws SQLException {
        // Create a new comment object and add it to the database
        Comment comment = new Comment(currUser, text, currBook.getTitle(), currBook.getId());
        forum.addComment(comment);

        // Show the updated list of comments
        showComments();
    }

    private void showComments() throws SQLException {
        // Clear the panel and set up the frame
        clearPanel();
        framePanel(currBook.getTitle());
        addComments();

        // Set up the comments panel
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.white);

        // Add the "Comments:" label
        JLabel commentsLabel = new JLabel("Comments:");
        commentsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        commentsLabel.setBackground(Color.white);
        commentsPanel.add(commentsLabel);

        // Get the list of comments for this book and display them
        ArrayList<Comment> commentsList = forum.getCommentList(currBook.getId());
        for (int i = 0; i < commentsList.size(); i++) {
            Comment comment = commentsList.get(i);
            String commentString = comment.getMessage();

            // Set up the comment panel
            JPanel commentPanel = new JPanel();
            commentPanel.setLayout(new MigLayout("", "[]20[]", "[]5[grow]"));
            commentPanel.setBackground(Color.white);
            // Add the username label
            JLabel commentUserLabel = new JLabel(comment.getUserName() + ":");
            commentUserLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            commentPanel.add(commentUserLabel, "cell 0 0");

            // Add the comment text label
            JLabel commentTextLabel = new JLabel();
            commentTextLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            commentTextLabel.setPreferredSize(new Dimension(300, 50));
            String wrappedCommentText = "<html>" + commentString.replaceAll("\n", "<br/>") + "</html>";
            commentTextLabel.setText(wrappedCommentText);
            commentPanel.add(commentTextLabel, "cell 0 1");

            commentsPanel.add(commentPanel);
        }

        // Set up the comments scroll pane
        JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        commentsScrollPane.setPreferredSize(new Dimension(400, 300));

        // Add the comments section to the frame
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

