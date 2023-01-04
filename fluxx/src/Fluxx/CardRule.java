package Fluxx;

public class CardRule extends Card {
	private String which = "";
	private int limit;
	
	public CardRule(String which, int limit, int idCard, String name) {
		super(idCard, name);
		this.which = which;
		this.limit = limit;
	}
	
	
	public void playCard(Game game) {
		game.playRule(this);
	}
	
	public int getLimit() {
		return limit;
	}
	
	public String getWhich() {
		return which;
	}
	
	// regular display method
	public String display() {
		return " Rule " + this.getNameCard();
	}
	
	// extended display method
	public String display_extended() {
		return "This is the rule card '" + this.getNameCard() + "'. Playing it "
				+ "will change the given rule to the given number.\n";
	}
}