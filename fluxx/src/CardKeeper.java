
public class CardKeeper extends Card {

	//I ADDED
	private int idkeeper;
	//////
	@Override
	public void playCard() {
		// TODO Auto-generated method stub

	}
	//I ADDED
	public CardKeeper(int idkeeper)
	{
	super(idcard, namecard, descriptioncard,typecard,totalquantitycard);
	this.idkeeper=idkeeper;
	}
	public int getidkeeper()
	{
	return idkeeper;
	}
	//////

}
