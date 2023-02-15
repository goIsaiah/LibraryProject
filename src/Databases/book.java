package Databases;

public class book {
	
	private String name;
	private String author;
	private String yearReleased;
	private String description;
	
	public book(String name, String author, String yearReleased, String description){
		this.name = name;
		this.author = author;
		this.yearReleased = yearReleased;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

