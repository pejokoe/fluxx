package Fluxx;
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
		String ret = "";
		ret += " Draw: ";
		if (limits.get("draw") == null) {
			ret += "1 card\n" ;
		} else {
			ret += String.format("%s", limits.get("draw").getLimit()) + " cards\n";
		}
		ret += " Keeper limit: ";
		if (limits.get("keeper") == null) {
			ret += "no limit\n" ;
		} else {
			ret += String.format("%s", limits.get("keeper").getLimit()) + " keepers\n";
		}
		ret += " Play: ";
		if (limits.get("play") == null) {
			ret += "1 card\n" ;
		} else {
			ret += String.format("%s", limits.get("play").getLimit()) + " cards\n";
		}
		ret += " Hand cards limit: ";
		if (limits.get("hand") == null) {
			ret += "no limit\n" ;
		} else {
			ret += String.format("%s", limits.get("hand").getLimit()) + " cards\n";
		}
		return ret;
	}
}
