package logic_domain_objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DomainObjects.User;
import Logic.Authentication;

class AuthenticationTests {

	@Test
	void authenticationTest()
	{
		User user = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		Authentication a = new Authentication(user);

		//testing the authentication methods
		//correctName method
		assertTrue(a.correctName("abbey"));
		assertFalse(a.correctName("jimmy"));

		//correctPassword method
		assertTrue(a.correctPassword("abbey123"));
		assertFalse(a.correctName("Abbey123"));

		//authenticated method
		assertEquals(user.getUsername(), "abbey");
		assertEquals(user.getPassword(), "abbey123");
		assertTrue(a.authenticated(a.correctName("abbey"), a.correctPassword("abbey123")));
		assertFalse(a.authenticated(a.correctName("abbey"), a.correctPassword("abbey1234")));
		assertFalse(a.authenticated(a.correctName("jimmy"), a.correctPassword("abbey123")));
	}
	

}
