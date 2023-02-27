package DomainObjects;

public class Book 
{
	
	//declaring attributes
	private String title;
	private String author;
	private String isbn;
	private int numOfPages;
	private int yearPublished;
	private int rating;
	
	public Book(String title, String author, int yearPublished, int rating)
	{
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.rating = rating;
	}

	public Book(String title, String author, int yearPublished, String isbn)
	{
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.isbn=isbn;
	}
	
	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getIsbn() 
	{
		return isbn;
	}

	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}

	public int getNumOfPages() 
	{
		return numOfPages;
	}

	public void setNumOfPages(int numOfPages) 
	{
		this.numOfPages = numOfPages;
	}

	public int getYearPublished() 
	{
		return yearPublished;
	}

	public void setYearPublished(int yearPublished)
	{
		this.yearPublished = yearPublished;
	}

	public int getRating() 
	{
		return rating;
	}

	public void setRating(int rating) 
	{
		this.rating = rating;
	}
	
	

}
