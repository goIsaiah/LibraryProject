package Logic;

import DomainObjects.User;

public class Authentication{
	private User user; 
	
	
//	public Authentication(User user) {
//		this.user = user; 
//	}
	
	public boolean correctName(String str) {
		
		if(str.equals(user.getUsername()))
			return true;
		
		return false; 
	}
	
	public boolean correctPassword(String str) {
		
		if(str.equals(user.getPassword())) 
			return true; 
		
		return false; 
	}


	
}
