import java.util.HashMap;

public class RuleArea {
	private HashMap<String, Integer> limits;
	
	public RuleArea() {
		limits = new HashMap<>();
		limits.put("draw", 1);
		limits.put("keeper", -1);
		limits.put("play", 1);
		limits.put("hand", -1);
	}
	
	// set specific limit
	public void setLimit(String which, int limit) {
		limits.replace(which, limit);
	}
	
	// return limit, -1 if no limit set
	public int getLimit(String which) {
		return limits.get(which);
	}
}
