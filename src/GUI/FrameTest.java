package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Databases.DBType_enum;
import Databases.DBUser;
import Databases.DBUtil;
import net.miginfocom.swing.MigLayout;

public class FrameTest {

	
	public static void main(String[] a) {
		JFrame frame = new JFrame(); 
		frame.setBounds(0, 0, 300, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		FriendPanel panel = new FriendPanel();
		frame.add(panel.getPane() ); 
		frame.pack();
		
		try {
			ArrayList<String> list = getUserInfo();
			for(int i = 0 ; i< list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			System.out.println("password not correct"); 
		} 
		
	}
	
	public static ArrayList<String> getUserInfo() throws SQLException{
		Connection conn = DBUtil.getConnection(DBType_enum.ONLINE); 
		String query = "Select USERNAME, EMAIL from USERTABLE;"; 
		Statement statement= conn.createStatement(); 
		ResultSet set = statement.executeQuery(query); 
		ArrayList<String> list = new ArrayList<>(); 
		
		while(set.next()) {
			list.add((set.getString(1) + "\n" + set.getString(2) + "\n")); 
		}
		return list; 
	}
	
}

class FriendProfile extends JPanel{
	private JLabel label; 
	private JPanel photo; 
	
	public FriendProfile(String text) {
		init(); 
		label = new JLabel();
		label.setText("<html>"+ text.replaceAll("\n", "<br/>") +"</html>");
		setPhoto(); 
		add(photo, BorderLayout.WEST);
		add(label, BorderLayout.CENTER);
	}
	
	private void init() {
		setFont(new Font("Arial", Font.BOLD, 20));
		BorderLayout layout = new BorderLayout(); 
		layout.setHgap(5);
		setPreferredSize(new Dimension(300, 50));
		this.setLayout(layout);
	}
	
	private void setPhoto() {
		photo = new JPanel();
		photo.setPreferredSize(new Dimension(30, 30));

//		ImageIcon imageIcon = new ImageIcon("/cat_avatar.png");
//		JLabel imageLabel = new JLabel(imageIcon);
//		photo.add(imageLabel);
		
		
        URL searchURL = getClass().getResource("/cat_avatar.png");
        ImageIcon searchIcon = new ImageIcon(searchURL);
        Image img = searchIcon.getImage();
        img = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(img);
        JLabel searchIconLabel = new JLabel(searchIcon);
		
        photo.add(searchIconLabel);
		
		
		
		
//		photo.setBackground(Color.GREEN);

		
		
	}
	


}

class FriendPanel  {
	private JScrollPane pane; 
	Timer stopwatch; 
	private int previousPosition; 
	
	public FriendPanel() {
		pane = new JScrollPane(); 
		pane.setPreferredSize(new Dimension(300 , 500));
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setViewportView(new FriendList());
	}
	
	private void rememberLastPosition() {
		previousPosition = pane.getVerticalScrollBar().getValue();
		pane.getVerticalScrollBar().setValue(previousPosition);
	}
	
	public JScrollPane getPane() {
		return pane; 
	}
	
	
	class FriendList extends JPanel {
		private Timer stopwatch ;
		
		
		public FriendList() {
			this.setPreferredSize(new Dimension(300 , 500));
			this.setLayout(new MigLayout("wrap", "[]", "[][][]"));
			refreshTimer();

		}
		
		private void refreshTimer() {
			stopwatch = new Timer(5000, refresh());
			stopwatch.setInitialDelay(0);
			stopwatch.start();
		}
		
		private ActionListener refresh() {
			ActionListener action = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					previousPosition = pane.getVerticalScrollBar().getValue();
					removeAll();
					revalidate();
					repaint();
					pane.getVerticalScrollBar().setValue(previousPosition);
					System.out.println("Refreshed");
					addContent();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			};
			
			return action; 
			
		}
		
		
		private void addContent() throws SQLException {
			DBUser user = new DBUser(); 
			ArrayList<String> list = user.getUserInfo();
			
			for(int i = 0 ; i<list.size(); i++) {
				FriendProfile label1 = new FriendProfile(list.get(i)); 
			    label1.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    System.out.println("Yay you clicked me");
	                }

	            });
				add(label1); 
			}
			//TODO onlick user profile should appear
			//	   or ability to add as friend is added
			
			
		}
		
		
	}// End of FriendList

	
	

}


//class FriendList extends JPanel {
//	private Timer stopwatch ;
//	
//	
//	public FriendList() {
//		this.setPreferredSize(new Dimension(300 , 500));
//		this.setLayout(new MigLayout("wrap", "[]", "[][][]"));
//		refreshTimer();
//
//	}
//	
//	private void refreshTimer() {
//		stopwatch = new Timer(5000, refresh());
//		stopwatch.setInitialDelay(0);
//		stopwatch.start();
//	}
//	
//	private ActionListener refresh() {
//		ActionListener action = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//				removeAll();
//				revalidate();
//				repaint();
//					addContent();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				
//			}
//			
//		};
//		
//		return action; 
//		
//	}
//	
//	
//	private void addContent() throws SQLException {
//		DBUser user = new DBUser(); 
//		ArrayList<String> list = user.getUserInfo();
//		
//		for(int i = 0 ; i<list.size(); i++) {
//			FriendProfile label1 = new FriendProfile(list.get(i)); 
//		    label1.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println("Yay you clicked me");
//                }
//
//            });
//			add(label1); 
//		}
//		//TODO onlick user profile should appear
//		//	   or ability to add as friend is added
//		
//		
//	}
//	
//	
//}




