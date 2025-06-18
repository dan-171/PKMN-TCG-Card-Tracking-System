package GUI;

import Database.Player;
import Database.Pokedex;

public class Main {
	public static void main(String [] args) {


//		new loadingPage();
//		new FirstPage();
//		new PlayerProfile();
//		new PokedexPage();
		new PokedexPage(new Pokedex(new Player(1)));



	}
}
