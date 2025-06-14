package GUI;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		BasicJDBC db = new BasicJDBC();

		try 
		{
			//Create a connection to the database
			Connection conn = db.getConnection();
			JOptionPane.showMessageDialog(null, "Database successfully connected!");
			//Create a Statement object
			Statement stmt = conn.createStatement();

			new FirstPage();
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Failed to connect to database:\n" + 
		e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
