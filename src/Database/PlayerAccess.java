package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlayerAccess {
	public boolean validateLogin(String username, String password) {
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

	    try (Connection conn = JDBC.getConnection();
	        Statement stmt = conn.createStatement()) {

	        String insert = "INSERT INTO players (Username, Password) VALUES ('" + username + "','" + password + "')";
	        int row = stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);

	        if (row == 1) {
	            ResultSet rs = stmt.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	            }
	            rs.close();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return generatedId;
	}
}
