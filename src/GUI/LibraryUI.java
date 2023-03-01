package GUI;

public class LibraryUI {
	
	
	public LibraryUI() {
		init();
	}
	
	public static void main(String[] args) {
		new LibraryUI();
	}
	
	private void init() {
		try {
			 new Template();
		} catch (Exception e) {
		} 
	}
	
}
