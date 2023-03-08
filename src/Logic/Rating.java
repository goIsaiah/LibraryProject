package Logic;

import DomainObjects.*;


public class Rating 
{
	private Book b;
	private User u;
	private int rating;
	
	public Rating(User u, Book b, int rating)
	{
		this.u = u;
		this.b = b;
		this.rating = 0;
	}
	
	public void setRating(int newRating)
	{
		this.rating = newRating;
	}
	
	public String toString()
	{
		String s = String.format("%s rated %s %d/5 stars", u.getFirstName(), b.getTitle(), this.rating);
		return s;
	}
	
	//get all attributes
	public User getUser()
	{
		return u;
	}
	
	public Book getBook()
	{
		return b;
	}
	
	public int getRating()
	{
		return rating;
	}

}
