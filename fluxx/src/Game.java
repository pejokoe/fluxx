import java.util.List;
import java.util.ArrayList;

public class Game {
	private static final int MINPLAYER = 2;
	private static final int MAXPLAYER = 6;
	
	private List<Card> deck;
	private List<Card> discardedPile;
	private List<Card> drawStack;
	private List<Player> players;
	
	public Game(int num_players) {
		deck = new ArrayList<>();
		discardedPile = new ArrayList<>();
		drawStack = new ArrayList<>();
		players = new ArrayList<>();
		initPlayers(num_players);
		initCards();
	}
	
	private void initPlayers(int players) {
		
	}
	
	private void initCards() {
		// rule cards
		deck.add(new CardRule("draw", 2));
		deck.add(new CardRule("draw", 3));
		deck.add(new CardRule("draw", 4));
		deck.add(new CardRule("draw", 5));
		
		deck.add(new CardRule("keeper", 2));
		deck.add(new CardRule("keeper", 3));
		deck.add(new CardRule("keeper", 4));

		deck.add(new CardRule("play", 2));
		deck.add(new CardRule("play", 3));
		deck.add(new CardRule("play", 4));
		deck.add(new CardRule("play", -1));
		
		deck.add(new CardRule("hand", 0));
		deck.add(new CardRule("hand", 1));
		deck.add(new CardRule("hand", 2));
		
		// keeper cards
		 
		// goal cards

		
		
		
	}
}
