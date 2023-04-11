package DomainObjects;

public class Comment {
	private User user; 
	private String message; 
	private String book_title;
	private int book_id; 
	
	

	/**
	 * 
	 * @param user The user that wrote the comment. 
	 * @param message The comment message that is to be stored and displayed to other users. 
	 * @param book_title The title of the book being commented on. 
	 * @param book_id The id of the book.
	 */
	public Comment(User user, String message, String book_title,int book_id) {
		this.user = user  ; 
		this.message = message; 
		this.book_id = book_id; 
		this.book_title = book_title; 
	}
	public String getUserName() {

		return user.getUsername();
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getBook_Id() {
		return this.book_id; 
	}
	
	public String getBook_Title() {
		return book_title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
