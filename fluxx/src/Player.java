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
		return idplayer;
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
	public void setHandcards()
	{
		this.handcards = new ArrayList<>();
	}
	public List<Card> Handcards()
	{
		return handcards;
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
		handcards.add(pick);
	}
	public void drawCard()
	{
		handcards.remove(pick);
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
	
	//For special goals.
	
}
