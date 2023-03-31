package GUI;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

import Databases.DBType_enum;
import Databases.DBUtil;
import net.miginfocom.swing.MigLayout;

public class LibraryUI {
	public static String sqlpassword;
	public static Connection conn ; 
	public LibraryUI() {
		try {
			conn = DBUtil.getConnection(DBType_enum.ONLINE); 
			init();
		}catch (SQLException e) {
			password(); 
		}
	}
	
	public static void main(String[] args) {
		new LibraryUI();
	}
	
	private void init() throws SQLException {
		new Template();
	
	}

	public void password() {
		JFrame frame = new JFrame(); 
		JTextField text_field = new JTextField(); 
		text_field.setColumns(35);
		JLabel label = new JLabel("Enter Mysql Authentication Password"); 
		JButton submit = new JButton();

		submit.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LibraryUI.sqlpassword = text_field.getText(); 
					text_field.setText("");
					frame.dispose();
					conn = DBUtil.getConnection(DBType_enum.OFFLINE);
					init();
				} catch (SQLException e1) {
				} 
			}
		});

		submit.setText("submit");

		frame.setPreferredSize(new Dimension(200 , 200));
		frame.setSize(new Dimension(300 , 200));
		frame.setLayout(new MigLayout("", "[]", "[]10[]10[]" ));


		frame.add(label, "cell 0 1");
		frame.add(text_field, "cell 0 2");
		frame.add(submit, "cell 0 3");

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
