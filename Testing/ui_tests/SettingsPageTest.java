package ui_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DomainObjects.User;
import GUI.LibraryUI;
import GUI.SettingsPage;

public class SettingsPageTest {

    private SettingsPage settingsPage;
    private User testUser;

    @BeforeEach
    public void setUp() {
    	new LibraryUI();
        testUser = new User("testUsername", "testPassword","testEmail@gmail.com");
        testUser.setFirstName("TestFirstName");
        testUser.setLastName("TestFirstName");
        testUser.setBio("This is a test bio for the user.");
        testUser.setURL("https://www.example.com/testuser");

        try {
            settingsPage = new SettingsPage(testUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSettingsPageUpdate() {
        JTextField firstNameField = (JTextField) findComponentByName(settingsPage, "First Name:");
        JTextField lastNameField = (JTextField) findComponentByName(settingsPage, "Last Name:");
        JScrollPane bioScrollPane = (JScrollPane) findComponentByName(settingsPage, "Bio:");
        JTextComponent bioField = (JTextComponent) bioScrollPane.getViewport().getView();
        JTextField userUrlField = (JTextField) findComponentByName(settingsPage, "Profile URL:");
        JButton submitButton = (JButton) findComponentByName(settingsPage, "   Submit Changes    ");

        firstNameField.setText("UpdatedFirstName");
        lastNameField.setText("UpdatedLastName");
        bioField.setText("Updated test bio for the user.");
        userUrlField.setText("https://www.example.com/updatedtestuser");

        submitButton.doClick();

        assertEquals("UpdatedFirstName", testUser.getFirstName(), "First name should be updated correctly");
        assertEquals("UpdatedLastName", testUser.getLastName(), "Last name should be updated correctly");
        assertEquals("Updated test bio for the user.", testUser.getBio(), "Bio should be updated correctly");
        assertEquals("https://www.example.com/updatedtestuser", testUser.getURL(), "URL should be updated correctly");
    }

    private Object findComponentByName(JPanel panel, String labelText) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JLabel && ((JLabel) panel.getComponent(i)).getText().equals(labelText)) {
                return panel.getComponent(i + 1);
            }
            if (panel.getComponent(i) instanceof JButton && ((JButton) panel.getComponent(i)).getText().equals(labelText)) {
                return panel.getComponent(i);
            }
        }
        throw new AssertionError("Component with label text '" + labelText + "' not found in SettingsPage");
    }
}
