import java.util.Scanner;

public class Reversi {

	public Scanner input;
	
	
	
	public Reversi() {
		input = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		ReversiView view = new ReversiView(model);
		
		
		view.startGame();
		System.out.println(controller.toString());
		controller.printScore();
		view.askQuestion();
		String location = "";
		//try {}
	}
	
}
