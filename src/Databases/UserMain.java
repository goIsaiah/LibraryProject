package Databases;

import java.util.ArrayList;

import DomainObjects.Book;
import DomainObjects.User;

public class UserMain {
	
	
	User user1 = new User("jondoey124", "qwerty123", "jon@my.yorku.ca");
	User user2 = new User("potterfan7", "dlkklk", "potter@my.yorku.ca");
	User user3 = new User("poloboyw7", "you1240", "polo@my.yorku.ca");
	User user4 = new User("pizzaeater123", "asdf56", "pizza@my.yorku.ca");
	User user5 = new User("munchlover2", "pollui12", "munch@my.yorku.ca");
	
	ArrayList <User> users;
	
	UserMain(){
		users =new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
	}
	
	public ArrayList<User> getList(){
		return users;
	}
	
	
}
