package Fluxx;

/*
 * This is the class in charge to make run all the main functionalities in the game, such us:
 * 1. Create players.
 * 2. Create cards.
 * 3. Start the game.
 * 4. Keep the gaming process.
 * 5. Check for the winner.
 * 6. Stop the game hen finds a winner. 
 */
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {

	//Instance variables:
	
	// IDs.
	private int cardIdGenerator = 1;

	// player organization.
	private static final int MINPLAYER = 2;
	private static final int MAXPLAYER = 6;
	private List<Player> players;


	// playing field.
	private List<Card> deck;
	private List<Card> discardPile;
	private RuleArea ruleArea;
	private CardGoal cardGoal = null;
	
	boolean winner = false;
	int turn = 0;

	// user interaction
	private UserInteraction ui = new UserInteraction(this);

	/* The card Goals are special cards, because they are formed by list of card keepers (decision taken to keep the scalability 
	 * feature of the software). So, there is some attributes that will be managed as ArrayList.
	 */
	private List<CardGoal> cardGoals;// Total list of all the card goals existing in the game.
	private List<CardKeeper> currentgoalcard; // The current goal card. It is a combination of two keeper cards.

	//Constructor with not parameters.
	public Game() {
		//Initialising main instance variables.
		deck = new ArrayList<>();
		discardPile = new ArrayList<>();
		players = new ArrayList<>();
		ruleArea = new RuleArea();
		cardGoals = new ArrayList<CardGoal>();		
		currentgoalcard = new ArrayList<CardKeeper>();
		
		//Starting the gaming process.
		initPlayers();
		initCards();
		initGame();
		start();
	}

	// Init players with nickname and id
	private void initPlayers() {
		int num_players = ui.intRange("How many players will participate?", MINPLAYER, MAXPLAYER);
		String tmpNickname = "";
		for (int i = 0; i < num_players; i++) {
			tmpNickname = ui.nickname(i);
			//I added this part on january 1.
			//To control different nicknames.
			for(int j = 0; j<players.size();j++)
			{
				if(players.get(j).getNickName().equals(tmpNickname))
				{						
					System.out.println("Please, choose another name there is already a Nick Name --> "+tmpNickname);				
					tmpNickname = ui.nickname(i);
					break;
				}
			
			}
			players.add(new Player(tmpNickname, i));
		}
	}

	// Create cards
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
		deck.add(new CardRule("play", 0, cardIdGenerator++, "Play all")); /* special case play all, uses 
																			 elsewise unused play limit 0 */

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
		
		//Special goals cards.
		ArrayList<CardGoal> cardSGoals = new ArrayList<>();
		for(int i=5; i<=10; i=i+5)
		{
			cardSGoals.add(new CardGoal( i+" keepers (S)", cardIdGenerator++,i));	
		}
		
		deck.addAll(cardSGoals);
	}

	// Deal cards
	private void initGame() {
		shuffle(deck);
		for (int i = 0; i < players.size(); i++) {
			List<Card> tmpCards = deck.subList(0, 3); //Initially 3 for each player.
			players.get(i).setHandcards(tmpCards);
			deck.subList(0, 3).clear();//Removing the elements from the sublist to start again for other player.
		}

	}

	// Randomly create goals consisting of two keepers
	private List<CardGoal> createCardGoals(List<CardKeeper> cardKeepers) {
		
		int numberCardGoals = 15; // setting this number to 15 keeps the types of cards balanced, it is possible to have repetitive cards.
		Random ran = new Random();
		CardKeeper keeper1 = null;
		CardKeeper keeper2 = null;
		//The next variables the type Collection are to have a list of 15 different keepers pair. 
		Collection<String> listOne = new HashSet<String>();
		Collection<String> listTemp = new ArrayList<>();
		int size=0; //Auxiliary variable.
		for (int i = 0; i < numberCardGoals; i++) {
			while (true) {
				keeper1 = cardKeepers.get(ran.nextInt(cardKeepers.size()));
				keeper2 = cardKeepers.get(ran.nextInt(cardKeepers.size()));
				if (keeper1 != keeper2) {//It can not exist a combination of the same 2 keepers as a card Goal.
					break;
				}
			}
			//Update the temporal list of the new random combination of keepers.
			listTemp = Arrays.asList(keeper1.getNameCard()+keeper2.getNameCard(),keeper2.getNameCard()+keeper1.getNameCard());
			//Update the general list with all combinations with not repetitions.
			listOne.addAll(listTemp);
			//It can not exist the same combination of card Keepers as card Goals, so next code will check that.
			if(cardGoals.size()==0){		
			//First time the combinations of card Keepers will be in an Arraylist.
				cardGoals.add(new CardGoal(i + cardIdGenerator++, keeper1, keeper2));
			}
			else if(cardGoals.size()>=1){
			/*From the second time of loop, it will be necessary verify if (so far) all the combinations were different, by checking the 
			 * the size of the Collection ListOne.
			 */
			
				if(listOne.size()==size){
				//If the combination is already in the List, it will have a new try to create the combination, by using i--.
					i--;
				}
				//Otherwise the cardGoal is created.
				else{
					cardGoals.add(new CardGoal(i + cardIdGenerator++, keeper1, keeper2));
				}
				
			}
			size=listOne.size();
		}
		/*
		  We have to delete this, it is just to test.
		System.out.printf("One:%s%n", listOne);
		System.out.print(listOne.size()); */
		return cardGoals;
	}
	

	// game routine
	private void start() {
		tutorial();
		boolean redraw = false;
		int draw = 1;
		while (!winner) {
			drawPhase(redraw, draw);
			prePhase();
			playPhase();
			discardPhase("hand");
			discardPhase("keeper");
			turn = (turn + 1) % players.size();
		}
	}


		//Method to check if the current goal was accomplished, but in the case of having an special card goal.
	public void checkWinSp() {
		int count=0;
		int max=cardGoal.special();
		int countwin=0;
		Player temp = null;

		for (int i = 0; i < players.size(); i++) 
		{
			if (players.get(i).getKeepers().size()>=max) 
			{
				if(players.get(i).getKeepers().size()>max)
				{//To obtain the max number of card keepers between the players that have more than # keeper cards.
					max=players.get(i).getKeepers().size();
					
				}
			count++; //To count the number of players that have more than the card keepers defined in th goal.
			}
		}		
		if(count==players.size())
		{//Case: accomplish the goal, so time to choose the one with more keeper cards.
			for (int i = 0; i < players.size(); i++) 
			{
				if (players.get(i).getKeepers().size()==max) 		
				{
					countwin++;
					temp=players.get(i);
				}
				
			}	

			if(countwin==1)
			{
				temp.winPlayer();
				winner=temp.getWinPlayer();
				System.out.println("Player " + temp.getNickName() + " wins!!!");
			}
			else
			{
				System.out.println("It is a tie; two or more players with same number of keepers, continue playing.");
			}
		}	
	}
	
	//Shuffling the cards.
	private void shuffle(List<Card> toShuffle) {
		Collections.shuffle(toShuffle);
	}
	
	private void tutorial() {
		String toPrint = "\nTutorial:\nAt all times, typing r shows the rules, k the keepers and g the goals.\n"
				+ "Selecting cards will be done by typing the according number.\n"
				+ "Typing 'help' will display all possible input options\n";
		System.out.println(toPrint);
	}
	
	//Managing the draw process of the game.
	private void drawPhase(boolean redraw, int draw) {
		if (!redraw) {
			draw = ruleArea.getLimit("draw");
		} else {
			System.out.printf("Due to a rule change %d more card(s) will be drawn.\n", draw);
		}
		while (draw > 0) {
			if (deck.isEmpty()) {
				shuffle(discardPile); //Simulation of real game, when the deck is empty, the discardPile is used to continue playing.
				deck = discardPile;
				discardPile.clear();
			} else {
				players.get(turn).drawCard(deck.get(0));
				deck.remove(0);
			}
			draw--;
		}
	}
	//Displaying the main help options in each turn.
	private void prePhase() {
		System.out.printf("\n%s, it's your turn.\n", players.get(turn).getNickName());
		String input = ui.wordInput("");
		while (input != "done") {
			input = processWordInput(input);
		}
	}
	
	//Displaying the detail of the cards if the player needs to.
	private String processWordInput(String input) {
		String info = "";
		switch (input) {
		case "k":
			info = displayKeepers();
			input = ui.wordInput(info);
			break;
		case "g":
			info = displayGoal();
			input = ui.wordInput(info);
			break;
		case "r":
			info = displayRules();
			input = ui.wordInput(info);
			break;
		case "h":
			info = displayHand();
			input = ui.wordInput(info);
			break;
		case "help":
			info = displayHelp();
			input = ui.wordInput(info);
			break;
		default:
			input = "done";
		}
		return input;
	}
	
	//After checking the help displayers, the player will be able to play their cards.
	private void playPhase() {
		int draw = ruleArea.getLimit("draw");
		int maxPlayRule = ruleArea.getLimit("play");
		int maxPlayHandcards = players.get(turn).Handcards().size();
		if (maxPlayRule == 0) { // case play rule == "play all"
			maxPlayRule = maxPlayHandcards + 1; /* this way, maxPlay will evaluate to maxPlayHands
													and all hand cards will be played */
		}
		int maxPlay = Math.min(maxPlayRule, maxPlayHandcards);
		while(maxPlay > 0) {
			Card card = players.get(turn).playCard(ui, maxPlay);
			playCard(card);
			maxPlay--;
			int draw_new = ruleArea.getLimit("draw"); // new rule cards taking immediate effects
			int play_new = ruleArea.getLimit("play"); // new rule cards taking immediate effects
			if (draw_new > draw) {
				boolean redraw = true;
				drawPhase(redraw, draw_new-draw);
				draw = draw_new;
			}
			if (play_new > maxPlayRule) {
				maxPlay += (play_new - maxPlayRule);
				maxPlay = Math.min(maxPlay, players.get(turn).Handcards().size());
			}
		}	
	}
		

	// Generic play card method, card.playCard() then uses dynamic lookup
	public void playCard(Card card) 
	{
		System.out.printf("You played card: %s\n", card.display());
		card.playCard(this);
	}

	
	// card specific play methods
	public void playGoal(CardGoal cardGoal) {
		if (this.cardGoal != null) {
			discardPile.add(this.cardGoal);
		}
		this.cardGoal = cardGoal;
		if (cardGoal.getNameCard().contains(" keepers (S)")) {
			checkWinSp();

		} else {
			checkWin();
		}
	}
	
	public void checkWin() {
		CardKeeper keeper1 = cardGoal.getKeeper1();
		CardKeeper keeper2 = cardGoal.getKeeper2();
		for (Player player : players) {
			if (player.getKeepers().contains(keeper1) && player.getKeepers().contains(keeper2)) {
				winner = true;
				System.out.println("GAME OVER\nPlayer " + player.getNickName() + " wins!!!");
			}
		}
	}
	public void playRule(CardRule cardRule) 
	{
		ruleArea.playRule(cardRule, discardPile);
	}
	

	public void playKeeper(CardKeeper cardKeeper) {
		players.get(turn).playKeeper(cardKeeper);
		if (cardGoal != null) {
			if (cardGoal.getNameCard().contains(" keepers (S)")) {
				checkWinSp();
			} else {
				checkWin();
			}
		}
	}
	
	public void discardPhase(String s) {
		int limit = ruleArea.getLimit(s);
		int noCards = 0;
		if (limit != -1) {
			if (s == "hand") 
				noCards = players.get(turn).Handcards().size();
			else if (s == "keeper")
				noCards = players.get(turn).getKeepers().size();
			int discard = noCards - limit;
			while(discard > 0) {
				Card card = null;
				if (s == "hand") {
					card = players.get(turn).discardHand(ui, discard);
				} else if (s == "keeper") {
					card = players.get(turn).discardKeeper(ui, discard); 
				}
				discardPile.add(card);
				discard --;
			}
		}
		if (s == "keeper") {
			System.out.printf("%s, your turn is over.\n\n\n", players.get(turn).getNickName());
		}
	}
	public String displayKeepers(){
		String info = "Keepers: \n";
		for (Player player : players) {
			if (player.getKeepers().isEmpty()) {
				info += " " + player.getNickName() + " has no keepers.\n";
			} else {
				info += " " + player.getNickName() + "'s keepers: \n";
				for (CardKeeper keeper : player.getKeepers()) {
					info += " " + keeper.display() + "\n";
				}
			}
		}
		return info;
	}
	
	public String displayGoal() {
		String info = "Current goal:\n";
		if (cardGoal != null) {
			info += cardGoal.display();
		} else {
			info += " No goal in play yet.\n";
		}
		return info;
	}
	
	public String displayRules() {
		String info = "Current rules:\n";
		info += ruleArea.display();
		return info;
	}
	
	public String displayHand() {
		String info = "Your hand cards:\n";
		info += players.get(turn).displayHand();
		return info;
	}
	
	public String displayHelp() {
		return "Type 'k' to display all the keepers on the playing field.\n"
				+ "Type 'g' to display the current goal.\n"
				+ "Type 'r' to display all current rules.\n"
				+ "Type 'h' to display your hand cards.\n"
				+ "Type 'help' to display all input options.\n";
	}
}
