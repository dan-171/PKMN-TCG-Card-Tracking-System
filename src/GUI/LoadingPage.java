package GUI;
import javax.swing.*;
import java.awt.*;

public class LoadingPage implements Page{
	private JFrame frame;
	private JProgressBar progressBar;

	public LoadingPage(){
		NorthPanel();
		CentralPanel();
		init();
	}

	public void init() {
		int counter = 0;

		while(counter<=100) {

			progressBar.setValue(counter);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (counter == 0)
				counter += 76;
			else
				counter += 24;
		}
		frame.dispose();
		FirstPage firstPage = new FirstPage();	
	}

	public void NorthPanel() {
		frame = new JFrame("Pokemon TCG Card Tracking System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	public void CentralPanel() {


		ImageIcon backgroundIcon = new ImageIcon("resources/LOGO/PokemonWallpaper1024.jpg");
		JLabel background = new JLabel(backgroundIcon);
		background.setLayout(new BorderLayout());
		frame.setContentPane(background);

		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(0xFF9900));
		progressBar.setFont(new Font("Agency FB",Font.PLAIN,25));
		progressBar.setBackground(Color.WHITE);
		progressBar.setPreferredSize(new Dimension(400, 30));
		background.add(progressBar,BorderLayout.SOUTH);

		frame.setVisible(true); 
	}

}