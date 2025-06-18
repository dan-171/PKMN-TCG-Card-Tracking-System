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
	public Pokedex(Player player){
		this.player = player;
		for (int i = 0; i < setSize; i++) {
			String paddedNo = String.format("%03d", i+1);
			String cardFileID = "BS" + paddedNo; //BS001, BS002, ...
			String cardFilePath = "resources/images/" + cardFileID + ".jpg"; //image/BS001.jpg, image/BS002.jpg, ...
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
	
	//return jpg path for overview display of full card set
	public String fetchCardImg(int index) {
		boolean missing = missingCard(cards.get(index-1).getCardID());
		
		if (missing) //show the card's back
			return "resources/images/PTCG_CardBack.jpg";
		else
			return cards.get(index-1).getCardFilePath();
		
	}
	
	//return card label
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
	
	//Card Search
	//CardSearch -> return CardFileID
	public String cardSearch(String cardName) {
		try (Connection conn = JDBC.getConnection();
				PreparedStatement fetchCardName = conn.prepareStatement("SELECT CardID FROM cards WHERE CardID LIKE ?")) {
				fetchCardName.setString(1, cardName);
				ResultSet rs = fetchCardName.executeQuery();
			    if(rs.next())
			    	return rs.getString("CardID");
			    
		} catch (Exception e) {
	        e.printStackTrace();
	        
		}
		return "CARD_NOT_FOUND";
		
	}
	
	//return jpg path for overview display of searched card
	public String fetchCardImg(String cardId) {
		boolean missing = missingCard(cardId);
		
		if (missing) //show the card's back
			return "resources/images/PTCG_CardBack.jpg";
		else
			return cards.get(Integer.parseInt(cardId.replaceAll("\\D", ""))).getCardFilePath();
	}
	
	//Card Filter
	//return filtered cardIDs
	public ArrayList<String> filterCards(String cardType, String type, String stage) {
	    ArrayList<String> filteredCardIDs = new ArrayList<>();
	    
	    StringBuilder sql = new StringBuilder("SELECT CardID FROM cards WHERE 1=1");
	    
	    if (cardType != null && !cardType.isEmpty())
	        sql.append(" AND CardType = ?");
	    if (type != null && !type.isEmpty())
	        sql.append(" AND Type = ?");
	    if (stage != null && !stage.isEmpty())
	        sql.append(" AND Stage = ?");

	    try (Connection conn = JDBC.getConnection();
	         PreparedStatement fetchFilteredCardID = conn.prepareStatement(sql.toString())) {
	        
	        int i = 1;
	        if (cardType != null && !cardType.isEmpty())
	        	fetchFilteredCardID.setString(i++, cardType);
	        if (type != null && !type.isEmpty())
	            fetchFilteredCardID.setString(i++, type);
	        if (stage != null && !stage.isEmpty())
	            fetchFilteredCardID.setString(i++, stage);

	        ResultSet rs = fetchFilteredCardID.executeQuery();
	        while (rs.next()) {
	            filteredCardIDs.add(rs.getString("CardID"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return filteredCardIDs;
	}

	//return jpg path for overview display of filtered card
	public String fetchFilteredCardImg(String cardId) {
		boolean missing = missingCard(cardId);
	    if (missing)
	        return "resources/images/PTCG_CardBack.jpg";
	    
	    for (Card card : cards) {
	        if (card.getCardID().equals(cardId)) {
	            return card.getCardFilePath();
	        }
	    }
	    return "ERR_CardID_Not_Found";
	}
}
