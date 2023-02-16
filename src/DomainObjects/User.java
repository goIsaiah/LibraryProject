package DomainObjects;

import java.util.*;

import Logic.AlreadyAFriendException;


public class User 
{

	//declaring attributes
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String birthday;
	private Date dateJoined;
	private ArrayList<Book> checkedOutBooks;
	private ArrayList<User> followerList; 
	
	//creating constructor
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;

		this.firstName = null;
		this.lastName = null;
		this.birthday = null;
		this.dateJoined = new Date();
		this.checkedOutBooks = null;
	}
	
	
	// to be tested 
	/**
	 * 
	 * @param u another User that this User wants to add as a freind or follow. 
	 * @return return true if u is added successfully, and false if not.
	 * @throws AlreadyAFriendException when the User u already is a friend or a follower of this User. 
	 */
	public boolean addFriend(User u) throws AlreadyAFriendException {
		
		if(this.followerList.contains(u)) throw new AlreadyAFriendException();
		else {
			this.followerList.add(u);
			return true; 
		}
		
	}
	
	
	
	// to be tested
	/**
	 * 
	 * @param u A user that is in this User's list of followers that this User wants to remove. 
	 */
	public void removeFriend(User u ) {
		if(this.followerList.contains(u))
			this.followerList.remove(this.followerList.indexOf(u));
	}

	
	//accessor and mutator methods
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday) 
	{
		this.birthday = birthday;
	}

	public Date getDateJoined() 
	{
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) 
	{
		this.dateJoined = dateJoined;
	}

	public ArrayList<Book> getCheckedOutBooks() 
	{
		return checkedOutBooks;
	}

	public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) 
	{
		this.checkedOutBooks = checkedOutBooks;
	}
}
