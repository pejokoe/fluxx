import java.util.List;
import java.util.ArrayList;

public class Game {
	
	private int idGame;
	private int CardIdGenerator = 1;
	
	private static final int MINPLAYER = 2;
	private static final int MAXPLAYER = 6;
	
	private List<Card> deck;
	private List<Card> discardedPile;
	private List<Card> drawStack;
	private List<Player> players;
	
	private RuleArea ruleArea;
	private CardGoal cardGoal = null;
	
	public Game(int num_players) {
		deck = new ArrayList<>();
		discardedPile = new ArrayList<>();
		drawStack = new ArrayList<>();
		players = new ArrayList<>();
		
		ruleArea = new RuleArea();
		
		initPlayers(num_players);
		initCards();
		initGame();
	}
	
	private void initPlayers(int num_players) {
		
	}
	
	private void initCards() {
		// rule cards
		deck.add(new CardRule("draw", 2, CardIdGenerator++));
		deck.add(new CardRule("draw", 3, CardIdGenerator++));
		deck.add(new CardRule("draw", 4, CardIdGenerator++));
		deck.add(new CardRule("draw", 5, CardIdGenerator++));
		
		deck.add(new CardRule("keeper", 2, CardIdGenerator++));
		deck.add(new CardRule("keeper", 3, CardIdGenerator++));
		deck.add(new CardRule("keeper", 4, CardIdGenerator++));

		deck.add(new CardRule("play", 2, CardIdGenerator++));
		deck.add(new CardRule("play", 3, CardIdGenerator++));
		deck.add(new CardRule("play", 4, CardIdGenerator++));
		deck.add(new CardRule("play", -1, CardIdGenerator++));
		
		deck.add(new CardRule("hand", 0, CardIdGenerator++));
		deck.add(new CardRule("hand", 1, CardIdGenerator++));
		deck.add(new CardRule("hand", 2, CardIdGenerator++));
		
		// keeper cards
		deck.add(new CardKeeper("The Sun", CardIdGenerator++));
		deck.add(new CardKeeper("The Moon", CardIdGenerator++));
		deck.add(new CardKeeper("Television", CardIdGenerator++));
		deck.add(new CardKeeper("Chocolate", CardIdGenerator++));
		deck.add(new CardKeeper("Cookies", CardIdGenerator++));
		deck.add(new CardKeeper("Time", CardIdGenerator++));
		deck.add(new CardKeeper("Love", CardIdGenerator++));
		deck.add(new CardKeeper("Sleep", CardIdGenerator++));
		deck.add(new CardKeeper("Bread", CardIdGenerator++));
		deck.add(new CardKeeper("Milk", CardIdGenerator++));
		deck.add(new CardKeeper("The Rocket", CardIdGenerator++));
		deck.add(new CardKeeper("Death", CardIdGenerator++));
		deck.add(new CardKeeper("The Brain", CardIdGenerator++));
		deck.add(new CardKeeper("The Toaster", CardIdGenerator++));
		 
		// goal cards
		deck.add(new CardGoal("Chocolate", "Milk", CardIdGenerator++));
		deck.add(new CardGoal("Milk", "Cookies", CardIdGenerator++));
		deck.add(new CardGoal("Chocolate", "Cookies", CardIdGenerator++));
		deck.add(new CardGoal("Sleep", "Time", CardIdGenerator++));
		deck.add(new CardGoal("Rocket", "The Moon", CardIdGenerator++));
		deck.add(new CardGoal("Love", "Brain", CardIdGenerator++));
		deck.add(new CardGoal("The Moon", "The Sun", CardIdGenerator++));
		deck.add(new CardGoal("Television", "Toaster", CardIdGenerator++));
		deck.add(new CardGoal("The Sun", "Chocolate", CardIdGenerator++));
		deck.add(new CardGoal("Death", "Chocolate", CardIdGenerator++));
		deck.add(new CardGoal("The Rocket", "Brain", CardIdGenerator++));
		deck.add(new CardGoal("Bread", "Cookies", CardIdGenerator++));
		deck.add(new CardGoal("Bread", "The Toaster", CardIdGenerator++));
	}
	
	private void initGame() {
		
	}
	
	public void start() {
		
	}
}
