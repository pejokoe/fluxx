package Fluxx;

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

	public String display_extended() {
		return "This is the keeper card '" + this.getNameCard() + "'. By playing it, it will be "
				+ "placed in your keeper area.\n";
	}

}
