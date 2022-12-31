package Fluxx;

/* This class will be in charge of representing the Goal card.
 * Each GoalCard will be composed by two 'cardkeepers'.
 */

public class CardGoal extends Card 
{
	private CardKeeper keeper1;
	private CardKeeper keeper2;
	private int special5;
	private int special10;



	public CardGoal(int idCard, CardKeeper keeper1, CardKeeper keeper2) 
	{
		super(idCard, keeper1.getNameCard() + " + " + keeper2.getNameCard());
		this.keeper1 = keeper1;
		this.keeper2 = keeper2;
	}

	public CardKeeper getKeeper1() 
	{
		return keeper1;
	}

	public CardKeeper getKeeper2() 
	{
		return keeper2;
	}

	public void playCard(Game game)
	{
		game.playGoal(this);
	}

	public String display() 
	{
		return " Goal " + this.getNameCard();
	}

	public String display_extended() 
	{
		return "This is the goal card'" + this.getNameCard() + "'. Playing it will change the "
			+ "winning condition to having these two keepers in your keeper area.\n";
	}


	//To set special goals if we can do it on time.
	public void setSpecialGoal5()
	{
	
	}
	public void setSpecialGoal10()
	{
	
	}
}
