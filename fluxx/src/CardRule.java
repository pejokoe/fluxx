
public class CardRule extends Card {
	private String which = "";
	private int limit;
	
	public CardRule(String which, int limit, int idCard, String name) {
		super(idCard, name);
		this.which = which;
		this.limit = limit;
	}
	
	// 
	public void playCard(Game game) {
		game.playRule(this);
	}
	
	public int getLimit() {
		return limit;
	}
	
	public String getWhich() {
		return which;
	}
	
	public String display() {
		return this.getNameCard();
	}
}
