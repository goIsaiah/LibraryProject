package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class FrameTest {
	public static void main(String[] a) {
		JFrame frame = new JFrame(); 
		frame.setBounds(0, 0, 800, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(new FriendList()); 
		frame.pack();
	}
	
}

class FriendProfile extends JPanel{
	private JLabel label ; 
	
	public FriendProfile() {
		label = new JLabel("Name"); 
		add(label); 
		setPreferredSize(new Dimension(50, 100));
	}
	
}


class FriendList extends JPanel{
	
	public FriendList() {
		this.setPreferredSize(new Dimension(300 , 500));
		this.setLayout(new MigLayout("", "[][]", "[][][]"));
	}
	
	
}




