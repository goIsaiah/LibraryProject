package Logic;

import DomainObjects.*;

//to do: add table but assume there is already a table for reviews and ratings
public class Review 
{
	private String message;
	private User u;
	private Book b;
	private int likes;
	
	public Review(String message, Book b, User u)
	{
		this.message = message;
		this.b = b;
		this.u = u;
		this.likes = 0;
	}
	
	public void editMessage(String newmessage)
	{
		this.message = newmessage + " \n(Edited)";
	}
	
	public void likeMessage()
	{
		likes++;
	}
	
	public void dislikeMessage()
	{
		likes--;
	}
	
	public int getLikes()
	{
		return likes;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String toString()
	{
		String s = String.format("%s reviewed %s. \nReview: %s", u.getUsername(), b.getTitle(), message);
		return s;
	}
	
	public Book getBook()
	{
		return b;
	}
	
	public User getUser()
	{
		return u;
	}
	
}
