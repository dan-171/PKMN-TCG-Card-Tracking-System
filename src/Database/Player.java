package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Player {
	private int playerID;
	private String playerName;
	private int cardQuantity;
	private Timestamp registerDate;

	public Player(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public String getPlayerName(){
	    return playerName;
	}

	public int getCardQuantity(){
	    return cardQuantity;
	}

	public Timestamp getRegistrationDate(){
	    return registerDate;
	}
	
	//register player
	public static int insertPlayer(String username, String password) {
	    int generatedId = -1;

	    try (Connection conn = JDBC.getConnection();
	    		PreparedStatement signUp = conn.prepareStatement(
	    				"INSERT INTO players (Username, Password, RegistrationDate) VALUES (?, ?, ?)",
	    				Statement.RETURN_GENERATED_KEYS)) {
	        signUp.setString(1, username);
	        signUp.setString(2, password);
	        // Set registration date to current timestamp
	        signUp.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

	        int row = signUp.executeUpdate();

	        if (row == 1) {
	        	
	            ResultSet rs = signUp.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	                rs.close();

	                PreparedStatement newSet = conn.prepareStatement(
	                		"INSERT INTO players_cards (PlayerID, CardID, CardQuantity) VALUES (?, ?, ?)");
	                for (int i = 0; i < 120; i++) {
	                    String cardID = String.format("BS%03d", i + 1);
	                    newSet.setInt(1, generatedId);
	                    newSet.setString(2, cardID);
	                    newSet.setInt(3, 0);
	                    newSet.addBatch();
	                }
	                newSet.executeBatch();
	                newSet.close();
	            }
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	    return generatedId;
	}
	
	//player sign in
	public static int validateSignIn(String username, String password) {
	    try (Connection conn = JDBC.getConnection();
	    	PreparedStatement validate = conn.prepareStatement("SELECT PlayerID FROM players WHERE Username = ? AND Password = ?")) {
	    	validate.setString(1, username);
	    	validate.setString(2, password);
	        ResultSet rs = validate.executeQuery();
	        if(rs.next()) {
	        	return rs.getInt("PlayerID");
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
        return 0;
	}
	
	//player forget pw -> reset pw
	public static boolean resetPassword(String playerID, String newPassword) {
	    boolean updated = false;
	    
	    try (Connection conn = JDBC.getConnection();
	         PreparedStatement updatePW = conn.prepareStatement("UPDATE players SET Password = ? WHERE ID = ?")) {
	    	updatePW.setString(1, newPassword);
	    	updatePW.setString(2, playerID);
	        
	        int row = updatePW.executeUpdate();
	        if (row == 1) {
	            updated = true;
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	    return updated;
	}
	
	//get player profile info
	public static Player loadPlayerProfile(int playerId) {
	    Player player = null;

	    try (Connection conn = JDBC.getConnection(); // fetch player name, registration date
	    		PreparedStatement getInfo = conn.prepareStatement("SELECT Username, RegistrationDate FROM players WHERE PlayerID = ?")) {
	        getInfo.setInt(1, playerId);
	        ResultSet rs = getInfo.executeQuery();

	        if (rs.next()) {
	        	player = new Player(playerId);
	            player.playerName = rs.getString("Username");
	            player.registerDate = rs.getTimestamp("RegistrationDate");
	        }

	        rs.close();
	        getInfo.close();

	        // fetch player's total card count
	        if (player != null) {
	        	 PreparedStatement psCount = conn.prepareStatement(
	 	                "SELECT SUM(CardQuantity) AS total FROM players_cards WHERE PlayerID = ?");
	 	        psCount.setInt(1, playerId);
	 	        ResultSet rsCount = psCount.executeQuery();
	 	       if (rsCount.next()) {
		            int cardCount = rsCount.getInt("total");
		            if (rsCount.wasNull()) // If SUM is NULL, then set to 0
		                player.cardQuantity = 0;
		            else
		            	player.cardQuantity = cardCount;
	 	       }
	       
	 	       rsCount.close();
	 	       psCount.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return player;
	}

	
}
