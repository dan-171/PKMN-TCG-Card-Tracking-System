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

	    String initPlayer = "INSERT INTO players (Username, Password, RegistrationDate) VALUES (?, ?, ?)";
	    String initPlayers_Cards = "INSERT INTO players_cards (PlayerID, CardID, CardQuantity) VALUES (?, ?, ?)";

	    try (Connection conn = JDBC.getConnection()) {
	        PreparedStatement signup = conn.prepareStatement(initPlayer, Statement.RETURN_GENERATED_KEYS);
	        signup.setString(1, username);
	        signup.setString(2, password);
	        // Set registration date to current timestamp
	        signup.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

	        int row = signup.executeUpdate();

	        if (row == 1) {
	        	
	            ResultSet rs = signup.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	                rs.close();

	                PreparedStatement newSet = conn.prepareStatement(initPlayers_Cards);
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
	        Statement stmt = conn.createStatement()) {
	        String query = "SELECT PlayerID FROM players WHERE Username = '" + username + "' AND Password = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
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
	public static boolean resetPassword(String username, String newPassword) {
	    boolean updated = false;

	    try (Connection conn = JDBC.getConnection();
	         Statement stmt = conn.createStatement()) {

	        String update = "UPDATE players SET Password = '" + newPassword + "' WHERE ID = '" + username + "'";
	        int row = stmt.executeUpdate(update);
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

	    try (Connection conn = JDBC.getConnection()) {
	        // fetch player name, registration date
	        PreparedStatement ps = conn.prepareStatement(
	                "SELECT Username, RegistrationDate FROM players WHERE PlayerID = ?");
	        ps.setInt(1, playerId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	player = new Player(playerId);
	            player.playerName = rs.getString("Username");
	            player.registerDate = rs.getTimestamp("RegistrationDate");
	        }

	        rs.close();
	        ps.close();

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
