package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Pokedex {
	private Player player;
	private int setSize = 102;
	private ArrayList<Card> cards = new ArrayList<>();
	
	//initialize Pokedex
	Pokedex(Player player){
		this.player = player;
		for (int i = 0; i < setSize; i++) {
			String paddedNo = String.format("%03d", i+1);
			String cardFileID = "BS" + paddedNo; //BS001, BS002, ...
			String cardFilePath = "images/" + cardFileID; //image/BS001, image/BS002, ...
			Card card = new Card(cardFileID, cardFilePath);
			cards.add(card);
			
		}
		
	}
	
	//indicate cards not yet acquired
	public boolean missingCard(String cardFileID) {
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement getCard = conn.prepareStatement(
						"SELECT * FROM players_cards WHERE PlayerID = ? AND CardID = ? AND CardQuantity > 0")) {
				getCard.setInt(1, player.getPlayerID());
				getCard.setString(2, cardFileID);
			    ResultSet rs = getCard.executeQuery();
			    if( !rs.next()) //selected card has quantity = 0
			    	return true;
			    
		} catch (Exception e) {
	        e.printStackTrace();
		}
		return false;
		
	}
	
	//return jpg path for overview display of card set
	public String fetchCardImg(int index) {
		boolean missing = missingCard(cards.get(index).getCardID());
		
		if (missing) //show the card's back
			return "images/PTCG_CardBack.jpg";
		else
			return cards.get(index).getCardIMGFile();
		
	}
	
	//return label overview display of card set
	public String fetchCardLabel(String cardFileID) {
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement fetchCardName = conn.prepareStatement("SELECT CardName FROM cards WHERE CardID = ?")) {
				fetchCardName.setString(1, cardFileID);
			    ResultSet rs = fetchCardName.executeQuery();
			    if(rs.next())
			    	return rs.getString("CardName");
			    
		} catch (Exception e) {
	        e.printStackTrace();
	        
		}
		return "CARD_NOT_FOUND";
		
	}
	
	//return card description
	public String fetchCardDescription(String cardFileID) {

		try (Connection conn = JDBC.getConnection();
				PreparedStatement fetchCardDesc = conn.prepareStatement("SELECT CardDescription FROM cards WHERE CardID = ?")){
				fetchCardDesc.setString(1, cardFileID);
				ResultSet rs = fetchCardDesc.executeQuery();
				if(rs.next())
					return rs.getString("CardDescription");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "DESCRIPTION_NOT_FOUND";
		
	}
	
	//return current card count
	public int fetchCardCount(String cardFileID) {
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement fetchCardCount = conn.prepareStatement(
						"SELECT CardQuantity FROM players_cards WHERE PlayerID = ? AND CardID = ?")){
				fetchCardCount.setInt(1, player.getPlayerID());
				fetchCardCount.setString(2, cardFileID);
				ResultSet rs = fetchCardCount.executeQuery();
				if(rs.next())
					return rs.getInt("CardQuantity");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return 1;
		
	}
	
	//CardQuantity ±1
	public void changeCardCount(String cardFileID, int i) {
		
		try (Connection conn = JDBC.getConnection()){
			try(PreparedStatement check = conn.prepareStatement("SELECT CardQuantity FROM players_cards WHERE PlayerID = ? AND CardID = ?")){
				check.setInt(1, i);
				check.setInt(2, player.getPlayerID());
				check.setString(3, cardFileID);
				ResultSet rs = check.executeQuery();
				if(rs.next()) { //if cardQuantity exist for logged-in playerID & selected cardID, increase by 1
					try(PreparedStatement update = conn.prepareStatement(
							"UPDATE players_cards SET CardQuantity = CardQuantity + ? WHERE PlayerID = ? AND CardID = ?")){
						update.setInt(1, player.getPlayerID());
						update.setString(2, cardFileID);
						update.executeUpdate();
					}
				}	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
