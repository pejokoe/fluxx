
public abstract class Card {
	protected int idCard;
	
	public Card(int idCard) {
		this.idCard = idCard;
	}
	
	public int getId() {
		return idCard;
	}
	
	abstract public void playCard(RuleArea ruleArea);
}
