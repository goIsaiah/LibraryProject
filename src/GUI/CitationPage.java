package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.*;

import org.json.JSONObject;

import DomainObjects.*;
import Logic.Citation;
import Logic.GoogleJSON;
import net.miginfocom.swing.MigLayout;

public class CitationPage extends JPanel{
	private Template parentTemplate;
	private Book book;
	
	public CitationPage(Book book) {
		this.book=book;
		framePanel("Citation");
		
		JLabel author = new JLabel("By: " + book.getAuthor());
        author.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,20));
        add(author, "cell 0 1");
        JLabel isbn = new JLabel("ISBN-13: "  + book.getIsbn());
        isbn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(isbn, "cell 0 2");
        JLabel yearPub = new JLabel("Published: "  + book.getYearPublished());
        yearPub.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        add(yearPub, "cell 0 3");
        
        String citation = getCitationString();
        
        JTextArea citationTextArea = new JTextArea(citation);
        citationTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        citationTextArea.setLineWrap(true);
        citationTextArea.setWrapStyleWord(true);
        JScrollPane citationScrollPane = new JScrollPane(citationTextArea);
        citationScrollPane.setPreferredSize(new Dimension(500, 100));
        add(citationScrollPane, "cell 0 4");

        // Copy to Clipboard Button
        JButton copyButton = new JButton("Copy to Clipboard");
        copyButton.addActionListener(e -> {
            StringSelection selection = new StringSelection(citation);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        });
        add(copyButton, "cell 0 5, alignx center");
	}

	private String getCitationString() {
		String citation;
		
		GoogleJSON gAPI = new GoogleJSON();
		Citation cObj = new Citation();
		JSONObject jObj = gAPI.getJSON(book.getIsbn());
		jObj = gAPI.getSearchIndex(jObj, 0);
		citation = cObj.getAPA(jObj);
		
		return citation;
	}

	private void framePanel(String labelString) {
		//CONTENT_FRAME
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        
        //BOOKMATE_LABEL
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        add(label, "cell 0 0");
	}

}
