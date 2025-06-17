package GUI;

import Database.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokedexPage {
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel;
	
	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public static final int breadth = 1920, length = 1080, Margin = 300;
	
	//Constructor
		public PokedexPage(){
			init();
			NorthPanel();
			/*CentralPanel();
			WestPanel();
			EastPanel();*/
			frame.setVisible(true);
			
		}
	
	public void init(){
		frame = new JFrame();
		frame.setSize(breadth,length);
		frame.setResizable(false);
		frame.setTitle("Pokedex");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
	}
	
	public void NorthPanel() {
		northPanel = setUp.gridBagLayout();
		JLabel Title = new JLabel("Welcome to the Pokemon TCG Card Tracking System");
		fonts.HeaderFont(Title);
		
		
		
		setUp.setGBC(gbc, 0, 1, 1, gbc.CENTER, gbc.HORIZONTAL, new Insets(30, 0, 0, 0), 0);
		northPanel.add(Title, gbc);
		
		frame.add(northPanel, BorderLayout.NORTH);
		
	}
}
