
public abstract class Card {
	private int Id;
	private static int idGenerator = 1;
	
	public Card() {
		Id = idGenerator++;
	}
	
	public int getId() {
		return Id;
	}
	
	abstract public void playCard(RuleArea ruleArea);
}
