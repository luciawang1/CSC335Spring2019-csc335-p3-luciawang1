import java.util.Scanner;

public class ReversiView {

	private Scanner input;
	public ReversiModel model;
	public ReversiController controller;

	public ReversiView(ReversiModel model) {
		model = new ReversiModel();
		input = new Scanner(System.in);
		controller = new ReversiController(model);
	}

	public void startGame() {
		System.out.println("Welcome to Reversi");
		System.out.println();
		System.out.println("You are W.");
		System.out.println();

	}

	public void displayBoard() {
		System.out.println(controller.toString());
		System.out.println();
	}

	public void askQuestion() {
		System.out.println("Where would you like to place your token?");
	}

	public void computerMove() {
		System.out.println("The computer places a piece at ");
	}

	public void endGame() {
		if (controller.getWScore() > controller.getBScore()) {
			System.out.println("You win!");
		} else if (controller.getBScore() > controller.getWScore()) {
			System.out.println("CPU wins");
		} else {
			System.out.println("tie");
		}
	}

	public int getCol() throws IllegalInputException {
		char col = '0';
		col = (char) Integer.parseInt(input.toString().substring(0, 1));
		return convertColToInt(col);
	}

	public int convertColToInt(char col) {
		if (col == 'a')
			return 0;
		else if (col == 'b')
			return 1;
		else if (col == 'c')
			return 2;
		else if (col == 'd')
			return 3;
		else if (col == 'e')
			return 4;
		else if (col == 'f')
			return 5;
		else if (col == 'g')
			return 6;
		else if (col == 'h')
			return 7;
		else
			return -1;

	}

	public int getRow() {
		char row = 0;
		try {
			row = (char) Integer.parseInt(input.toString().substring(1, 2));
			if (0 <= row || row <= 7) {
				throw new IllegalInputException();
			}
		} catch (IllegalInputException e) {
			System.out.println("enter a letter a-h followed by a number 1-8");
		}
		return row;
	}

	public void play() {
		String input = "";
		int row = getRow();
		int col = getCol();
		if (col == -1) {
			try {
				throw new IllegalInputException("Number has to be between 1 and 7");
			} catch (IllegalInputException e) {
				// TODO Auto-generated catch block
				System.out.println("Enter new number");
			}
		}

		controller.humanTurn(2, 6);
		controller.cpuTurn();

	}

}
