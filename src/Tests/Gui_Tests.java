package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GUI.LogIn;

class Gui_Tests {

	@Test
	void test() {
		LogIn login = new LogIn();
		login.run();
	}

}
