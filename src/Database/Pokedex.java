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
		String query = "SELECT * FROM players_cards WHERE PlayerID = ? AND CardID = ? AND CardQuantity > 0";
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setInt(1, player.getPlayerID());
				pstmt.setString(2, cardFileID);
			    ResultSet rs = pstmt.executeQuery();
			    if( !rs.next())
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
		String query = "SELECT CardName FROM cards WHERE CardID = ?";
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, cardFileID);
			    ResultSet rs = pstmt.executeQuery();
			    if(rs.next())
			    	return rs.getString("CardName");
			    
		} catch (Exception e) {
	        e.printStackTrace();
	        
		}
		return "CARD_NOT_FOUND";
		
	}
	
	//return card description
	public String fetchCardDescription(String cardFileID) {
		String query = "SELECT CardDescription FROM cards WHERE CardID = ?";
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setString(1, cardFileID);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("CardDescription");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "DESCRIPTION_NOT_FOUND";
		
	}
	
	//return current card count
	public int fetchCardCount(String cardFileID) {
		String query = "SELECT CardQuantity FROM players_cards WHERE PlayerID = ? AND CardID = ?";
		
		try (Connection conn = JDBC.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setInt(1, player.getPlayerID());
				pstmt.setString(2, cardFileID);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getInt("CardQuantity");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return 1;
		
	}
	
	//CardQuantity ±1
	public void changeCardCount(String cardFileID, int i) {
		String check = "SELECT CardQuantity FROM players_cards WHERE PlayerID = ? AND CardID = ?";
		String update = "UPDATE players_cards SET CardQuantity = CardQuantity + ? WHERE PlayerID = ? AND CardID = ?";
		
		try (Connection conn = JDBC.getConnection()){
			try(PreparedStatement checkPstmt = conn.prepareStatement(check)){
				checkPstmt.setInt(1, i);
				checkPstmt.setInt(2, player.getPlayerID());
				checkPstmt.setString(3, cardFileID);
				ResultSet rs = checkPstmt.executeQuery();
				if(rs.next()) { //if cardQuantity exist for logged-in playerID & selected cardID, increase by 1
					try(PreparedStatement updatePstmt = conn.prepareStatement(update)){
						updatePstmt.setInt(1, player.getPlayerID());
						updatePstmt.setString(2, cardFileID);
						updatePstmt.executeUpdate();
					}
				}	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
