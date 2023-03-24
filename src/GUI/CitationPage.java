package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.json.JSONObject;
import DomainObjects.Book;
import Logic.Citation;
import Logic.GoogleJSON;
import net.miginfocom.swing.MigLayout;

public class CitationPage extends JPanel {
    private Book book;

    public CitationPage(Book book) {
        this.book = book;

        // Set up frame panel
        framePanel("Citation");

        // Add author, ISBN, and publication year labels
        JLabel author = new JLabel("By: " + book.getAuthor());
        author.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        add(author, "cell 0 1");

        JLabel isbn = new JLabel("ISBN-13: " + book.getIsbn());
        isbn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(isbn, "cell 0 2");

        JLabel yearPub = new JLabel("Published: " + book.getYearPublished());
        yearPub.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(yearPub, "cell 0 3");

        // Generate citation string and add it to a text area
        String citation = getCitationString();

        JTextArea citationTextArea = new JTextArea(citation);
        citationTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        citationTextArea.setLineWrap(true);
        citationTextArea.setWrapStyleWord(true);
        JScrollPane citationScrollPane = new JScrollPane(citationTextArea);
        citationScrollPane.setPreferredSize(new Dimension(500, 100));
        add(citationScrollPane, "cell 0 4");

        // Add "Copy to Clipboard" button and its action listener
        clipBoard(citation);
    }

    private void framePanel(String labelString) {
        setLayout(new MigLayout("", "[]30[]", "[]30[]"));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        setBackground(Color.white);
        JLabel label = new JLabel(labelString);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(label, "cell 0 0");
    }

    private String getCitationString() {
        GoogleJSON gAPI = new GoogleJSON();
        Citation cObj = new Citation();
        JSONObject jObj = gAPI.getJSON(book.getIsbn());
        jObj = gAPI.getSearchIndex(jObj, 0);
        String citation = cObj.getAPA(jObj);
        return citation;
    }

    private void clipBoard(String citation) {
        JButton copyButton = new JButton("Copy to Clipboard");
        copyButton.addActionListener(e -> {
            StringSelection selection = new StringSelection(citation);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        });
        add(copyButton, "cell 0 5, alignx center");
    }
}
