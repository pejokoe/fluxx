import java.util.ArrayList;
import java.util.List;

public class CardGoal extends Card {
private int idcardgoal;
private CardKeeper keeper1;
private CardKeeper keeper2;
private List<CardKeeper> cardgoal;
private int special5;
private int special10;
private String descriptiongoal;
	
	@Override
	public void playCard() {
		// TODO Auto-generated method stub

	}
	
	public CardGoal(int idcardgoal, CardKeeper keeper1, CardKeeper keeper2)
{ 	super(idcard, namecard, descriptioncard,typecard,totalquantitycard);
	this.idcardgoal=idcardgoal;
	this.cardgoal= new ArrayList<CardKeeper>();
}
public CardKeeper getKeeper1()
{
	return keeper1;
}
public CardKeeper getKeeper2()
{
	return keeper2;
}
public void setCardGoal()
{
	cardgoal.add(keeper1);
	cardgoal.add(keeper2);
	
}
public void descriptionGoal()
{
	System.out.println("The user with" + keeper1.getnamecard()+" and "+keeper2.getnamecard()+"will win the game");
	System.out.println("The " + keeper1.getnamecard()+" card: "+keeper1.getdescriptioncard());
	System.out.println("The " + keeper2.getnamecard()+" card: "+keeper2.getdescriptioncard());;
}
public void setSpecialGoal5()
{
	
}
public void setSpecialGoal10()
{
	
}

}
