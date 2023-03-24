package GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class PSWD extends JFrame {

	private String password ; 

	public PSWD() {
		JTextField text_field = new JTextField(); 
		text_field.setColumns(35);
		JLabel label = new JLabel("Enter Mysql Authentication Password"); 
		JButton submit = new JButton();

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				password = text_field.getText(); 
				text_field.setText("");
				dispose();
				
			}

		});



		submit.setText("submit");

		setPreferredSize(new Dimension(200 , 200));
		setSize(new Dimension(300 , 200));
		setLayout(new MigLayout("", "[]", "[]10[]10[]" ));


		add(label, "cell 0 1");
		add(text_field, "cell 0 2");
		add(submit, "cell 0 3");

		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}


	
	public String getPassword() {
		return password; 
	}

//	public static void main(String[] args) {
//		new PSWD(); 
//	}



}
