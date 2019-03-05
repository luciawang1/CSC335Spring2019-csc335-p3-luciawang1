import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversiTest {
	public static void main(String[] args) {

		ReversiModel m = new ReversiModel();
		String b = m.getBlack();
		String w = m.getWhite();
		ReversiController c = new ReversiController(m);
		System.out.println(c.toString());

		System.out.println(c.checkRight(b, w, 3, 2));
		System.out.println(c.checkRight(w, b, 4, 2));

		System.out.println(c.checkDown(b, w, 2, 3));
		System.out.println(c.checkDown(w, b, 2, 4));

		System.out.println(c.checkLeft(b, w, 4, 5));
		System.out.println(c.checkLeft(w, b, 3, 5));

		System.out.println(c.checkUp(b, w, 5, 4));
		System.out.println(c.checkUp(w, b, 5, 3));

		// c.updateUp(w, b, 5, 3);
		// System.out.println(c.toString());

		// System.out.println(c.checkUpperRight(b, w, 5, 2));

		// c.updateUpperRight(b, w, 5, 2);
		// System.out.println(c.toString());

		// System.out.println(c.checkUpperRight(b, w, 5, 1));

		c.humanTurn(5, 3);
		// System.out.println(c.toString());

		c.cpuTurn();
		// System.out.println(c.toString());

		c.humanTurn(2, 3);
		// System.out.println(c.toString());

		assertEquals(c.getWScore(), 5);
		assertEquals(c.getBScore(), 2);
		c.printScore();

		c.cpuTurn();
		// System.out.println(c.toString());

		c.humanTurn(3, 1);
		// System.out.println(c.toString());

		c.cpuTurn();
//		System.out.println(c.toString());

		c.humanTurn(1, 3);
		System.out.println(c.toString());
//		System.out.println();
		assertFalse(c.gameOver());

		c.cpuTurn();
		// System.out.println(c.toString());

		c.humanTurn(3, 5);
		// System.out.println(c.toString());

		c.cpuTurn();
		// System.out.println(c.toString());
		c.humanTurn(0, 1);
		// System.out.println(c.toString());

		c.cpuTurn();
		// System.out.println(c.toString());
		c.humanTurn(4, 5);
		// System.out.println(c.toString());

		c.cpuTurn();
		// System.out.println(c.toString());

		c.humanTurn(0, 5);
		// System.out.println(c.toString());

		c.cpuTurn();
		System.out.println(c.toString());

		System.out.println(c.checkBottomRight(w, b, 5, 2));
		System.out.println(c.checkDown(w, b, 5, 2));
		System.out.println(c.checkUp(w, b, 5, 2));
		System.out.println(c.checkLeft(w, b, 5, 2));
		System.out.println(c.checkRight(w, b, 5, 2));
		System.out.println(c.checkLowerLeft(w, b, 5, 2));
		System.out.println(c.checkUpperRight(w, b, 5, 2));
		System.out.println(c.checkUpperLeft(w, b, 5, 2));

		c.humanTurn(5, 2);
		System.out.println(c.toString());

		c.cpuTurn();
		System.out.println(c.toString());

		c.humanTurn(2, 5);
		System.out.println(c.toString());

		c.cpuTurn();
		System.out.println(c.toString());
	}

//	  0 1 2 3 4 5 6 7 
//  0 _ _ _ _ _ _ _ _ 
//	1 _ _ _ _ _ _ _ _ 
//	2 _ _ _ _ _ _ _ _ 
//	3 _ _ _ W B _ _ _ 
//	4 _ _ _ B W _ _ _ 
//	5 _ _ _ _ _ _ _ _ 
//	6 _ _ _ _ _ _ _ _ 
//	7 _ _ _ _ _ _ _ _ 

}
