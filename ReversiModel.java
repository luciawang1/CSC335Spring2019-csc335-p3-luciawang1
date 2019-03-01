
public class ReversiModel {

	private int bScore;
	private int wScore;
	private String black = "B";
	private String white = "W";
	public int DIMENSION = 8;
	public String[][] board;

	public ReversiModel() {
		board = new String[DIMENSION][DIMENSION];
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				board[i][j] = "_";
			}
			//System.out.println();
		}
		board[3][3] = white;
		board[3][4] = black;
		board[4][3] = black;
		board[4][4] = white;
	}

}
