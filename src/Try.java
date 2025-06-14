import javax.swing.*;
import java.awt.*;

public class Try {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // First column: Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // spans 1 column
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel("Name:"), gbc);

        // Second and third column: TextField
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // spans 2 columns
        panel.add(new JTextField(20), gbc);

        frame.add(panel);
        frame.setVisible(true);
        
        
        
        

    }
}
