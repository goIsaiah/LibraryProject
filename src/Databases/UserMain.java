package Databases;

import java.util.ArrayList;

import DomainObjects.User;

public class UserMain {
	
	public static void main(String[] args) {

	User user1 = new User("jondoey124", "qwerty123");
	User user2 = new User("potterfan7", "dlkklk");
	User user3 = new User("poloboyw7", "you1240");
	User user4 = new User("pizzaeater123", "asdf56");
	User user5 = new User("munchlover2", "pollui12");
	
	ArrayList <User> users = new ArrayList<User>();
	
	users.add(user1);
	users.add(user2);
	users.add(user3);
	users.add(user4);
	users.add(user5);
	
	
	}
}