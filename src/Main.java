import GUI.*;
import Database.*;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		try 
		{
			//Create a connection to the database
			Connection conn = JDBC.getConnection();
			JOptionPane.showMessageDialog(null, "Database successfully connected!");
			loadingPage loadingPage = new loadingPage();			
//			FirstPage firstPage = new FirstPage();
//			boolean login = firstPage.getSuccessLogin();
//			if(login == true) {
//				JOptionPane.showMessageDialog (null, "YEAH!!!!!!!!!");
//				JFrame frame = new JFrame();
//				PokedexPage pokedexPage = new PokedexPage();
//				pokedexPage.setPlayer(firstPage.player);
//			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to connect to database:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

}
