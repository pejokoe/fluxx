import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {

	// IDs
	private int idGame;
	private int cardIdGenerator = 1;

	// player organization
	private static final int MINPLAYER = 2;
	private static final int MAXPLAYER = 6;
	private List<Player> players;
	private Player currentPlayer;

	// playing field
	private List<Card> deck;
	private List<Card> discardPile;
	private RuleArea ruleArea;
	private CardGoal cardGoal = null;

	// user interaction
	private UserInteraction ui = new UserInteraction();

	// I ADDED.

	private List<CardGoal> cardgoals;// Total list of all the card goals existing in the game.
	private List<CardGoal> cardgoalsgame; // List of card goal that were used in a specific game.
	private List<CardKeeper> currentgoalcard; // The current goal card. It is a combination of two keeper cards.

	public Game() {
		deck = new ArrayList<>();
		discardPile = new ArrayList<>();
		players = new ArrayList<>();

		ruleArea = new RuleArea();

		// I ADDED
		cardgoals = new ArrayList<CardGoal>();
		cardgoalsgame = new ArrayList<CardGoal>();
		currentgoalcard = new ArrayList<CardKeeper>();

		////////

		initPlayers();
		initCards();
		initGame();
		start();
	}

	// init players with nickname and id
	private void initPlayers() {
		int num_players = ui.intRange("How many players will participate?", MINPLAYER, MAXPLAYER);
		String tmpNickname = "";
		for (int i = 0; i < num_players; i++) {
			tmpNickname = ui.nickname(i);
			players.add(new Player(tmpNickname, i));
		}
	}

	// create cards
	private void initCards() {
		// rule cards
		deck.add(new CardRule("draw", 2, cardIdGenerator++, "Draw 2"));
		deck.add(new CardRule("draw", 3, cardIdGenerator++, "Draw 3"));
		deck.add(new CardRule("draw", 4, cardIdGenerator++, "Draw 4"));
		deck.add(new CardRule("draw", 5, cardIdGenerator++, "Draw 5"));

		deck.add(new CardRule("keeper", 2, cardIdGenerator++, "Keeper limit 2"));
		deck.add(new CardRule("keeper", 3, cardIdGenerator++, "Keeper limit 3"));
		deck.add(new CardRule("keeper", 4, cardIdGenerator++, "Keeper limit 4"));

		deck.add(new CardRule("play", 2, cardIdGenerator++, "Play 2"));
		deck.add(new CardRule("play", 3, cardIdGenerator++, "Play 3"));
		deck.add(new CardRule("play", 4, cardIdGenerator++, "Play 4"));
		deck.add(new CardRule("play", -1, cardIdGenerator++, "Play all"));

		deck.add(new CardRule("hand", 0, cardIdGenerator++, "Hand limit 0"));
		deck.add(new CardRule("hand", 1, cardIdGenerator++, "Hand limit 1"));
		deck.add(new CardRule("hand", 2, cardIdGenerator++, "Hand limit 2"));

		// keeper cards
		ArrayList<CardKeeper> cardKeepers = new ArrayList<>();
		cardKeepers.add(new CardKeeper("The Sun", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("The Moon", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Television", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Chocolate", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Cookies", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Time", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Love", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Sleep", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Bread", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Milk", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("The Rocket", cardIdGenerator++));
		cardKeepers.add(new CardKeeper("Death", cardIdGenerator++));
		deck.addAll(cardKeepers);

		// goal cards
		deck.addAll(createCardGoals(cardKeepers));
	}

	// deal cards
	private void initGame() {
		shuffle(deck);
		for (int i = 0; i < players.size(); i++) {
			List<Card> tmpCards = deck.subList(0, 3);
			players.get(i).setHandcards(tmpCards);
			deck.subList(0, 3).clear();
		}

	}

	// randomly create goals consisting of two keepers
	public List<CardGoal> createCardGoals(List<CardKeeper> cardKeepers) {
		List<CardGoal> cardGoals = new ArrayList<>();
		int numberCardGoals = 15; // setting this number to 15 keeps the types of cards balanced
		Random ran = new Random();
		CardKeeper keeper1 = null;
		CardKeeper keeper2 = null;
		for (int i = 0; i < numberCardGoals; i++) {
			while (true) {
				keeper1 = cardKeepers.get(ran.nextInt(cardKeepers.size()));
				keeper2 = cardKeepers.get(ran.nextInt(cardKeepers.size()));
				if (keeper1 != keeper2) {
					break;
				}
			}
			
			cardGoals.add(new CardGoal(i + cardIdGenerator++, keeper1, keeper2));
		}
		return cardGoals;
	}

	// start game routine
	public void start() {

	}

	public List<CardKeeper> pickcardgoal(List<CardGoal> cardgoals) {
		int maxcardgoal = 10;
		int randomcardgoal = (int) (Math.random() * maxcardgoal + 1);
		CardGoal ccg = cardgoals.get(randomcardgoal);
		List<CardKeeper> currentgoalcard = new ArrayList<CardKeeper>();
		currentgoalcard.add(ccg.getKeeper1());
		currentgoalcard.add(ccg.getKeeper2());
		return currentgoalcard;

	}

	public void checkWinPlayer(List<CardKeeper> currentgoalcard) {

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).accomplishgoal(currentgoalcard))
				players.get(i).winPlayer();

		}

	}

	private void shuffle(List<Card> toShuffle) {
		Collections.shuffle(toShuffle);
	}

	// generic play card method, uses dynamic lookup
	public void playCard(Card card) {
		card.playCard(this);
	}

	// card specific play methods
	public void playRule(CardRule cardRule) {
		ruleArea.playRule(cardRule, discardPile);
	}

	public void playKeeper(CardKeeper cardKeeper) {
		currentPlayer.playKeeper(cardKeeper);
	}

	public void playGoal(CardGoal cardGoal) {
		if (this.cardGoal != null) {
			discardPile.add(this.cardGoal);
		}
		this.cardGoal = cardGoal;
	}
}
