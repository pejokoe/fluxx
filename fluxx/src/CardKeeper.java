
public class CardKeeper extends Card {

	@Override
	public void playCard() {
		// TODO Auto-generated method stub

	}
//I ADDED
	private int idkeeper;
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
