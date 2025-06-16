package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Player {
	private int playerID;
	
	public Player() {
		
	}
	
	public Player(int playerID){
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return playerID;
		
	}
	
	public boolean validateSignin(String username, String password) {
	    try (Connection conn = JDBC.getConnection();
	        Statement stmt = conn.createStatement()) {
	        String query = "SELECT * FROM players WHERE Username = '" + username + "' AND Password = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	        
	    }
	    
	}

	public int insertUser(String username, String password) {
	    int generatedId = -1;
	    String initializePlayer = "INSERT INTO players (Username, Password) VALUES (?, ?)";
	    String getPlayerID = "SELECT PlayerID FROM players ORDER BY PlayerID DESC LIMIT 1";
	    String initializePlayers_Cards = "INSERT INTO players_cards (PlayerID, CardID, CardQuantity) VALUES (?, ?, ?)";
	    
	    try (Connection conn = JDBC.getConnection()) {
	    	PreparedStatement signup = conn.prepareStatement(initializePlayer);
	    	signup.setString(1, username);
	    	signup.setString(2, password);
	        int row = signup.executeUpdate(initializePlayer, Statement.RETURN_GENERATED_KEYS); //add new player to players table

	        if (row == 1) {
	            ResultSet rs = signup.getGeneratedKeys();
	            if (rs.next()) {
	            	generatedId = rs.getInt(1);
	            	rs.close();
	            	
	            	//Statement stmt = conn.createStatement();
	        	    //ResultSet rs = stmt.executeQuery(getPlayerID);
	            	PreparedStatement newset = conn.prepareStatement(initializePlayers_Cards);
	            	//for (int i = 1; i <= 102; i++) {
	            		//newset.setString(1, );
	            	//}
	            }
	                
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }

	    return generatedId;
	}
}
