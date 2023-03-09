package Logic;

import DomainObjects.User;

public class Comment {
	private User user; 
	private String message; 

	
	/*
	 * TODO get main user from database 
	 * 
	 */
	
	public Comment(User user, String message) {
		this.user = user  ; 
		this.message = message; 
	
	}
	public String getUser() {

		return user.getUsername();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
