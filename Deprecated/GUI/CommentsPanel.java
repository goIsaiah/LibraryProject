//package GUI;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//import Databases.DBForum;
//import net.miginfocom.swing.MigLayout;
//
//class CommentsPanel extends JPanel{
//	
//	private DBForum forum;
//	private JScrollPane sp; 
//	private JPanel commentsPanel ; 
//	private ArrayList<String> list; 
//	
//	public CommentsPanel() throws SQLException {
//		forum = new DBForum();
//		setPreferredSize(new Dimension(1000, 800));
//		commentsPanel = new JPanel(); 
//		
//		initScroll();
//		
//		MigLayout mig = new MigLayout("wrap", "5[]", "4[]4[]4[]");
//		commentsPanel.setLayout(mig);
//		
//		this.updateCommentsPanel(); // populates the commentsPanel
//		setBackground(Color.white);
//		add(sp);
//	}
//	
//	public void initScroll() {
//		sp = new JScrollPane(commentsPanel); 
//		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		sp.setPreferredSize(getPreferredSize());
//	}
//	
//	public void updateCommentsPanel() throws SQLException {
//		commentsPanel.removeAll();
////		list = forum.getComments();
//		for(String s: list) {
//			commentsPanel.add(new CommentBlob(s));
//		}
//		commentsPanel.revalidate();
//		setBackground(Color.white);
//		
//	}
//	
//}