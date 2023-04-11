package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Databases.DBFollowers;
import Databases.DBUser;
import Databases.DBUserInfo;
import DomainObjects.User;
import net.miginfocom.swing.MigLayout;

class FriendsList extends  JScrollPane {
	
	private JPanel panel; 
    private Template parentTemplate;
    
	private User user; 
	public FriendsList(User user) {
		this.user = user; 
		panel = makeContentPanel(); 
		try {
			addContentToPanel();
			init(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private void init() {
		this.setViewportView(panel);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension(900, 300));
	}
	
	private JPanel makeContentPanel() {
		panel = new JPanel(); 
		panel.setPreferredSize(new Dimension(900, 300));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new MigLayout("wrap", "[]", "[] [] [] "));
		return panel; 
	}
	
	private void addContentToPanel() throws SQLException {
		DBFollowers db_f = new DBFollowers(); 
		DBUser db = new DBUser(); 
	   DBUserInfo info = new DBUserInfo();
	   
		ArrayList<Integer> ids = db_f.getAllFriend_id(db.getUserId(this.user));

		Hashtable<Integer , String> set = getInfo(ids);

		
		
		int size = ids.size(); 
		for(int i =0 ; i<size ; i++ ) {
			int id = ids.get(i);
			String value = set.get(id); 
			MiniProfile profile = new MiniProfile(value); 
			profile.setPhoto(info.getPhotoUrl(id));
            panel.add(profile);
			
		}
	}
	
	public Hashtable<Integer, String> getInfo(ArrayList<Integer> keys) throws SQLException{
		DBUser db = new DBUser(); 
		Hashtable<Integer , String> allset = db.getUserInfo();
		Hashtable<Integer , String> set = new Hashtable<>(); 
		int size = keys.size(); 
		for(int i = 0 ; i<size ; i++) {
			int id = keys.get(i);
			if(allset.containsKey(id)) {
				set.put(id, allset.get(id));
			}
		}
		
		
		return set; 
	}
	
	
}