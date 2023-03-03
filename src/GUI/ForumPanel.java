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

class CommentTextPane extends JPanel{
	
	private JScrollPane scroll; 
	private User user ;
	public JTextArea textArea;
	
	public CommentTextPane(User user) {
		
		this.user = user; 
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


class CommentBox extends JTextArea{
	
	public CommentBox(String comment) {
		super(3, 21);
		this.setText(comment);
		this.setEditable(false);
		this.setLineWrap(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
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
		
		ArrayList<String> list = forum.getComments();
		
		for(String s: list) {
			commentsPanel.add(new CommentBox(s));
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


class ForumPanel extends JPanel{
	private User user; 
	private CommentsPanel panel ; 
	private CommentTextPane text; 
	
	
	public ForumPanel() {
		user = new User("Vince", "1234", "Vince.email.com" );; 
		panel = new CommentsPanel(); 
		text = new CommentTextPane(user); 
		
		JButton submit = new JButton(); 
		submit.setText("Submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand= new Random(); 
				
				Comment com = new Comment(user, rand.nextInt(10), text.getTextArea().getText()); 
				Forum forum = new Forum(); 
				forum.addComment(com);
				text.setTextArea("");
			}
			
		});
		
		add(panel);
		add(text); 
		add(submit);
		
	}
	
	private void init() {
		MigLayout mig = new MigLayout("wrap", "[]", "5[]5[]5"); 
		this.setLayout(mig);
		this.setPreferredSize(new Dimension(400, 800));
		
	}
	

}





class ForumTest{
	
//	private static CommentTextPane forum;
//	private static CommentsPanel forum;
	private static JFrame frame; 
	
	public static void main(String[] args) {
		
//		forum = new CommentTextPane(); 
//		forum = new CommentsPanel();
		
		ForumPanel forum = new ForumPanel(); 
		/*
		 * init frame
		 */
		frame = new JFrame(); 
		frame.setPreferredSize(new Dimension(350, 500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.add(forum);
		
	}
	
}

 

