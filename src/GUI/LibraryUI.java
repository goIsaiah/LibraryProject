package GUI;

public class LibraryUI {
	
	private Template template = new Template(); 
	
	public LibraryUI() {
		init();
	}
	
	public static void main(String[] args) {
		new LibraryUI();
	}
	
	private void init() {
		try {
			template.closeOP();
			 new Profile(template);
		} catch (Exception e) {
		} 
	}
	
	
}
