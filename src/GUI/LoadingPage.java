package GUI;
import javax.swing.*;
import javax.swing.Box.Filler;

import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
	
public class LoadingPage {
		JFrame frame;
		JProgressBar progressBar;
		
	public LoadingPage(){
		
		 frame = new JFrame("Pokemon TCG Card Tracking System");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(1024, 768);
	     frame.setLocationRelativeTo(null);
	     frame.setResizable(false);
	     
	     ImageIcon backgroundIcon = new ImageIcon("resources/LOGO/PokemonWallpaper1024.jpg");
	     JLabel background = new JLabel(backgroundIcon);
	     background.setLayout(new BorderLayout());
	     frame.setContentPane(background);

	     progressBar = new JProgressBar();
	     progressBar.setValue(0);
	     progressBar.setStringPainted(true);
	     progressBar.setForeground(new Color(0xFF9900));
	     progressBar.setFont(new Font("MV Boli",Font.PLAIN,25));
	     progressBar.setBackground(Color.WHITE);
	     progressBar.setPreferredSize(new Dimension(400, 30));
	     background.add(progressBar,BorderLayout.SOUTH);
	    
	     frame.setVisible(true); 
	     fill();
	}
	public void fill() {
		int counter = 0;
		
		while(counter<=100) {
			
			progressBar.setValue(counter);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter += 10;
		}
		frame.dispose();
		FirstPage firstPage = new FirstPage();	
	}
}