package Fluxx;
/*
 * This is the class in charge of creating each player and its functionalities such us:
 * Have a NickName.
 * Check if the player has the 'cardgoal' in play.
 * Manage the card that the player is choosing to play.

 */

import java.util.ArrayList;
import java.util.List;

public class Player {
	//Basic attributes.
	private int idPlayer;
	private String nickname;
	
	//List of cards for each player.
	private List<Card> handCards;
	private List<CardKeeper> keepers;
	
	private List<CardKeeper> currentgoal; //Two Keeper cards.
	
	private boolean winplayer=false;//To track if the player wins.
	
	//Constructor with the two basic instance variables as parameters and initialising the card arrays for each player.
	public Player(String nickname, int idPlayer) {
		this.nickname = nickname;
		this.idPlayer = idPlayer;
		handCards = new ArrayList<>();
		keepers = new ArrayList<>();
	}
	//Getter for instance variable.
	public String getNickName()
	{
		return nickname;
	}
	//Getter for instance variable.
	public int idPlayer()
	{
		return idPlayer;
	}
	//Getter for instance variable.
	public List<CardKeeper> getKeepers(){
		return keepers;
	}
	
	//Getter for instance variable.
	public List<Card> Handcards()
	{
		return handCards;
	}
	//Setter for instance variable.
	public void setHandcards(List<Card> startingHand)
	{
		handCards.addAll(startingHand);
	}
	//Setter for winner variable.
	public void winPlayer()
	{
		this.winplayer=true;
	}
	// Getter for winner variable.
	public boolean getWinPlayer()
	{
		return winplayer;
	}
	
	public void drawCard(Card card)
	{
		handCards.add(card);
	}
	//ADDED 30-12
	public void discardCard(Card card)
	{
		handCards.remove(card);
	}
	public void setcardgoal(List<CardKeeper> currentgoal)
	{
		
		this.currentgoal=new ArrayList<CardKeeper>();
	}
	public List<CardKeeper> getcurrentgoal()
	{
		
		return currentgoal;
	}
	//Method to verify if the player has the goal card in play.
	public boolean accomplishgoal(List<CardKeeper> currentgoal)
	{
		int accomplishcount=0;
		boolean accomplish=false;
		for (int i=0; i<keepers.size();i++)
		{
			for (int j=0; j<currentgoal.size();j++)
			{
				if(keepers.get(i).getId()==currentgoal.get(j).getId())
				{
					accomplishcount++;
				}
			}
			
		}
		if(accomplishcount==2)  accomplish=true;
	
		return accomplish;
	}
	
	public String displayHand() {
		String ret = "";
		for (int i = 0; i < handCards.size(); i++)
			ret += String.format(" %d: %s\n", i, handCards.get(i).display());
		return ret;
	}
	
	public String displayKeepers() {
		String ret = "";
		for (int i = 0; i < keepers.size(); i++) {
			ret += String.format(" %d: %s\n", i, keepers.get(i).display());
		}
		return ret;
	}
	
	//Method to return the card that the player choose.
	public Card playCard(UserInteraction ui, int maxPlay) {
		System.out.printf("\n%s, you must play %d card(s)!\n", nickname, maxPlay);
		System.out.println("\nYour hand cards are:");
		System.out.println(displayHand());
		Card ret = handCards.get(ui.intRange("Choose a card to play by entering its number.\n", 0, handCards.size()-1));
		//I REPLACED THE NEXT LINE FOR A METHOD WICH IS DOING THE SAME BUT IS BEING USEDD FROM ANOTHER CLASSES AS WELL;
		discardCard(ret);
		return ret;
	}
	
	public Card discardHand(UserInteraction ui, int discard) {
		System.out.printf("\n%s, you must discard %d card(s)!\n", nickname, discard);
		System.out.println("\nYour hand cards are:");
		System.out.println(displayHand());
		Card ret = handCards.get(ui.intRange("Choose a card to discard by entering its number.\n", 0, handCards.size()-1));
		discardCard(ret);
		return ret;
	}
	
	public Card discardKeeper(UserInteraction ui, int discard) {
		System.out.printf("\n%s, you must discard %d keeper(s)!\n", nickname, discard);
		System.out.println("\nYour keepers are:");
		System.out.println(displayKeepers());
		Card ret = handCards.get(ui.intRange("Choose a keeper to discard by entering its number.\n", 0, keepers.size()-1));
		keepers.remove(ret);
		return ret;
	}
	
	public void playKeeper(CardKeeper cardKeeper) {
		keepers.add(cardKeeper);
	}
	
	//For special goals.
	
}
