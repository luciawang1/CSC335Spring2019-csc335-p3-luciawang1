import java.util.Scanner;

public class ReversiView {

	private Scanner input;
	public ReversiModel model;
	public ReversiController controller;

	public ReversiView(ReversiModel model) {
		model = new ReversiModel();
		controller = new ReversiController(model);
		input = new Scanner(System.in);

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

	public void endGame() {
		if (controller.getWScore() > controller.getBScore()) {
			System.out.println("You win!");
		} else if (controller.getBScore() > controller.getWScore()) {
			System.out.println("CPU wins");
		} else {
			System.out.println("tie");
		}
	}

	public int convertColToInt(String col) {
		if (col.equals("a"))
			return 0;
		else if (col.equals("b"))
			return 1;
		else if (col.equals("c"))
			return 2;
		else if (col.equals("d"))
			return 3;
		else if (col.equals("e"))
			return 4;
		else if (col.equals("f"))
			return 5;
		else if (col.equals("g"))
			return 6;
		else if (col.equals("h"))
			return 7;
		else
			return -1;

	}

	public void gameOver() {
		if (controller.gameOver()) {
			System.out.println("Game Over");
			System.out.println("The final score is: " + controller.getBScore() + ":" + controller.getWScore());
			if (controller.getBScore() > controller.getWScore()) {
				System.out.println("You win!");
			} else if (controller.getWScore() > controller.getBScore()) {
				System.out.println("You lose.");
			} else {
				System.out.println("It's a tie.");
			}
			System.out.println("Would you liked to play again?");
			while (input.nextLine().toLowerCase() != "no") {
				play();
			}
		}
	}

	public void play() {
		boolean yourTurn = true;
		while (!controller.gameOver()) {
			if (yourTurn) {
				// System.out.print(controller.getBScore());
				// System.out.print(controller.bScore);
				// System.out.print(controller.bScore);
				// System.out.print(controller.bScore);

				controller.printScore();
				System.out.println("Where do you want to place your token?");
				// System.out.print(controller.bScore);
				String location = input.nextLine();
				int row = Integer.parseInt(location.toString().substring(1, 2)) - 1;
				if (row > 7 || row < 0)
					row = -1;
				int col = convertColToInt(location.toString().substring(0, 1));
				while (row == -1 || col == -1) {
					System.out.println("Please enter a character a-h followed by a number 0-7.");
					location = input.nextLine();
				}
				while (controller.humanTurn(row, col) == false) {
					System.out.println("Invalid move. Please try again.");
					location = input.nextLine();
					row = Integer.parseInt(location.toString().substring(1, 2)) - 1;
					col = convertColToInt(location.toString().substring(0, 1));

				}

				if (controller.humanTurn(row, col)) {
					displayBoard();
					yourTurn = !yourTurn;
				}

				this.displayBoard();
				yourTurn = !yourTurn;

			}
			if (!yourTurn) {
				controller.printScore();
				controller.cpuTurn();
				this.displayBoard();
				int cRow = controller.cpuRow + 1;
				String cCol = intToString(controller.cpuCol);
				System.out.println("computer places a piece at " + cCol + cRow);
				yourTurn = !yourTurn;
			}
		}
	}

	private String intToString(int i) {
		if (i == 0)
			return "a";
		if (i == 1)
			return "b";
		if (i == 2)
			return "c";
		if (i == 3)
			return "d";
		if (i == 4)
			return "e";
		if (i == 5)
			return "f";
		if (i == 6)
			return "g";
		else
			return "h";

	}
}
