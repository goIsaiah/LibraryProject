package Databases;

import java.util.ArrayList;

public class bookmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		book book1 = new book("Harry Potter: The Goblet of Fire", "JK Rowlking", "2000",
				"\n" +"It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft "
						+ "and Wizardry, and the mystery surrounding the entry of Harry's name into the Triwizard Tournament, in which he is forced to compete. "
						+ "The book was published in the United Kingdom by Bloomsbury and in the United States by Scholastic");

		book book2 = new book("The Hobbit", "J.R.R Tolkien", "1937",
				"\n" +"The Hobbit is set within Tolkien's fictional universe and follows the quest of home-loving Bilbo Baggins,"
						+ " the titular hobbit, to win a share of the treasure guarded by a dragon named Smaug. "
						+ "Bilbo's journey takes him from his light-hearted, rural surroundings into more sinister territory.");

		book book3 = new book("The Hunger Games", "Suzane Collins", "2008","\n" + "The Hunger Games is a 2008 dystopian "
				+ "novel by the American writer Suzanne Collins. It is written in the perspective of 16-year-old "
				+ "Katniss Everdeen, who lives in the future, post-apocalyptic nation of Panem in North America. ");

		book book4 = new book("The Maze Runner", "James Dashner", "2009",
				"\n" + "The Maze Runner is a 2009 young adult dystopian "
						+ "science fiction novel written by American author "
						+ "James Dashner and the first book released in The Maze Runner series");

		book book5 = new book("IT", "Stephen King", "1986",
				"\n" +"It is a 1986 horror novel by American author Stephen King. It was his 22nd book and his 17th novel"
						+ " written under his own name. The story follows the experiences of seven children as they are terrorized by"
						+ " an evil entity that exploits the fears of its victims to disguise itself while hunting its prey.");
		
		ArrayList<book> books = new ArrayList<book>(); 
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
		
		
		System.out.print(books);

	}

}
