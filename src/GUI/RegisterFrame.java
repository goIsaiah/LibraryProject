package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class RegisterFrame extends Template {

	private JPanel contentPane;
	private Container container = getContentPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {

		super();

    	//CONTENT_FRAME
        JPanel content = new JPanel(new MigLayout("", "[]30[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        content.setBackground(Color.white);
        container.add(content, BorderLayout.CENTER);

        //BOOKMATE_LABEL
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");

        //SEARCH_BAR
        content.add(searchBar(), "cell 1 0");

        closeOP();

		/*
		setTitle("BookMate");

        //ICON
        URL iconUrl = getClass().getResource("/Icon.png");
        ImageIcon icon = new ImageIcon(iconUrl);
        setIconImage(icon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//SIDEBAR
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(75, 0));
        sidebar.setBackground(Color.decode("#0B6BCC"));
        container.add(sidebar, BorderLayout.WEST);

        JPanel content = new JPanel(new MigLayout("", "[]30[]", "[]30[]"));
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        JLabel label = new JLabel("BookMate");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        content.add(label, "cell 0 0");
        container.add(content, BorderLayout.CENTER);

		setContentPane(contentPane);
		closeOP();
		*/
	}

}
