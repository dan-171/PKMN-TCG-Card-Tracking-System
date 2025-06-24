import GUI.*;
import Database.*;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main{
	public static void main(String[] args) throws DBConnectionFailException {
		try {
			//Create a connection to the database
			Connection conn = JDBC.getConnection();
			PokedexPage pokedexPage = new PokedexPage(new Pokedex(new Player(1)));
//			LoadingPage loadingPage = new LoadingPage();			
			
		}
		catch (Exception e) {
			throw new DBConnectionFailException("Fail to connect to database or access loading page!");
		}
	}
}