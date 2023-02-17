package Databases;

import java.util.ArrayList;

import DomainObjects.User;
public class UserDatabase implements Database<User> {
	
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
		return null;
	}
	
	@Override 
	public User getData(String s) {
		return null;
	}

	
	@Override
	public boolean addData(User user) throws Exception{
		if(users.contains(user)) throw new Exception(); 
		else {
			users.add(user);
			return true;
		}
	}

	
	@Override 
	public boolean removeData(User user) {
		if(users.contains(user)) {
			users.remove(users.indexOf(user));
			return true;
		}
		return false;
	}
	

}
