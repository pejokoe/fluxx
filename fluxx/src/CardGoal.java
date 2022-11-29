import java.util.ArrayList;
import java.util.List;

public class CardGoal extends Card {
	private CardKeeper keeper1;
	private CardKeeper keeper2;
	private String description;

	public CardGoal(int idCard, CardKeeper keeper1, CardKeeper keeper2) {
		super(idCard, keeper1.getNameCard() + " + " + keeper2.getNameCard());
		this.keeper1 = keeper1;
		this.keeper2 = keeper2;
	}
	
	public CardKeeper getKeeper1() {
		return keeper1;
	}

	public CardKeeper getKeeper2() {
		return keeper2;
	}
	
	public void playCard(Game game) {
		game.playGoal(this);
	}
	
	public String display() {
		return "Goal " + this.getNameCard();
	}
	
	public String display_extended() {
		return "This is the goal card'" + this.getNameCard() + "'. Playing it will change the "
				+ "winning condition to having these two keepers in your keeper area.\n";
	}

//	public void descriptionGoal() {
//		System.out.println(
//				"The user with" + keeper1.getNameCard() + " and " + keeper2.getNameCard() + "will win the game");
//		System.out.println("The " + keeper1.getNameCard() + " card: " + keeper1.getdescriptioncard());
//		System.out.println("The " + keeper2.getNameCard() + " card: " + keeper2.getdescriptioncard());
//		;
//	}

}
