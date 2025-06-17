package Database;

public class Card {
	private String cardID;
	private String cardFilePath;
	
	public Card(String cardID, String cardFilePath) {
		this.cardID = cardID;
		this.cardFilePath = cardFilePath;
	}
	
	public String getCardID(){
		return cardID;
	}
	
	public String getCardFilePath() {
		return cardFilePath;
	}
	
}
