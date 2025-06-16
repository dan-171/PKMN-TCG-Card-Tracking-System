package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String connectionURL = "jdbc:mysql://localhost:3306/";
	private static final String dbName = "pkmn-tcg-card-tracking-system";
	private static final String username = "root";
	private static final String password = "";
	
	public static Connection getConnection() throws Exception{
		Class.forName(driver);
		return DriverManager.getConnection(connectionURL+dbName, username, password);
		
	}

}
