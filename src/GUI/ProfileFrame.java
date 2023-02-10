package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class ProfileFrame extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileFrame frame = new ProfileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfileFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(31, 107, 203));
		panel.setBounds(0, -11, 61, 607);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(113, 68, 69));
		panel_1.setBounds(154, 68, 125, 125);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(701, 20, 311, 29);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 311, 29);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel search_btn_panel = new JPanel();
		search_btn_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		search_btn_panel.setBounds(629, 21, 65, 29);
		contentPane.add(search_btn_panel);
		search_btn_panel.setLayout(null);
		
		JButton search_btn = new JButton("Search");
		search_btn.setBounds(0, 0, 65, 29);
		search_btn_panel.add(search_btn);
		search_btn.setIcon(null);
		search_btn.setBackground(new Color(237, 237, 237));
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel refresh_btn_panel = new JPanel();
		refresh_btn_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		refresh_btn_panel.setBounds(536, 21, 81, 29);
		contentPane.add(refresh_btn_panel);
		refresh_btn_panel.setLayout(null);
		
		JButton ReFresh_btn = new JButton("Refresh");
		ReFresh_btn.setBounds(0, 0, 81, 29);
		refresh_btn_panel.add(ReFresh_btn);
		ReFresh_btn.setBackground(new Color(237, 237, 237));
		
		JLabel profile_name = new JLabel("Profile Name");
		profile_name.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		profile_name.setIgnoreRepaint(true);
		profile_name.setBounds(291, 68, 251, 23);
		contentPane.add(profile_name);
		
		JLabel email_label = new JLabel("Email@gmail.com");
		email_label.setIgnoreRepaint(true);
		email_label.setBounds(292, 100, 250, 16);
		contentPane.add(email_label);
		
		JLabel clubName_label = new JLabel("Club Name");
		clubName_label.setIgnoreRepaint(true);
		clubName_label.setBounds(292, 128, 250, 16);
		contentPane.add(clubName_label);
		
		JLabel numRead_head = new JLabel("#Read");
		numRead_head.setIgnoreRepaint(true);
		numRead_head.setBounds(420, 156, 250, 16);
		contentPane.add(numRead_head);
		
		JLabel numFollowers_head = new JLabel("#Followers");
		numFollowers_head.setIgnoreRepaint(true);
		numFollowers_head.setBounds(682, 156, 250, 16);
		contentPane.add(numFollowers_head);
		
		JLabel lblNewLabel = new JLabel("10");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(420, 176, 35, 16);
		contentPane.add(lblNewLabel);
		
		JLabel numFollowers = new JLabel("160");
		numFollowers.setHorizontalAlignment(SwingConstants.CENTER);
		numFollowers.setBounds(682, 175, 61, 16);
		contentPane.add(numFollowers);
	}
}
