package Logic;

import DomainObjects.User;

public class Comment {
	private User user; 
	private String message; 
	private int id;
	
	
	public Comment(User user,int id, String message) {
		this.user = user  ; 
		this.message = message; 
		this.id =id; 
	}
	public String getUser() {
		return user.getFirstName()+" "+ user.getLastName();
	}
	
	

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Comment [user=" + user.getFirstName() + " " +user.getLastName() +
		 "\n message=" + message + "\n id=" + id + "]";
	}
	
}
