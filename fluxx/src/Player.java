import java.util.List;
import java.util.ArrayList;

public class Player {
	private int idPlayer;
	private String nickname;
	
	private List<Card> handCards;
	private List<CardKeeper> keepers;
	
	private Card pick;//Not to sure yet.
	private List<CardKeeper> currentgoal; //Two Keeper cards.
	private boolean turn=false;//The turn of the player.
	private boolean winplayer=false;//If the player wins.
	
	
	public Player(String nickname, int idPlayer) {
		this.nickname = nickname;
		this.idPlayer = idPlayer;
		handCards = new ArrayList<>();
		keepers = new ArrayList<>();
	}
	public String getNickName()
	{
		return nickname;
	}
	public int idPlayer()
	{
		return idPlayer;
	}
	
	public boolean getTurn()
	{
		return turn;
	}
	public void changeTurn()
	{
		this.turn=true;
	}
	public void winPlayer()
	{
		this.winplayer=true;
	}
	public void setHandcards(List<Card> startingHand)
	{
		handCards.addAll(startingHand);
	}
	public List<Card> Handcards()
	{
		return handCards;
	}
	
	public void setpick(Card pick)
	{
		this.pick=pick;
	}
	public Card getPick()
	{
		return pick;
	}
	public void pickCard()
	{
		handCards.add(pick);
	}
	public void drawCard()
	{
		handCards.remove(pick);
	}
	public void setcardgoal(List<CardKeeper> currentgoal)
	{
		
		this.currentgoal=new ArrayList<CardKeeper>();
	}
	public List<CardKeeper> getcurrentgoal()
	{
		
		return currentgoal;
	}
	public boolean accomplishgoal(List<CardKeeper> currentgoal)
	{
		int accomplishcount=0;
		boolean accomplish=false;
		for (int i=0; i<keepers.size();i++)
		{
			for (int j=0; j<currentgoal.size();j++)
			{
				if(keepers.get(i).getidkeeper()==currentgoal.get(j).getidkeeper())
				{
					accomplishcount++;
				}
			}
			
		}
		if(accomplishcount==2)  accomplish=true;
	
		return accomplish;
	}
	
	public Card playCard(UserInteraction ui) {
		for (int i = 0; i < handCards.size(); i++)
			System.out.printf("%d: %s", i, handCards.get(i).getNameCard());
		Card ret = handCards.get(ui.intRange("Choose a card", 0, handCards.size()));
		handCards.remove(ret);
		return ret;
	}
	
	public void playKeeper(CardKeeper cardKeeper) {
		keepers.add(cardKeeper);
	}
	
	//For special goals.
	
}
