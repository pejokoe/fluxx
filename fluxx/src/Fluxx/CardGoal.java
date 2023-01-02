package Fluxx;
//LAST CHANGE 2/01/2023
/* This class will be in charge of representing the Goal card.
 * Each GoalCard will be composed by two 'cardkeepers'.
 * Another type of Goal card is managed trough using polimorphism as having to constructor 
 * and a special variable to define the number of card keepers that all the players need to
 * have in order to choose to a winner (Special goals of the Fluxx game).
 * 
 */

public class CardGoal extends Card 
{
	
	private CardKeeper keeper1;
	private CardKeeper keeper2;
	private int special;

// First constructor for card goals composed by card keepers.
	public CardGoal(int idCard, CardKeeper keeper1, CardKeeper keeper2) 
	{
		super(idCard, keeper1.getNameCard() + " + " + keeper2.getNameCard());
		this.keeper1 = keeper1;
		this.keeper2 = keeper2;
	}
//Second constructor for special card goals.
	public CardGoal(String name, int idCard, int special) {
		super(idCard, name);
		this.special=special;
	}

// Method to obtain the first card keeper composing the card goal.
	public CardKeeper getKeeper1() 
	{
		return keeper1;
	}
// Method to obtain the second card keeper composing the card goal.
	public CardKeeper getKeeper2() 
	{
		return keeper2;
	}
// Method to obtain the number of card keepers that will be necessary to play a defined special goal card.
	public int special()
	{
		return special;
	}
//Method used hen a Goal card is played in class game.
	public void playCard(Game game)
	{
		game.playGoal(this);
	}
//Basic display method.
	public String display() 
	{
		return " Goal " + this.getNameCard();
	}
//Display extended method for both: Card goals composed by card keepers and Special Card Goals.
	public String display_extended() 
	{
		if(this.getNameCard().contains(" keepers (S)"))
		{
			return "This is the goal card'" + this.getNameCard() + "'. Playing it will change the "
					+ "winning condition to having "+this.special() + " keepers in your keeper area.\n";
		}
		else
		{
		return "This is the goal card'" + this.getNameCard() + "'. Playing it will change the "
			+ "winning condition to having these two keepers in your keeper area.\n";
		}
	}
}
