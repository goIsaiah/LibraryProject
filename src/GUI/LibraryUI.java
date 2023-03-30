package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class LibraryUI {
	public static String sqlpassword;

	public LibraryUI() {
		password(); 
	}

	public static void main(String[] args) {
		new LibraryUI();
	}

	private void init() {
		try {

			new Template();
		} catch (Exception e) {
			JFrame incorrectpwdframe = new JFrame();
			JLabel incorrectpwd = new JLabel("Incorrect Password", SwingConstants.CENTER);
			JButton ok = new JButton();
			ok.setText("OK");
			incorrectpwd.setFont(incorrectpwd.getFont().deriveFont(Font.BOLD, 14f));
			incorrectpwdframe.setPreferredSize(new Dimension(200 , 150));
			incorrectpwdframe.setSize(new Dimension(200 , 150));
			incorrectpwdframe.setLayout(new MigLayout("", "[]", "[]2[]2[]" ));
			incorrectpwdframe.add(incorrectpwd);
			incorrectpwdframe.add(ok, "cell 0 2");
			incorrectpwdframe.setLocationRelativeTo(null);
			incorrectpwdframe.setVisible(true);
			incorrectpwdframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					incorrectpwdframe.dispose();
				}

			});
			ok.addKeyListener(new KeyAdapter()
			{

				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						incorrectpwdframe.dispose();
					}
				}
			});


		} 
	}

	public void password() {
		JFrame frame = new JFrame(); 
		JPasswordField text_field = new JPasswordField(); 
		text_field.setColumns(35);
		JLabel label = new JLabel("Enter Mysql Authentication Password: "); 
		JButton submit = new JButton();
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sqlpassword = text_field.getText(); 
				text_field.setText("");
				frame.dispose();
				init(); 
			}

		});

		text_field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					sqlpassword = text_field.getText(); 
					text_field.setText("");
					frame.dispose();
					init(); 
				}
			}
		});

		//SQL Popup
		submit.setText("Submit");
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
