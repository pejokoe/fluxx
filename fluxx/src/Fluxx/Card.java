package Fluxx;

public abstract class Card {
	private int idCard;
	private String nameCard;
	
	public Card(int idCard, String nameCard) {
		this.idCard = idCard;
		this.nameCard = nameCard;
	}
	
	public int getId() {
		return idCard;
	}
	
	public String getNameCard() {
		return nameCard;
	}

	abstract public String display();
	
	abstract public String display_extended();
	
	abstract public void playCard(Game game);
}