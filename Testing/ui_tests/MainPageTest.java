package ui_tests;
import GUI.MainPage;
import DomainObjects.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private ArrayList<Book> bookList;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        bookList = new ArrayList<>();
        bookList.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 0));
        bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 0));
        bookList.add(new Book("1984", "George Orwell", 1949, 0));
    }

    @Test
    public void testBookListDisplay() {
        mainPage = new MainPage(bookList);
        JPanel panel = mainPage;
        int bookLabels = 0;
        int bookButtons = 0;

        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JLabel) {
                JLabel label = (JLabel) panel.getComponent(i);
                if (label.getText().contains("The Catcher in the Rye") ||
                    label.getText().contains("To Kill a Mockingbird") ||
                    label.getText().contains("1984")) {
                    bookLabels++;
                }
            } else if (panel.getComponent(i) instanceof JButton) {
                JButton button = (JButton) panel.getComponent(i);
                if (button.getText().contains("Open")) {
                    bookButtons++;
                }
            }
        }

        assertTrue(bookLabels == 3, "All book titles should be displayed");
        assertTrue(bookButtons == 3, "All book buttons should be displayed");
    }
}
