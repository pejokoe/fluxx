import java.util.HashMap;
import java.util.List;

public class RuleArea {
	private HashMap<String, CardRule> limits;
	
	public RuleArea() {
		limits = new HashMap<>();
		limits.put("draw", null);
		limits.put("keeper", null);
		limits.put("play", null);
		limits.put("hand", null);
	}
	
	// set specific limit
	public void playRule(CardRule cardRule, List<Card> discardedPile) {
		String which = cardRule.getWhich();
		if (limits.get(which) != null) {
			discardedPile.add(limits.get(which));
		}
		limits.replace(cardRule.getWhich(), cardRule);
	}
	
	// return limit, -1 if no limit set
	public int getLimit(String which) {
		if (limits.get(which) != null) {
			return limits.get(which).getLimit();
		} else {
			return -1;
		}
	}
	
	public String display() {
		return "still to do";
//		return "The rules are: \n"
//				+ "Draw: " + draw + " cards.\n"
//				+ "Keeper limit: " + keeper + " keepers.\n"
//				+ "Play: Maximum " + play + " cards.\n"
//				+ "Hand limit: " + hand + " cards.";
	}
}
