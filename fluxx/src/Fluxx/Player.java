package Fluxx;
/*
 * this is the class in charge of creating each player and its functionalities such as:#
 * manage the card that the player is choosing to play.
 * manage hand cards
 * manage keeper cards
 */

import java.util.ArrayList;
import java.util.List;

public class Player 
{
	// Basic attributes.
	private int idPlayer;
	private String nickname;
	
	// List of cards for each player.
	private List<Card> handCards;
	private List<CardKeeper> keepers;
	
	// Constructor with the two basic instance variables as parameters and initialising the card arrays for each player.
	public Player(String nickname, int idPlayer) 
	{
		this.nickname = nickname;
		this.idPlayer = idPlayer;
		handCards = new ArrayList<>();
		keepers = new ArrayList<>();
	}
	
	// Getter for instance variable.
	public String getNickName()
	{
		return nickname;
	}
	
	// Getter for instance variable.
	public int idPlayer()
	{
		return idPlayer;
	}
	
	// Getter for instance variable.
	public List<CardKeeper> getKeepers(){
		return keepers;
	}
	
	// Adding card keepers to the ArrayList of keepers of the player.
	public void playKeeper(CardKeeper cardKeeper) {
		keepers.add(cardKeeper);
	}
	// Getter for instance variable.
	public List<Card> Handcards()
	{
		return handCards;
	}
	
	// Setter for instance variable.
	public void setHandcards(List<Card> startingHand)
	{
		handCards.addAll(startingHand);
	}
	
	// Adding a card to the ArrayList in hand of each player.
	public void drawCard(Card card)
	{
		handCards.add(card);
	}
	
	// Removing a card to the ArrayList in hand of each player.
	public void discardCard(Card card)
	{
		handCards.remove(card);
	}
	
	// Displaying the ArrayList of cards that are in hand of the player.
	public String displayHand() {
		String ret = "";
		for (int i = 0; i < handCards.size(); i++)
			ret += String.format(" %d:%s\n", i, handCards.get(i).display());
		return ret;
	}
	
	// Displaying the ArrayList of cards that are in "Keeper List" of the player.
	public String displayKeepers() {
		String ret = "";
		for (int i = 0; i < keepers.size(); i++) {
			ret += String.format(" %d:%s\n", i, keepers.get(i).display());
		}
		return ret;
	}
	
	// Method to return the card that the player chose interactively
	public Card playCard(UserInteraction ui, int maxPlay) {
		System.out.printf("\n%s, you must play %d card(s)!\n", nickname, maxPlay);
		System.out.println("\nYour hand cards are:");
		System.out.println(displayHand());
		Card ret = handCards.get(ui.intRange("Choose a card to play by entering its number.\n ", 0, handCards.size()-1));
		discardCard(ret);
		return ret;
	}
	
	// Method to, in the case of a hand limit, interactively discard hand cards
	public Card discardHand(UserInteraction ui, int discard) {
		System.out.printf("\n%s, you must discard %d card(s)!\n", nickname, discard);
		System.out.println("\nYour hand cards are:");
		System.out.println(displayHand());
		Card ret = handCards.get(ui.intRange("Choose a card to discard by entering its number.\n", 0, handCards.size()-1));
		discardCard(ret);
		return ret;
	}
	
	// Method to, in the case of a keeper limit, interactively discard keeper cards
	public Card discardKeeper(UserInteraction ui, int discard) {
		System.out.printf("\n%s, you must discard %d keeper(s)!\n", nickname, discard);
		System.out.println("\nYour keepers are:");
		System.out.println(displayKeepers());
		Card ret = keepers.get(ui.intRange("Choose a keeper to discard by entering its number.\n", 0, keepers.size()-1));
		keepers.remove(ret);
		return ret;
	}
}
