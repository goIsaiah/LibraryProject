package database_tests;
import Databases.DBUser;
import DomainObjects.User;
import GUI.LibraryUI;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBUserTests {
    private DBUser dbUser;
    private LibraryUI libUi;
    @BeforeAll
    public void setUp() throws SQLException {
    	libUi = new LibraryUI();
        dbUser = new DBUser();
    }

    @Test
    public void testCheckUserExists() throws SQLException {
        boolean userExists = dbUser.checkUserExists("testUser3", "test@example.com");
        assertFalse(userExists, "User should not exist");
    }

    @Test
    public void testAddUserAndGetEmail() throws SQLException {
        dbUser.addUser("testUser4", "testPassword4", "test@example.com4");
        boolean userExists = dbUser.checkUserExists("testUser4", "test@example.com4");
        assertTrue(userExists, "User should exist");
        String email = dbUser.getEmail("testUser4");
        assertEquals("test@example.com4", email, "Email should be retrieved correctly");
    }
    
    @Test
    public void testGetUser() throws SQLException {
        int userIdToFetch = 1;
        String expectedUsername = "Polywertz";
        String expectedFName = "Sathira";
        User fetchedUser = dbUser.getUser(userIdToFetch);
        assertNotNull(fetchedUser, "Fetched user should not be null");
        assertEquals(expectedUsername, fetchedUser.getUsername());
        assertEquals(expectedFName, fetchedUser.getFirstName());
    }
    
    @Test
    public void testGetUserId() throws SQLException {
        String knownUsername = "Polywertz";
        User testUser = new User(knownUsername);
        int fetchedUserId = dbUser.getUserId(testUser);
        assertTrue(fetchedUserId > 0, "Fetched user_id should be greater than 0");
        User fetchedUser = dbUser.getUser(fetchedUserId);
        assertEquals(knownUsername, fetchedUser.getUsername(), "Fetched user should have the expected username");
    }
    
    @Test
    public void testBeFriendAndUnFriend() throws SQLException {
        String knownUsername1 = "Polywertz";
        String knownUsername2 = "rickastley";
        User testUser1 = new User(knownUsername1);
        User testUser2 = new User(knownUsername2);

        // Get the user_ids of the test users
        int userId1 = dbUser.getUserId(testUser1);
        int userId2 = dbUser.getUserId(testUser2);

        // Befriend testUser1 and testUser2
        dbUser.beFriendUser(userId1, userId2);

        // Check if they are friends now
        boolean areFriends = dbUser.isFriend(testUser1, testUser2);
        assertTrue(areFriends, "testUser1 and testUser2 should be friends after beFriendUser");

        // Unfriend testUser1 and testUser2
        dbUser.unFriendUser(userId1, userId2);

        // Check if they are not friends anymore
        areFriends = dbUser.isFriend(testUser1, testUser2);
        assertFalse(areFriends, "testUser1 and testUser2 should not be friends after unFriendUser");

        // Befriend testUser1 and testUser2 again
        dbUser.beFriendUser(userId1, userId2);

        // Check if they are friends again
        areFriends = dbUser.isFriend(testUser1, testUser2);
        assertTrue(areFriends, "testUser1 and testUser2 should be friends after the second beFriendUser");

        // Unfriend testUser1 and testUser2 again
        dbUser.unFriendUser(userId1, userId2);

        // Check if they are not friends anymore
        areFriends = dbUser.isFriend(testUser1, testUser2);
        assertFalse(areFriends, "testUser1 and testUser2 should not be friends after the second unFriendUser");
    }
    
    @AfterAll
    public void tearDown() throws SQLException {
        String sql = "DELETE FROM USERTABLE WHERE username = 'testUser4'";
        libUi.conn.createStatement().executeUpdate(sql);
    }
}
