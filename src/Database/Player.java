package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Player {
	private int playerID;
	
	public Player() {}
	
	public Player(int playerID)
	{
		this.playerID = playerID;
	}
	
	public int getPlayerID() 
	{
		return playerID;
	}
	
	public boolean validateSignin(String username, String password) {
	    try (Connection conn = JDBC.getConnection();
	        Statement stmt = conn.createStatement()) 
	    {
	        String query = "SELECT * FROM players WHERE Username = '" + username + "' AND Password = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        return rs.next();
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        return false;
	    }
	}

	public int insertUser(String username, String password) {
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
	
	public boolean resetPassword(String username, String newPassword) {
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
	
	//add for testing
	public boolean loadPlayerProfile(int playerId) {
	    boolean found = false;

	    try (Connection conn = JDBC.getConnection()) {
	        // First fetch player info
	        PreparedStatement ps = conn.prepareStatement(
	                "SELECT Username, RegistrationDate FROM players WHERE ID = ?");
	        ps.setInt(1, playerId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            this.playerID = playerId;
	            this.name = rs.getString("Username");

	            this.registerDate = rs.getTimestamp("RegistrationDate");

	            found = true;
	        }

	        rs.close();
	        ps.close();

	        // Then sum up total cards for this player
	        PreparedStatement psCount = conn.prepareStatement(
	                "SELECT SUM(CardQuantity) AS total FROM players_cards WHERE PlayerID = ?");
	        psCount.setInt(1, playerId);
	        ResultSet rsCount = psCount.executeQuery();

	        if (rsCount.next()) {
	            this.cardQuantity = rsCount.getInt("total");

	            if (rsCount.wasNull()) { // If SUM is NULL, then set to 0
	                this.cardQuantity = 0;
	            }
	        }

	        rsCount.close();
	        psCount.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return found;
	}

	private String name;
	private int cardQuantity;
	private Timestamp registerDate;

	// getters for these
	public String getName(){
	    return name;
	}

	public int getCardQuantity(){
	    return cardQuantity;
	}

	public Timestamp getRegistrationDate(){
	    return registerDate;
	}
}
