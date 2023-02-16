package GUI;

public class LibraryUI {
	
	private static Template template = new Template(); 
	
	public static void main(String[] args) {
		init(); 
	}
	
	private static void init() {
		try {
			template.closeOP();
			 new Profile(template);
		} catch (Exception e) {
		} 
	}
	
	
}
