
public abstract class Card {
	protected int idCard;
	
	//I ADDED
	protected static String namecard;
	protected static String descriptioncard;
	protected static String typecard;
	protected static int totalquantitycard;
	//////////
	
	public Card(int idCard) {
		this.idCard = idCard;
	}
	
	public int getId() {
		return idCard;
	}
	
	abstract public void playCard(RuleArea ruleArea);
	// I ADDED
	public Card(int idcard, String namecard,String descriptioncard, String typecard, int totalquantitycard)
	{
	this.idcard=idcard;
	this.namecard=namecard;
	this.descriptioncard=descriptioncard;
	this.typecard=typecard;
	this.totalquantitycard=totalquantitycard;
	}
	public String getnamecard()
	{
	return namecard;
	}
	public String getdescriptioncard()
	{
	return descriptioncard;
	}
	public String gettypecard()
	{
	return typecard;
	}
	public int gettotalquantitycard()
	{
	return totalquantitycard;
	}

	public void suffle()
	{
	
	}
	////////////
}
