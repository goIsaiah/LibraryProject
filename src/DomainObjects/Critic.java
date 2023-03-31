package DomainObjects;

public class Critic 
{
	private Book b;
	private User u;
	private Review review;
	private Rating rating;
	private int book_id;
	public Critic (Book b, User u, Review review, Rating rating, int book_id)
	{
		this.b = b;
		this.u = u;
		this.review = review;
		this.rating = rating;
		this.book_id = book_id;
		
	}

	public Book getBook() 
	{
		return b;
	}

	public void setBook(Book b) 
	{
		this.b = b;
	}

	public User getUser() 
	{
		return this.u;
	}

	public void setUser(User u) 
	{
		this.u = u;
	}

	public Review getReview() 
	{
		return review;
	}

	public void setReview(Review review) 
	{
		this.review = review;
	}

	public Rating getRating() 
	{
		return rating;
	}

	public void setRating(Rating rating) 
	{
		this.rating = rating;
	}
	
	public int getBookId()
	{
		return this.book_id;
	}
	
	public String toString()
	{
		String s = String.format("%s gave %s %d stars.\nReview: %s", u.getUsername(), b.getTitle(), rating.getRating(), review.getMessage());
		return s;
	}
	


}
