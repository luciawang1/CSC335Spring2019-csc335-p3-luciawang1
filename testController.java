import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testController {

	@Test
	void test1() {
		ReversiModel m = new ReversiModel();
		ReversiController c = new ReversiController(m);
		System.out.println(c.toString());
	}

	@Test
	void test2() {
		ReversiModel m = new ReversiModel();
		ReversiController c = new ReversiController(m);
		assertTrue(c.valid("B", 5, 4));
		assertTrue(c.valid("W", 5, 4));

		System.out.println(c.toString());
		
		System.out.println(c.toString());

		//c.cpuTurn(5, 4);
		//c.humanTurn(3, 5);

		// assertTrue(c.valid("B", 3, 6));
		// assertFalse(c.valid("W", 3, 5));
		// assertFalse(c.valid("W", 100, 100));
		System.out.println(c.toString());
		c.cpuTurn(2, 4);
		System.out.println(c.toString());

	}

}
