package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DomainObjects.User;
import Logic.AlreadyAFriendException;

class UserTests {

	@Test
	void userTest()
	{
		User abbey = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		User vince = new User("vince", "vince123", "vince@my.yorku.ca");
		User sathira = new User("sathira", "sathira123", "sathira@my.yorku.ca");
		User isaiah = new User("isaiah", "isaiah123", "isaiah@my.yorku.ca");
		User jet = new User("jet", "jet123", "jet@my.yorku.ca");
		User badfriend = new User("badfriend", "badfriend123", "badfriend@my.yorku.ca");

		//testing the addFriend method
		try
		{
			abbey.addFriend(vince);
			abbey.addFriend(sathira);
			abbey.addFriend(isaiah);
			abbey.addFriend(jet);
			abbey.addFriend(badfriend);
		}
		catch (AlreadyAFriendException e)
		{
			fail("error thrown unexpectedly");
		}

		//trying to friend a user that has already been friended
		try
		{
			abbey.addFriend(vince);
			fail("exception not thrown");
		}
		catch(AlreadyAFriendException e)
		{

		}


		//testing the remove friend function
		try
		{
			abbey.removeFriend(badfriend); //this should succeed
		}
		catch (Exception e)
		{
			fail("error thrown unexpectedly");
		}

		//trying to remove a friend not on friend list
		try
		{
			abbey.removeFriend(badfriend); //now they are no longer friends, this should throw excpetion
			fail("exception not thrown");
		}
		catch (Exception e)
		{

		}
	}

}
