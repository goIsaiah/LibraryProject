//package GUI;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.EventQueue;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Rectangle;
//import java.awt.ScrollPane;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.Random;
//
//import javax.swing.*;
//
//import Databases.DBForum;
//import DomainObjects.Comment;
//import DomainObjects.User;
//
//
//public class ForumPanel extends JPanel{
//	private CommentsPanel panel ; 
//	private CommentTextField text; 
//	public ForumPanel( User user, String book_title, int book_id) throws SQLException {
//
//		
//		panel = new CommentsPanel(); 
//		text = new CommentTextField(); 
//		
//		JButton submit = new JButton(); 
//		submit.setText("Submit");
//		submit.addActionListener(new ActionListener() {
//			
//			// updates database and GUI when submit button is clicked 
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Comment com = new Comment(user, text.getTextArea().getText(), book_title, book_id ); 
//				DBForum forum = new DBForum(); 
//					try {
//						forum.addComment(com);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					text.setTextArea(""); 							
//					SwingUtilities.invokeLater(() -> {
//						try {
//							panel.updateCommentsPanel();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					});						
//			}
//			
//		});
//		setBackground(Color.white);
//		panel.setBackground(Color.white);
//		text.setBackground(Color.white);
//		add(panel);
//		add(text); 
//		add(submit);
//	}
//}
//
// 
