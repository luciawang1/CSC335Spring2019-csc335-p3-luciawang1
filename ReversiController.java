
/**
 * @author Lucia Wang In MVC: Controller handles how the game works, such as
 *         what places on the board are valid or not valid, and whose turn it is
 */
public class ReversiController {

	public ReversiModel model;
	public int bScore;
	public int wScore;
	String[][] board;
	int dimension;
	public String human = "W";
	public String cpu = "B";
	public String BLANK = "_";

	/**
	 * Constructor
	 * 
	 * @param ReversiModel model
	 */
	public ReversiController(ReversiModel model) {
		this.model = model;
		this.board = model.board;
		this.dimension = model.DIMENSION;
		bScore = 0;
		wScore = 0;

	}

	/**
	 * Determines if the game is over
	 * 
	 * Game is over when cpuTurn and humanTurn are both false, or when there are no
	 * more blank spaces on the board
	 * 
	 * @return true/false;
	 */
	public boolean gameOver() {
		for (int r = 0; r < dimension; r++) {
			for (int c = 0; c < dimension; c++) {
				if (board[r][c] == "_") {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns physical representation of board as String
	 * 
	 * @return String board
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < model.DIMENSION; i++) {
			s += i + 1 + " ";
			for (int j = 0; j < model.DIMENSION; j++) {
				s += model.board[i][j] + " ";
			}
			s += "\n";
		}
		s += "  a b c d e f g h ";
		return s;
	}

	/**
	 * Returns score of W(white)
	 * 
	 * @return int score
	 */
	public int getWScore() {
		wScore = 0;
		for (int i = 0; i < model.DIMENSION; i++) {
			for (int j = 0; j < model.DIMENSION; j++) {
				if (model.board[i][j] == "W") {
					wScore += 1;
				}
			}
		}
		return wScore;

	}

	/**
	 * Returns the number of squares occupied by b
	 * 
	 * @return int score
	 */
	public int getBScore() {
		bScore = 0;
		for (int i = 0; i < model.DIMENSION; i++) {
			for (int j = 0; j < model.DIMENSION; j++) {
				if (model.board[i][j] == "B") {
					bScore += 1;
				}
			}
		}
		return bScore;
	}

	/**
	 * Actually prints out the board
	 */
	public void printScore() {
		System.out.println();
		System.out.println("The score is " + getWScore() + ":" + getBScore() + ".");
	}

	/**
	 * Represents a human playing the game
	 * 
	 * Checks all 8 directions of the board for valid moves, and updates the board
	 * based on which of those directions are valid directions
	 * 
	 * @param r represents the row
	 * @param c represents column
	 * @return true if the move is valid, false if it is not
	 */
	public boolean humanTurn(int r, int c) {
		String player = "W";
		String opp = "B";
		boolean turn = false;

		// check and update all 8 directions
		// left
		if (checkLeft(player, opp, r, c)) {
			updateLeft(player, opp, r, c);
			turn = true;
		}
		// right
		if (checkRight(player, opp, r, c)) {
			updateRight(player, opp, r, c);
			turn = true;
		}
		// up
		if (checkUp(player, opp, r, c)) {
			System.out.println("r=" + r + ", c=" + c);
			updateUp(player, opp, r, c);
			turn = true;
		}
		// down
		if (checkDown(player, opp, r, c)) {
			updateDown(player, opp, r, c);
			turn = true;
		}
		// bottom right
		if (checkBottomRight(player, opp, r, c)) {
			updateBottomRight(player, opp, r, c);
			turn = true;
		}
		// upper left
		if (checkUpperLeft(player, opp, r, c)) {
			updateUpperLeft(player, opp, r, c);
			turn = true;
		}
		// lower left
		if (checkLowerLeft(player, opp, r, c)) {
			updateLowerLeft(player, opp, r, c);
			turn = true;
		}
		// upper right
		if (checkUpperRight(player, opp, r, c)) {
			updateUpperRight(player, opp, r, c);
			turn = true;
		}
		return turn;
	}

	// rows/columns to flip the colors to
	private int up = -1;
	private int down = -1;
	private int left = -1;
	private int right = -1;
	private int upperLeftCol = -1;
	private int upperLeftRow = -1;
	private int lowerRightRow = -1;
	private int lowerRightCol = -1;
	private int lowerLeftRow = -1;
	private int lowerLeftCol = -1;
	private int upperRightRow = -1;
	private int upperRightCol = -1;

	// decrease col
	/**
	 * Checks if items on the left of the current row and column make the move valid
	 * 
	 * the move is valid only if the left piece is the opponent and there is one
	 * even further left that is the player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the left, false
	 *         otherwise
	 */
	private boolean checkLeft(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		c = c - 1;
		if (c >= 0 && board[r][c] != opp)
			return false;
		c = c - 1;
		while (c >= 0 && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				left = c + 1;
				return true;
			}
			c -= 1;
		}
		return false;
	}

	/**
	 * precondition: checkLeft returns true Updates the board after checkLeft
	 * returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the left that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the left, false
	 *         otherwise
	 */
	private void updateLeft(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (left < c) {
			board[r][left] = player;
			left += 1;
		}
	}

	// increase col
	/**
	 * Checks if items on the right of the current row and column make the move
	 * valid
	 * 
	 * the move is valid only if the right piece is the opponent and there is one
	 * even further right that is the player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the right, false
	 *         otherwise
	 */
	private boolean checkRight(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		c = c + 1;
		if (c < dimension && board[r][c] != opp)
			return false;
		c = c + 1;
		while (c < dimension && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				right = c;
				return true;
			}
			c += 1;
		}
		return false;
	}

	/**
	 * precondition: checkRight returns true
	 * 
	 * Updates the board after checkRight returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the left that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the right, false
	 *         otherwise
	 */
	private void updateRight(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (c < right) {
			board[r][c] = player;
			c += 1;
		}
	}

	// decrease row
	/**
	 * Checks if items above current row and column make the move valid
	 * 
	 * the move is valid only if the piece above is the opponent and there is one
	 * even further up that is the player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces above it, false
	 *         otherwise
	 */
	private boolean checkUp(String player, String opp, int r, int c) {

		if (!isValidMove(r, c))
			return false;
		r = r - 1;
		if (r >= 0 && board[r][c] != opp)
			return false;
		r = r - 1;
		while (r >= 0 && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				up = r;
				return true;
			}
			r -= 1;
		}
		return false;
	}

	/**
	 * precondition: checkUp returns true
	 * 
	 * Updates the board after checkUp returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones above that that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces above it, false
	 *         otherwise
	 */
	private void updateUp(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (up < r) {
			board[up][c] = player;
			up++;
		}
	}

	// increaseRow
	/**
	 * Checks if items below the current row and column make the move valid
	 * 
	 * the move is valid only if the piece below is the opponent and there is one
	 * even further underneath that is the player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces below it, false
	 *         otherwise
	 */
	private boolean checkDown(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		r = r + 1;
		if (r < dimension && board[r][c] != opp)
			return false;
		r = r + 1;
		while (r < dimension && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				down = r;
				return true;
			}
			r += 1;
		}
		return false;
	}

	/**
	 * precondition: checkDowns returns true
	 * 
	 * Updates the board after checkDown returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones below it that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the left, false
	 *         otherwise
	 */
	private void updateDown(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (r < down) {
			board[r][c] = player;
			r++;
		}
	}

	// increaseRow increaseCol
	/**
	 * Checks if items to the bottom right of the current row and column make the
	 * move valid
	 * 
	 * the move is valid only if the piece right below and to the right is the
	 * opponent and there is one even further below and to the right that is the
	 * player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the bottom right ,
	 *         false otherwise
	 */
	private boolean checkBottomRight(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		r += 1;
		c += 1;
		if (r < dimension && c < dimension && board[r][c] != opp) {
			return false;
		}
		r += 1;
		c += 1;
		while (r < dimension && c < dimension && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				lowerRightRow = r;
				lowerRightCol = c;
				return true;
			}
			r += 1;
			c += 1;
		}
		return false;
	}

	/**
	 * precondition: checkBottomRight returns true
	 * 
	 * Updates the board after checkLeft returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the bottom right of it that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the bottom right,
	 *         false otherwise
	 */
	private void updateBottomRight(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (r < lowerRightRow && c < lowerRightCol) {
			board[r][c] = player;
			r++;
			c++;
		}
	}

	// decreaseRow decreaseCol
	// increaseRow increaseCol
	/**
	 * Checks if items to the upper left of the current row and column make the move
	 * valid
	 * 
	 * the move is valid only if the piece right above and to the left is the
	 * opponent and there is one even further above and to the left that is the
	 * player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the lower left,
	 *         false otherwise
	 */
	private boolean checkUpperLeft(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		r -= 1;
		c -= 1;
		if (c >= 0 && r >= 0 && board[r][c] != opp)
			return false;
		r -= 1;
		c -= 1;
		while (r >= 0 && c >= 0 && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				upperLeftRow = r;
				upperLeftCol = c;
				return true;
			}
			r -= 1;
			c -= 1;
		}
		return false;
	}

	/**
	 * precondition: checkUpperLeft returns true
	 * 
	 * Updates the board after checkLeft returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the Top left that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the upper left,
	 *         false otherwise
	 */
	private void updateUpperLeft(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (upperLeftRow < r && upperLeftCol < c) {
			board[upperLeftRow][upperLeftCol] = player;
			upperLeftRow++;
			upperLeftCol++;
		}
	}

	// increaseRow decreaseCol
	// increaseRow increaseCol
	/**
	 * Checks if items to the lower left of the current row and column make the move
	 * valid
	 * 
	 * the move is valid only if the piece right below and to the left is the
	 * opponent and there is one even further below and to the zzz that is the
	 * player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the bottom right ,
	 *         false otherwise
	 */
	private boolean checkLowerLeft(String player, String opp, int r, int c) {

		if (!isValidMove(r, c))
			return false;
		r += 1;
		c -= 1;

		if (r < dimension && c >= 0 && board[r][c] != opp)
			return false;
		r += 1;
		c -= 1;
		while (r < dimension && c >= 0 && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				lowerLeftRow = r;
				lowerLeftCol = c;
				return true;
			}
			r += 1;
			c -= 1;
		}
		return false;
	}

	/**
	 * precondition: checkLowerLeft returns true
	 * 
	 * Updates the board after checkLowerLeft returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the lower left that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the lower left,
	 *         false otherwise
	 */
	private void updateLowerLeft(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (r < lowerLeftRow && lowerLeftCol < c) {
			board[r][lowerLeftCol] = player;
			r++;
			lowerLeftCol++;
		}
	}

	// decreaseRow increaseCol
	/**
	 * Checks if items to the upper right of the current row and column make the
	 * move valid
	 * 
	 * the move is valid only if the piece right above and to the right is the
	 * opponent and there is one even further above and to the right that is the
	 * player, with no blank spaces in between
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the lower right,
	 *         false otherwise
	 */

	private boolean checkUpperRight(String player, String opp, int r, int c) {
		if (!isValidMove(r, c))
			return false;
		r -= 1;
		c += 1;
		if (c < dimension && r >= 0 && board[r][c] != opp)
			return false;
		r -= 1;
		c += 1;
		while (r >= 0 && c < dimension && board[r][c] != BLANK) {
			if (board[r][c] == player) {
				upperRightRow = r;
				upperRightCol = c;
				return true;
			}
			r -= 1;
			c += 1;
		}
		return false;
	}

	/**
	 * precondition: checkUpperRight returns true
	 * 
	 * Updates the board after checkUpperRight returns true
	 * 
	 * This means the player will now occupy the row and column, and all the other
	 * ones to the upper right that count
	 * 
	 * @param String player is the current player
	 * @param String opp is the opponent
	 * @param        int r is the current row
	 * @param        int c is the current col
	 * 
	 * @return true if the move is valid based on the pieces to the upper right,
	 *         false otherwise
	 */
	private void updateUpperRight(String player, String opp, int r, int c) {
		board[r][c] = player;
		while (upperRightRow < r && c < upperRightCol) {
			board[upperRightRow][upperRightCol] = player;
			upperRightRow++;
			upperRightCol--;
		}
	}

	/**
	 * Determines if the move at a given row and columns is valid or not
	 * 
	 * The move is valid if row and columns are between 0 and 7 inclusive, and the
	 * board at row and column is a blank space
	 * 
	 * @param int row is the row of the board
	 * @param int col is the col of the board
	 * 
	 * @return boolean true if the move is valid, false otherwise
	 * @return true if the move is valid based on the pieces to the lower left,
	 *         false otherwise
	 */
	private boolean isValidMove(int r, int c) {
		return (((r >= 0 && r <= 7) && (c >= 0 && c <= 7) && model.board[r][c] == "_"));
	}

	public int cpuRow;
	public int cpuCol;

	public boolean cpuTurn() {
		for (int r = 0; r < dimension; r++) {
			for (int c = 0; c < dimension; c++) {
				if (this.checkLeft(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateLeft(cpu, human, r, c);
					return true;
				} else if (this.checkUp(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateUp(cpu, human, r, c);
					return true;
				} else if (this.checkBottomRight(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateBottomRight(cpu, human, r, c);
					return true;
				} else if (this.checkDown(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateDown(cpu, human, r, c);
					return true;
				} else if (this.checkRight(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateRight(cpu, human, r, c);
					return true;
				} else if (this.checkLowerLeft(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					if (r == 0 && c == 4)
						this.updateLowerLeft(cpu, human, r, c);
					return true;
				} else if (this.checkUpperLeft(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateUpperLeft(cpu, human, r, c);
					return true;
				} else if (this.checkUpperRight(cpu, human, r, c)) {
					cpuRow = r;
					cpuCol = c;
					this.updateUpperRight(cpu, human, r, c);
					return true;
				}
			}
		}
		return false;
	}
}
