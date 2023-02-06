package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Login {

	private JFrame frmBookmate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmBookmate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookmate = new JFrame();
		frmBookmate.setTitle("BookMate");
		frmBookmate.setResizable(false);
		frmBookmate.getContentPane().setEnabled(false);
		frmBookmate.getContentPane().setBackground(Color.WHITE);
		frmBookmate.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(31, 107, 203));
		panel.setBounds(0, 0, 62, 608);
		frmBookmate.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtrBookmate = new JTextArea();
		txtrBookmate.setEditable(false);
		txtrBookmate.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		txtrBookmate.setBounds(113, 20, 130, 33);
		txtrBookmate.setText("BookMate");
		frmBookmate.getContentPane().add(txtrBookmate);
		frmBookmate.setBounds(100, 100, 1080, 608);
		frmBookmate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
