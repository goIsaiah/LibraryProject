package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DomainObjects.User;
import Logic.Comment;
import Logic.Forum;


class ForumAndComment {

	@Test
	public void addCommenttest() {
		
		String msg = "This is a an amazing book to read"; 
		User user = new User("vince", "psw" ,"@email.com" );
		user.setFirstName("Vince");
		user.setLastName("Flores");
		Comment comment = new Comment(user,msg );
		Forum forum = new Forum(); 
		forum.addComment(comment);
		
		
		
	}
	
	
	@Test 
	public void removeComment() {
		Forum forum = new Forum(); 
		forum.removeComment(3);
		
	}

}
