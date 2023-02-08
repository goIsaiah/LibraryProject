package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Profile {

	private JFrame frame;
	private JTextField txtBookmate;
	private JTextField txtProfilename;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1080, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(11, 106, 203));
		panel.setBounds(0, 0, 61, 607);
		frame.getContentPane().add(panel);
		
		txtBookmate = new JTextField();
		txtBookmate.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookmate.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		txtBookmate.setText("BookMate");
		txtBookmate.setBounds(112, 19, 140, 33); 
		frame.getContentPane().add(txtBookmate);
		txtBookmate.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 68, 69));
		panel_1.setBounds(154, 68, 125, 125);
		frame.getContentPane().add(panel_1);
		
		txtProfilename = new JTextField();
		txtProfilename.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtProfilename.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfilename.setText("ProfileName");
		txtProfilename.setBounds(300, 68, 87, 15);
		frame.getContentPane().add(txtProfilename);
		txtProfilename.setColumns(10);
		
		textField = new JTextField();
		textField.setText("ProfileName");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(300, 88, 87, 15);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("ProfileName");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(299, 110, 87, 15);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("ProfileName");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(415, 142, 87, 15);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("ProfileName");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(543, 142, 87, 15);
		frame.getContentPane().add(textField_3);
	}
}
