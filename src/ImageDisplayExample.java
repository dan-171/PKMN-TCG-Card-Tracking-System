import javax.swing.*;
import java.awt.*;

public class ImageDisplayExample {
	
		public ImageDisplayExample(){
			// Create frame
	        JFrame frame = new JFrame("Card Viewer");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 400);

	        // Load image from resources/images
	        ImageIcon icon = new ImageIcon(ImageDisplayExample.class.getResource("/images/BS1_Alakazam.jpg"));

	        // Create label with the image
	        JLabel label = new JLabel(icon);
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        label.setVerticalAlignment(SwingConstants.CENTER);

	        // Add label to frame
	        frame.add(label);
	        frame.setVisible(true);	
		}
}
