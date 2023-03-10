package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

class CommentBlob extends JTextArea{
	
	public CommentBlob(String comment) {
		super(3, 21);
		this.setText(comment);
		this.setEditable(true);
		this.setLineWrap(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 0));
		
		this.setFont( new Font("Arial", Font.PLAIN, 11));
	}	
}