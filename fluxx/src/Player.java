import java.util.List;
import java.util.ArrayList;

public class Player {
	private int idPlayer;
	private String nickname;
	
	private List<Card> handCards;
	private List<CardKeeper> keepers;
	
	
	public Player(String nickname, int idPlayer) {
		this.nickname = nickname;
		this.idPlayer = idPlayer;
		handCards = new ArrayList<>();
		keepers = new ArrayList<>();
	}
	
	
}
