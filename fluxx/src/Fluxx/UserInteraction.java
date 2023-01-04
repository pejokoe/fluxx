package Fluxx;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteraction {
	private Scanner scanner;
	private Game game;
	
	public UserInteraction(Game game) {
		scanner = new Scanner(System.in);
		this.game = game;
	}
	
	public String nickname(int player) {
		System.out.printf("Player %d, please give your nickname:\n", player);
		return scanner.next();
	}
	
	public int intRange(String instruction, int min, int max) {
		int input = intSelection(instruction);
		while(min > input || max < input) {
			System.out.printf("Please give an integer between %d and %d!\n", min, max);
			input = intSelection(instruction);
		}
		return input;
	}
	
	public int intSelection(String instruction) {
		boolean isInt = false;
		int input = 0;
		System.out.println(instruction);
		while (!isInt) {
			try {
				input = scanner.nextInt();
				isInt = true;
			} catch (InputMismatchException e) {
				String s = scanner.next();
				if (s.equals("k")) {
					System.out.println(game.displayKeepers());
				} else if (s.equals("r")) {
					System.out.println(game.displayRules());
				} else if (s.equals("g")) {
					System.out.println(game.displayGoal());
				} else if (s.equals("help")) {
					System.out.println(game.displayHelp());
				}
				System.out.println("Please give an Integer as input.");
			}
		}
		return input;
	}
	
	public String wordInput(String instruction) {
		System.out.println(instruction);
		System.out.println("Type anything except k, g, r, h and 'help' to continue.");
		return scanner.next();
	}
}