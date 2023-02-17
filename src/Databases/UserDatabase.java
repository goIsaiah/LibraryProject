package Databases;

import java.util.ArrayList;

import DomainObjects.User;
public class UserDatabase {
	
	private static ArrayList<User> users = new ArrayList<User>();
	
	public UserDatabase() {
		
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	public User getUser(User user) {
		if(users.contains(user)) {
			return users.get(users.indexOf(user));
		}
	}

	public boolean addUser(User user) throws Exception{
		if(users.contains(user))throw new Exception(); 
		
		else {
			users.add(user);
			return true;
		}
		return false; 
		
	}
	
	public boolean removeUser(User user) {
		if(users.contains(user)) {
			users.remove(users.indexOf(user));
			return true;
		}
		return false;
	}
	

	

}
