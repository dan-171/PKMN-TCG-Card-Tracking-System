import GUI.*;
import Database.*;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		try 
		{
			//Create a connection to the database
			Connection conn = JDBC.getConnection();
			JOptionPane.showMessageDialog(null, "Database successfully connected!");

			//new ImageDisplayExample();
			new FirstPage();
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Failed to connect to database:\n" + 
		e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
