package ui_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUI.Template;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateTest {

    private Template template;

    @BeforeEach
    public void setUp() throws SQLException {
        template = new Template();
    }

    @AfterEach
    public void tearDown() {
        template = null;
    }

    @Test
    public void testTemplateCreation() {
        assertNotNull(template, "Template object should not be null");
    }

    // Add more tests here for other methods and functionalities
}
