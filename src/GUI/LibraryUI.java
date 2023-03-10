package GUI;

import java.util.Scanner;

public class LibraryUI {
	public static String sqlpassword;
	
	public LibraryUI() {
		init();
	}
	
	public static void main(String[] args) {
		new LibraryUI();
	}
	
	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter SQL password: ");
		sqlpassword = sc.nextLine();
		sc.close();
		try {
			 new Template();
		} catch (Exception e) {
		} 
	}
	
}
