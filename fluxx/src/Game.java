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
	
	boolean winner = false;
	int turn = 0;

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
		deck.add(new CardRule("play", 0, cardIdGenerator++, "Play all"));

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
	private List<CardGoal> createCardGoals(List<CardKeeper> cardKeepers) {
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

	// game routine
	private void start() {
		while (!winner) {
			prePhase();
			drawPhase();
			playPhase();
//			discardPhase();
			turn = (turn + 1) % players.size();
		}
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
	
	private void drawPhase() {
		int draw = ruleArea.getLimit("draw");
		if (draw == -1) {
			draw = 1;
		}
		while (draw > 0) {
			if (deck.isEmpty()) {
				shuffle(discardPile);
				deck = discardPile;
				discardPile = null;
			} else {
				players.get(turn).drawCard(deck.get(0));
				deck.remove(0);
			}
			draw--;
		}
	}
	
	private void prePhase() {
		System.out.printf("\n%s, it's your turn.\n\n", players.get(turn).getNickName());
		String input = ui.wordInput("Type 'help' to display all input options.\n"
				+ "All inputs displayed by calling 'help' are allowed too.\n"
				+ "Type 'done' or anything else to continue with your turn.\n");
		while (input != "done") {
			input = processWordInput(input);
		}
	}
	
	private String processWordInput(String input) {
		String info = "";
		switch (input) {
		case "showKeepers":
			for (Player player : players) {
				info += player.getNickName() + ":\n";
				for (CardKeeper keeper : player.getKeepers()) {
					info +=  keeper.getNameCard() + "\n";
				}
			}
			input = ui.wordInput(info);
			break;
		case "showGoals":
			if (cardGoal != null) {
				info = cardGoal.display();
			} else {
				info = "No goal in play yet.\n";
			}
			input = ui.wordInput(info);
			break;
		case "showRules":
			info = ruleArea.display();
			input = ui.wordInput(info);
			break;
		case "help":
			info = "Type 'showKeepers' to display all the keepers on the playing field.\n"
					+ "Type 'showGoals' to display the current goal.\n"
					+ "Type 'showRules' to display all current rules."
					+ "Type 'help' to display all input options.\n"
					+ "Type 'done' to continue with your turn.\n";
			input = ui.wordInput(info);
			break;
		default:
			input = "done";
		}
		return input;
	}
	
	private void playPhase() {
		int play = ruleArea.getLimit("play");
		if (play == -1) {
			play = 1;
		}
		int maxPlay = players.get(turn).Handcards().size();
		while(play > 0 && maxPlay > 0) {
			Card card = players.get(turn).playCard(ui);
			playCard(card);
			play--;
			maxPlay--;
		}
			
	}
		

	// generic play card method, uses dynamic lookup
	public void playCard(Card card) {
		System.out.printf("You played card: %s\n", card.display());
		card.playCard(this);
	}

	// card specific play methods
	public void playRule(CardRule cardRule) {
		ruleArea.playRule(cardRule, discardPile);
	}

	public void playKeeper(CardKeeper cardKeeper) {
		players.get(turn).playKeeper(cardKeeper);
	}

	public void playGoal(CardGoal cardGoal) {
		if (this.cardGoal != null) {
			discardPile.add(this.cardGoal);
		}
		this.cardGoal = cardGoal;
	}
}
