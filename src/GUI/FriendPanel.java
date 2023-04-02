package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import Databases.DBUser;
import Databases.DBUserInfo;
import net.miginfocom.swing.MigLayout;

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
//					System.out.println("Refreshed");
					addContent();
					} catch (SQLException e1) {
//						e1.printStackTrace();
					}
					
				}
				
			};
			
			return action; 
			
		}
		
		
		private void addContent() throws SQLException {
			DBUser user = new DBUser(); 
			DBUserInfo info = new DBUserInfo();
			Hashtable<Integer, String> list = user.getUserInfo();
			ArrayList<Integer> set = new ArrayList<>(list.keySet());
			
						
			for(int i = 0 ; i<set.size(); i++) {
				MiniProfile label1 = new MiniProfile(list.get(set.get(i))); 
				label1.setPhoto(info.getPhotoUrl(set.get(i)));
				
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