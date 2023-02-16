package Databases;

import java.util.ArrayList;

import DomainObjects.User;
public class UserDatabase {
	
	private static ArrayList<User> users = new ArrayList<User>();

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	

}
