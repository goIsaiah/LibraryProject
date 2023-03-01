package Logic;

import java.util.Random;

import DomainObjects.User;

public class Comment {
	private User user; 
	private String message; 
	private int likes; 
	private int id;
	public Comment(User user, String message) {
		this.user = user  ; 
		this.message = message; 
		Random random = new Random(); 
		this.likes=0;
		this.id =1000+ random.nextInt(120); 
	}
	
	public String getUser() {
		return user.getFirstName()+" "+ user.getLastName();
		
	}
	
	
	public int getLikes() {
		return likes;
	}
	
	public void addLikes() {
		this.likes += 1;
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
		 "\n message=" + message + "\n likes=" + likes + "\n id=" + id + "]";
	}
	
	
	
	
	
}
