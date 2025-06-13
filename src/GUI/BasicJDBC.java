package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicJDBC {
	String driver;
	String dbName;
	String connectionURL;
	String username;
	String password;
	Connection conn;
	
	public BasicJDBC()
	{
		driver = "com.mysql.jdbc.Driver";
		connectionURL = "jdbc:mysql://localhost:3306/";
		dbName = "pokemon";
		username = "root";
		password = "";
	}
	
	public Connection getConnection() throws Exception
	{
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(connectionURL+dbName, username, password);
		return connection;
	}
	
	public boolean validateLogin(String username, String password) {
	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement()) {

	        String query = "SELECT * FROM persons WHERE Username = '" + username + "' AND Password = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public int insertUser(String username, String password) {
	    int generatedId = -1;

	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement()) {

	        String insert = "INSERT INTO persons (Username, Password) VALUES ('" + username + "','" + password + "')";
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
