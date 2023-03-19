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
	private String email;
	private String birthday;
	//	private String email;
	private Date dateJoined;
	private ArrayList<Book> checkedOutBooks;
	private ArrayList<User> followerList; 
	private ArrayList<Book> favouritesList;

	//creating constructor
	public User(String username) {
		this.username = username;
	}
	
	
	public User(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;

		this.firstName = null;
		this.lastName = null;
		this.birthday = null;
		this.dateJoined = new Date();
		this.checkedOutBooks = null;

		this.checkedOutBooks = new ArrayList<Book>();
		this.followerList = new ArrayList<User>();
		this.favouritesList = new ArrayList<Book>();
	}


	// to be tested 
	/**
	 * 
	 * @param u another User that this User wants to add as a friend/follow. 
	 * @return void
	 * @throws AlreadyAFriendException when the User u already is a friend or a follower of this User. 
	 */
	public void addFriend(User u) throws AlreadyAFriendException 
	{

		if(this.followerList.contains(u)) //if they already are followers
		{
			throw new AlreadyAFriendException();
		}
		else 
		{
			this.followerList.add(u);
		}

	}

	// to be tested
	/**
	 * 
	 * @param u A user that is in this User's list of followers that this User wants to remove. 
	 * @return void
	 * @throws Exception if the user to remove isn't found
	 */
	public void removeFriend(User u) throws Exception
	{
		if(this.followerList.contains(u))
		{
			this.followerList.remove(this.followerList.indexOf(u));
		}
		else
		{
			throw new Exception("failed, follower to remove not found");
		}
	}

	/**
	 * 
	 * @param b, the Book the user wants to checkout 
	 * @throws Exception when the book has already been checked out
	 * TODO: write junit test cases for this methods
	 */
	public void checkoutBook(Book b) throws Exception
	{
		if (checkedOutBooks.contains(b))
		{
			throw new Exception("book already checkout out");
		}
		else
		{
			checkedOutBooks.add(b);
		}
	}

	/**
	 * 
	 * @param b, the book the user wants to return
	 * @throws Exception if they have not checkout out the book previously
	 * TODO: write junit test cases for this method
	 */
	public void returnBook(Book b) throws Exception
	{
		if (checkedOutBooks.contains(b))
		{
			checkedOutBooks.remove(b); //remove the book from holds
		}
		else
		{
			throw new Exception("error, cannot return book because you have not checked it out");
		}
	}
	

	/**
	 * 
	 * @param b, the book the user wants to add to favourites
	 * @throws Exception if the book is already in the favourites list
	 * TODO: write junit test case for this method
	 */
	public void addToFavourites(Book b) throws Exception
	{
		if (favouritesList.contains(b))
		{
			throw new Exception("book is already added to favourites!");

		}
		else
		{
			favouritesList.add(b);
		}
	}
	/**
	 * 
	 * @param b, the book the user wants to remove from favourites
	 * @throws Exception if the book is not found in the favourites list (therefore, cannot remove the book)
	 * TODO: write junit test case for this method
	 */

	public void removeFromFavourites(Book b) throws Exception
	{
		if (favouritesList.contains(b))
		{
			favouritesList.remove(b);		
		}
		else
		{
			throw new Exception("error, book not found in favourites");
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public ArrayList<Book> getFavouritesList()
	{
		return favouritesList;
	}
	
	public ArrayList<User> getFollowersList()
	{
		return followerList;
	}
}
