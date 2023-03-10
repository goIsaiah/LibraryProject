package GUI;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.*;

import DomainObjects.User;
import Logic.Comment;
import Logic.Forum;


class ForumPanel extends JPanel{
	private CommentsPanel panel ; 
	private CommentTextField text; 
	public ForumPanel( User user, String book_title, int book_id) {

		
		panel = new CommentsPanel(); 
		text = new CommentTextField(); 
		
		JButton submit = new JButton(); 
		submit.setText("Submit");
		submit.addActionListener(new ActionListener() {
			
			// updates database and GUI when submit button is clicked 
			@Override
			public void actionPerformed(ActionEvent e) {
				Comment com = new Comment(user, text.getTextArea().getText(), book_title, book_id ); 
				Forum forum = new Forum(); 
					try {
						forum.addComment(com);
						
						text.setTextArea(""); 							
						SwingUtilities.invokeLater(() -> {
							panel.updateCommentsPanel();
						});
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}						
			}
			
		});
		
		add(panel);
		add(text); 
		add(submit);
	}
}

 

