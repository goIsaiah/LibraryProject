package Logic;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class KeysUtil  {

	private KeysUtil() {
		
	}
	
	public static  <T extends JTextComponent> void  CCP( T field) {
		COPY(field); 
		CUT(field);
		PASTE(field);
	}
	
	
	private static<T extends JTextComponent> void COPY(T field) {
		// TODDO copy from the textfield
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				field.copy();
		}};
         String keyStrokeAndKey = "control c";
         KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
         field.getInputMap().put(keyStroke, keyStrokeAndKey);
         field.getActionMap().put(keyStrokeAndKey, action);
//		return null; 
	}
	private static <T extends JTextComponent> void  CUT(T field) {
		// TODDO copy from the textfield
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				field.cut();	
		}};
         String keyStrokeAndKey = "control x";
         KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
         field.getInputMap().put(keyStroke, keyStrokeAndKey);
         field.getActionMap().put(keyStrokeAndKey, action);
	}
	private static <T extends JTextComponent> void  PASTE(T field) {
		// TODDO copy from the textfield
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				field.paste();
		}};
         String keyStrokeAndKey = "control p";
         KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
         field.getInputMap().put(keyStroke, keyStrokeAndKey);
         field.getActionMap().put(keyStrokeAndKey, action);
	}
	
	

	
	

}
