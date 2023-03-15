package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

class CommentTextField extends JPanel{
	
	private JScrollPane scroll; 
	public JTextArea textArea;
	
	public CommentTextField() {
		setBackground(Color.white);
		this.setLayout(new MigLayout("wrap", "[]",""));
		// text to input the comments 
		initText(); 
		initScroll();
		add(scroll);
		
	}
	
	private void initText() {
		textArea = new JTextArea(5, 20);
		textArea.setPreferredSize(new Dimension(500, 500));
		textArea.setLineWrap(true);
	}
	
	private void initScroll() {
		scroll = new JScrollPane(textArea);
//		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public JTextArea getTextArea() {
		return this.textArea; 
	}
	
	public void setTextArea(String str) {
		textArea.setText(str);
	}
}