package logic_domain_objects;

import static org.junit.Assert.*;
import org.junit.Test;

import Databases.DBUser;
import GUI.LibraryUI;

import java.sql.SQLException;
import java.util.Scanner;

public class DBUserTest {

    @Test
    public void testCheckUserExists() throws SQLException {
        // Test case for checkUserExists() method
    	Scanner sc = new Scanner(System.in);
		System.out.print("Enter SQL password: ");
    	LibraryUI.sqlpassword = sc.nextLine();
        DBUser dbUser = new DBUser();
        boolean result1 = dbUser.checkUserExists("john123", "john@gmail.com");
        boolean result2 = dbUser.checkUserExists("Polywertz", "mary@gmail.com");
        boolean result3 = dbUser.checkUserExists("sarah789", "sarah@gmail.com");
        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    public void testCheckLogin() throws SQLException {
        // Test case for checkLogin() method
        DBUser dbUser = new DBUser();
        boolean result1 = dbUser.checkLogin("john123", "pass123");
        boolean result2 = dbUser.checkLogin("JohnDoe", "him123");
        boolean result3 = dbUser.checkLogin("sarah789", "pass789");
        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    public void testGetEmail() throws SQLException {
        // Test case for getEmail() method
        DBUser dbUser = new DBUser();
        String result1 = dbUser.getEmail("janewest");
        String result2 = dbUser.getEmail("Polywertz");
        String result3 = dbUser.getEmail("sarah789");
        assertEquals("jane@gmail.com", result1);
        assertEquals("Polywertz@gmail.com", result2);
        assertEquals("", result3);
    }

    @Test
    public void testAddUser() throws SQLException {
        // Test case for addUser() method
        DBUser dbUser = new DBUser();
        dbUser.addUser("jim1", "jimmy@gmail.cdsdom", "1231");
        boolean result = dbUser.checkLogin("jim1", "1231");
        assertTrue(!result);
    }
}
