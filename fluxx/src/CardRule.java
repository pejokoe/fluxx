
public class CardRule extends Card {
	private String which = "";
	private int limit;
	
	public CardRule(String which, int limit, int idCard) {
		super(idCard);
		this.which = which;
		this.limit = limit;
	}
	public void playCard(RuleArea ruleArea) {
		ruleArea.setLimit(which, limit);
	}

}
