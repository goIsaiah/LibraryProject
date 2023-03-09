package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import DomainObjects.User;
import Logic.Comment;
import Logic.Forum;
import net.miginfocom.swing.MigLayout;

/*
 * todo:
 * 	*update CommentsPanel when a new comment is added
 */

class ForumPanel extends JPanel{
	private CommentsPanel panel ; 
	private CommentTextField text; 
	
	
	public ForumPanel( User user, String book_title, int book_id) {

		
		panel = new CommentsPanel(); 
		text = new CommentTextField(); 
		
		JButton submit = new JButton(); 
		submit.setText("Submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Comment com = new Comment(user, text.getTextArea().getText(), book_title, book_id ); 
				Forum forum = new Forum(); 
				
				try {
					forum.addComment(com);
					
					text.setTextArea(""); 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				text.setTextArea("");
			}
			
		});
		
		add(panel);
		add(text); 
		add(submit);
		
	}
	
}



class CommentsPanel extends JPanel{
	
	private Forum forum;
	private JScrollPane sp; 
	private JPanel commentsPanel ; 
	
	public CommentsPanel() {
		forum = new Forum();
		setPreferredSize(new Dimension(300, 200));
		commentsPanel = new JPanel(); 
		
		initScroll();
		
		MigLayout mig = new MigLayout("wrap", "5[]", "4[]4[]4[]");
		commentsPanel.setLayout(mig);
		
		try {
			ArrayList<String> list = forum.getComments();
			for(String s: list) {
				commentsPanel.add(new CommentBlob(s));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		add(sp);
	}
	
	public void initScroll() {
		sp = new JScrollPane(commentsPanel); 
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(getPreferredSize());
	}
	
}


class CommentBlob extends JTextArea{
	
	public CommentBlob(String comment) {
		super(3, 21);
		this.setText(comment);
		this.setEditable(false);
		this.setLineWrap(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}	
}


class CommentTextField extends JPanel{
	
	private JScrollPane scroll; 
	public JTextArea textArea;
	
	public CommentTextField() {
		
		this.setLayout(new MigLayout("wrap", "[]",""));
		// text to input the comments 
		initText(); 
		initScroll();
		add(scroll);
		
	}
	
	private void initText() {
		textArea = new JTextArea(5, 20);
		textArea.setPreferredSize(new Dimension(300, 500));
		textArea.setLineWrap(true);
	}
	
	private void initScroll() {
		scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public JTextArea getTextArea() {
		return this.textArea; 
	}
	
	public void setTextArea(String str) {
		textArea.setText(str);
	}
}

 

