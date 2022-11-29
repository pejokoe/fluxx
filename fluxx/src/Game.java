import java.util.List;
import java.util.ArrayList;

public class Game {
	
	// IDs
	private int idGame;
	private int cardIdGenerator = 1;
	
	// player limits
	private static final int MINPLAYER = 2;
	private static final int MAXPLAYER = 6;
	
	// list off all cards and players, initialization
	// and control flow of the game
	private List<Card> deck;
	private List<Player> players;
	
	// playing field
	private List<Card> discardedPile;
	private List<Card> drawStack;
	private RuleArea ruleArea;
	private CardGoal cardGoal = null;
	
	// all user interaction
	private UserInteraction ui = new UserInteraction();
	
	//I ADDED.
		
		private Player currentplayer;
		private int numberplayers; //Between 2 and 6.
		private List<CardGoal> cardgoals;//Total list of all the card goals existing in the game.
		private List<CardGoal> cardgoalsgame; //List of card goal that were used in a specific game.
		private List<CardKeeper> currentgoalcard; //The current goal card. It is a combination of two keeper cards.
		private List<CardKeeper> cardkeepers; //Just to use the list to create the list of goal cards.
		
	public Game() {
		deck = new ArrayList<>();
		discardedPile = new ArrayList<>();
		drawStack = new ArrayList<>();
		players = new ArrayList<>();
		
		ruleArea = new RuleArea();
		
		//I ADDED
			cardgoals = new ArrayList<CardGoal>();
			cardgoalsgame = new ArrayList<CardGoal>();
			currentgoalcard= new ArrayList<CardKeeper>();
			cardkeepers= new ArrayList<CardKeeper>();
		////////
		
		initPlayers();
//		initCards();
//		initGame();
	}
	
	// init players with nickname and id
	private void initPlayers() {
		int num_players = ui.numPlayers("How many players will participate?", MINPLAYER, MAXPLAYER);
		String tmpNickname = "";
		for (int i = 0; i < num_players; i++) {
			tmpNickname = ui.nickname(i);
			players.add(new Player(tmpNickname, i));
		}
	}
	
//	// create cards
//	private void initCards() {
//		// rule cards
//		deck.add(new CardRule("draw", 2, cardIdGenerator++));
//		deck.add(new CardRule("draw", 3, cardIdGenerator++));
//		deck.add(new CardRule("draw", 4, cardIdGenerator++));
//		deck.add(new CardRule("draw", 5, cardIdGenerator++));
//		
//		deck.add(new CardRule("keeper", 2, cardIdGenerator++));
//		deck.add(new CardRule("keeper", 3, cardIdGenerator++));
//		deck.add(new CardRule("keeper", 4, cardIdGenerator++));
//
//		deck.add(new CardRule("play", 2, cardIdGenerator++));
//		deck.add(new CardRule("play", 3, cardIdGenerator++));
//		deck.add(new CardRule("play", 4, cardIdGenerator++));
//		deck.add(new CardRule("play", -1, cardIdGenerator++));
//		
//		deck.add(new CardRule("hand", 0, cardIdGenerator++));
//		deck.add(new CardRule("hand", 1, cardIdGenerator++));
//		deck.add(new CardRule("hand", 2, cardIdGenerator++));
//		
//		// keeper cards
//		deck.add(new CardKeeper("The Sun", cardIdGenerator++));
//		deck.add(new CardKeeper("The Moon", cardIdGenerator++));
//		deck.add(new CardKeeper("Television", cardIdGenerator++));
//		deck.add(new CardKeeper("Chocolate", cardIdGenerator++));
//		deck.add(new CardKeeper("Cookies", cardIdGenerator++));
//		deck.add(new CardKeeper("Time", cardIdGenerator++));
//		deck.add(new CardKeeper("Love", cardIdGenerator++));
//		deck.add(new CardKeeper("Sleep", cardIdGenerator++));
//		deck.add(new CardKeeper("Bread", cardIdGenerator++));
//		deck.add(new CardKeeper("Milk", cardIdGenerator++));
//		deck.add(new CardKeeper("The Rocket", cardIdGenerator++));
//		deck.add(new CardKeeper("Death", cardIdGenerator++));
//		deck.add(new CardKeeper("The Brain", cardIdGenerator++));
//		deck.add(new CardKeeper("The Toaster", cardIdGenerator++));
//		 
//		// goal cards
//		deck.add(new CardGoal("Chocolate", "Milk", cardIdGenerator++));
//		deck.add(new CardGoal("Milk", "Cookies", cardIdGenerator++));
//		deck.add(new CardGoal("Chocolate", "Cookies", cardIdGenerator++));
//		deck.add(new CardGoal("Sleep", "Time", cardIdGenerator++));
//		deck.add(new CardGoal("Rocket", "The Moon", cardIdGenerator++));
//		deck.add(new CardGoal("Love", "Brain", cardIdGenerator++));
//		deck.add(new CardGoal("The Moon", "The Sun", cardIdGenerator++));
//		deck.add(new CardGoal("Television", "Toaster", cardIdGenerator++));
//		deck.add(new CardGoal("The Sun", "Chocolate", cardIdGenerator++));
//		deck.add(new CardGoal("Death", "Chocolate", cardIdGenerator++));
//		deck.add(new CardGoal("The Rocket", "Brain", cardIdGenerator++));
//		deck.add(new CardGoal("Bread", "Cookies", cardIdGenerator++));
//		deck.add(new CardGoal("Bread", "The Toaster", cardIdGenerator++));
//	}
	
	// deal cards
	private void initGame() {
		
	}
	
	// start game routine
	public void start() {
		
	}
	
	//I ADDED
	
	//The next method is to have a list of Card Keepers to create the list of goal cards.
		public List<CardKeeper> createCardKeeper()
		{ 
//			cardkeepers.add(new CardKeeper("The Sun", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("The Moon", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Television", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Chocolate", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Cookies", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Time", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Love", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Sleep", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Bread", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Milk", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("The Rocket", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("Death", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("The Brain", cardIdGenerator++));
//			cardkeepers.add(new CardKeeper("The Toaster", cardIdGenerator++));
//			deck.addAll(Card(cardkeepers)); // Are two different objetcs, it is probable that we have to add some step.
			
			
		}
		public List<CardGoal> createCardGoals()
		{

			//Just to try, we are creating a list of 10 card goals, with random combinations of card keepers.
			int maxcardgoal=10;
			int numberkeepers=14;
			
			for(int j=0; j<maxcardgoal; j++)
			{
				
				int randomkeeper1 = (int)(Math.random()*numberkeepers+1);
				int randomkeeper2 = (int)(Math.random()*numberkeepers+1);
				if(randomkeeper1==randomkeeper2 && randomkeeper2!=numberkeepers) randomkeeper2++;
				else 
					if(randomkeeper1==randomkeeper2 && randomkeeper2==numberkeepers) randomkeeper2--;
					
				CardGoal cg=new CardGoal(j,cardkeepers.get(randomkeeper1), cardkeepers.get(randomkeeper2));
				cardgoals.add(cg);
				
			}
				
			return cardgoals;
		}
		public List<CardKeeper> pickcardgoal(List<CardGoal> cardgoals)
		{
			int maxcardgoal=10;
			int randomcardgoal = (int)(Math.random()*maxcardgoal+1);
			CardGoal ccg=cardgoals.get(randomcardgoal);
			List<CardKeeper> currentgoalcard= new ArrayList<CardKeeper>();
			currentgoalcard.add(ccg.getKeeper1());
			currentgoalcard.add(ccg.getKeeper2());
			return currentgoalcard;
	
		}
		public void checkWinPlayer(List<CardKeeper> currentgoalcard)
		{
			
					
			for(int i=0; i<players.size();i++)
			{
				if(players.get(i).accomplishgoal(currentgoalcard))    players.get(i).winPlayer();
			
			}
			
			
		}
}
