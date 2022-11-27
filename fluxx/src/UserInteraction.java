import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteraction {
	private Scanner scanner;
	
	public UserInteraction() {
		scanner = new Scanner(System.in);
	}
	
	public String nickname(int player) {
		System.out.printf("Player %d, please give your nickname:\n", player);
		return scanner.next();
	}
	
	public int numPlayers(String instruction, int min, int max) {
		int input = intSelection(instruction);
		while(min > input || max < input) {
			System.out.println("Only 2 to 6 players are allowed!");
			input = intSelection(instruction);
		}
		return input;
	}
	
	public int intSelection(String instruction) {
		boolean isInt = false;
		int input = 0;
		System.out.println(instruction);
		while(!isInt) {
			try {
				input = scanner.nextInt();
				isInt = true;
			} catch (InputMismatchException e) {
				System.out.println("Please give an Integer as input.");
			}
		}
		return input;
	}
}
