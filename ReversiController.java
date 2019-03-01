import java.awt.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class ReversiController {

	public ReversiModel model;
	private String BLANK = "_";

	public ReversiController(ReversiModel model) {
		this.model = model;
	}

	public boolean gameOver() {
		for (int r = 0; r < model.DIMENSION; r++) {
			for (int c = 0; c < model.DIMENSION; c++) {
				if (model.board[r][c] == "_") {
					return false;
				}
			}
		}
		return true;
	}

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

	public int getWScore() {
		int count = 0;
		for (int i = 0; i < model.DIMENSION; i++) {
			for (int j = 0; j < model.DIMENSION; j++) {
				if (model.board[i][j] == "W") {
					count += 1;
				}
			}
		}
		return count;

	}

	public int getBScore() {
		int count = 0;
		for (int i = 0; i < model.DIMENSION; i++) {
			for (int j = 0; j < model.DIMENSION; j++) {
				if (model.board[i][j] == "B") {
					count += 1;
				}
			}
		}
		return count;
	}

	public void printScore() {
		System.out.println();
		System.out.println("The score is " + getWScore() + ":" + getBScore() + ".");
	}

	public void humanTurn(int r, int c) {
		move("W", r, c);
	}

	public java.util.List<Entry<Integer, Integer>> getValidMoves(String color) {
		// Returns a list of [x,y] lists of valid moves for the given player on the
		// given board.
		java.util.List<java.util.Map.Entry<Integer, Integer>> validMoves = new java.util.ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (isValidMove(color, x, y)) {
					java.util.Map.Entry<Integer, Integer> pair = new java.util.AbstractMap.SimpleEntry<>(x, y);
					validMoves.add(pair);
				}
			}
		}
		return validMoves;
	}

	private boolean isValidMove(String color, int r, int c) {
		// TODO Auto-generated method stub
		return ((model.board[r][c] == "_") && (r >= 0 && r <= 7) && (c >= 0 && c <= 7));
	}

	public void cpuTurn() {
		// Given a board and the computer's tile, determine where t
		// move and return that move as a [x, y] list.
		java.util.List<Entry<Integer, Integer>> possibleMoves = getValidMoves("B");
		Entry<Integer, Integer> bestMove = null;
		int bestScore = -1;
		for (Entry<Integer, Integer> move : possibleMoves) {
			ReversiModel m = new ReversiModel();
			m.board = model.board;
			int score = scoreOfBoard(m);
			if (score > bestScore) {
				bestScore = score;
				bestMove = move;
			}
		}
		model.board[bestMove.getKey()][bestMove.getValue()] = "B";
	}

	// b1 w2
	public int scoreOfBoard(ReversiModel m) {
		int bScore = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (m.board[i][j] == "B")
					bScore += 1;
			}
		}
		return bScore;
	}

	public void move(String color, int r, int c) {
		// Check if spot is full
		String oppColor = "";
		if (color == "B")
			oppColor = "W";
		if (color == "W")
			oppColor = "B";
		if (model.board[r][c] != "_") {
			System.exit(1);
		}
		if (!isValidMove(color, r, c)) {
			System.exit(1);
		}
		// Check if move is Valid...

		// Check Right Horizontal
		if (c < 6 && model.board[r][c + 1] != "_" && model.board[r][c + 1] == oppColor) {
			for (int pos = c + 2; pos < 8; pos++) {
				if (model.board[r][pos] == "_") {
					break;
				}
			}
			for (int pos = c + 2; pos < 8; pos++) {

				if (model.board[r][pos] == color) {
					fill(color, r, c, r, pos);
				}
			}
		}

		// Check Left Horizontal
		if (c > 1 && model.board[r][c - 1] != "_" && model.board[r][c - 1] == oppColor) {
			for (int pos = c - 2; pos > -1; pos--) {
				if (model.board[r][pos] == "_") {
					break;
				}
			}
			for (int pos = c - 2; pos > -1; pos--) {
				if (model.board[r][pos] == color) {
					fill(color, r, pos, r, c);
				}
			}
		}

		// Check Bottom Vertical
		if (r < 6 && model.board[r + 1][c] != "_" && model.board[r + 1][c] == oppColor) {
			for (int pos = r + 2; pos < 8; pos++) {
				if (model.board[pos][c] == "_") {
					break;
				}
			}
			for (int p = r + 2; p < 8; p++) {
				if (model.board[p][c] == color) {
					fill(color, r, c, p, c);
				}
			}
		}

		// Check Top Vertical
		if (r > 1 && model.board[r - 1][c] != "_" && model.board[r - 1][c] == oppColor) {
			for (int pos = r - 2; pos > -1; pos--) {
				if (model.board[pos][c] == "_") {
					break;
				}
			}
			for (int p = r - 2; p > -1; p--) {
				if (model.board[p][c] == color) {
					fill(color, p, c, r, c);
				}
			}

		}

		// Check Upper Right Diagonal
		if ((r > 1 && c < 6) && (model.board[r - 1][c + 1] != BLANK) && (model.board[r - 1][c + 1] == oppColor)) {
			int r1 = r - 2;
			int c1 = c + 2;
			while (r1 > -1 && c1 < 8) {
				if (model.board[r1][c1] == BLANK) {
					r--;
					c1++;
					break;
				}
			}
			int r2 = r - 2;
			int c2 = c + 2;
			while (r2 > -1 && c2 < 8) {
				if (model.board[r2][c2] == color) {
					fill(color, r - 2, c + 2, r2, c2);
				}
				r2++;
				c2--;
			}
		}

		// Check Upper Left Diagonal
		if ((r > 1 && c > 1) && (model.board[r - 1][c - 1] != BLANK) && (model.board[r - 1][c - 1] == oppColor)) {
			int i1 = r - 2;
			int j1 = c - 2;
			while (i1 > -1 && j1 > -1) {
				if (model.board[i1][j1] == BLANK) {
					j1--;
					i1--;
					break;
				}
			}
			int i2 = r - 2;
			int j2 = c - 2;
			while (i2 > -1 && j2 > -1) {
				if (model.board[i2][j2] == color) {
					fill(color, r - 2, c - 2, i2, j2);
				}
				i2--;
				j2--;
			}
		}

		// Check Lower Left Diagonal
		if ((r < 6 && c > 1) && (model.board[r + 1][c - 1] != BLANK) && (model.board[r + 1][c - 1] == oppColor)) {
			int r1 = r + 2;
			int c1 = c - 2;
			while (r1 < 8 && c1 > -1) {
				if (model.board[r1][c1] == BLANK) {
					c1++;
					r1--;
					break;
				}
			}
			int r2 = r + 2;
			int c2 = c - 2;
			while (r2 < 8 && c2 > -1) {
				if (model.board[r2][c2] == color) {
					fill(color, r + 2, c - 2, r2, c2);
				}
				r2++;
				c2--;
			}
		}
		// Check Lower Right Diagonal
		if ((r < 6 && c < 6) && (model.board[r + 1][c + 1] != BLANK) && (model.board[r + 1][c + 1] == oppColor)) {
			int r1 = r + 2;
			int c1 = c + 2;
			while (r1 < 8 && c1 < 8) {
				if (model.board[r1][c1] == BLANK) {
					c1++;
					r1++;
					break;
				}
			}
			int r2 = r + 2;
			int c2 = c + 2;
			while (r2 < 8 && c2 < 8) {
				if (model.board[r2][c2] == color) {
					fill(color, r + 2, c + 2, r2, c2);
					r2++;
					c2++;
				}
			}
		}
	}

	public void setColor(String color, int r, int c) {
		model.board[r][c] = color;
	}

	private void fill(String color, int r1, int c1, int r2, int c2) {

		// Horizontal Filling
		if (r1 == r2) {
			for (int pos = Math.min(c1, c2); pos <= Math.max(c1, c2); pos++) {
				model.board[r1][pos] = color;
			}
		}
		// vertical filling
		if (c1 == c2) {
			for (int pos = Math.min(r1, r2); pos <= Math.max(r1, r2); pos++) {
				model.board[pos][c1] = color;
			}
		}
		// diagonal filling
		else {
			for (int i = Math.min(r1, r2); i < Math.max(r1, r2); i++) {
				for (int j = Math.min(c1, c2); j < Math.max(c1, c2); j++) {
					model.board[i][j] = color;
				}
			}
		}
	}
}
