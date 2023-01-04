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
	
	// add a rule to the rule area by playing the card
	public void playRule(CardRule cardRule, List<Card> discardedPile) {
		String which = cardRule.getWhich();
		if (limits.get(which) != null) {
			discardedPile.add(limits.get(which));
		}
		limits.replace(cardRule.getWhich(), cardRule);
	}
	
	// return a processable value for the requested limit
	public int getLimit(String which) {
		
		if (limits.get(which) != null) {
			return limits.get(which).getLimit();
		} else {
			if (which == "keeper" || which == "hand")
				return -1;
			else 
				return 1;
		}
	}
	
	// extensive display method to display all rules intelligibly
	public String display() {
		String ret = "";
		ret += " Draw: ";
		if (limits.get("draw") == null) {
			ret += "1 card\n" ;
		} else {
			ret += String.format("%s cards\n", limits.get("draw").getLimit());
		}
		ret += " Keeper limit: ";
		if (limits.get("keeper") == null) {
			ret += "no limit\n" ;
		} else {
			ret += String.format("%s keepers\n", limits.get("keeper").getLimit());
		}
		ret += " Play: ";
		if (limits.get("play") == null) {
			ret += "1 card\n" ;
		} else {
			if(limits.get("play").getLimit()==0)
			{
				ret += String.format("all cards\n");
			}
			else
			{
				ret += String.format("%s cards\n", limits.get("play").getLimit()) + " cards\n";
			}
			
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
