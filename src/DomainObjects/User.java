package DomainObjects;

import java.util.*;
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
