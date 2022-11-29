
public class CardKeeper extends Card {

	public CardKeeper(String name, int idCard) {
		super(idCard, name);
	}
	
	public void playCard(Game game) {
		game.playKeeper(this);
	}
	
	public String display() {
		return "Keeper " + this.getNameCard();
	}

}
