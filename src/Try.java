import javax.swing.*;
import java.awt.*;

public class Try {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Try().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // 1. Title panel at the top
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("My Application Login");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // 2. Main container panel with BORDERLAYOUT
        JPanel mainContainer = new JPanel(new BorderLayout());

        // Empty side panels to create margins
        JPanel leftMargin = new JPanel();
        leftMargin.setPreferredSize(new Dimension(150, 0)); // width 150px empty
        JPanel rightMargin = new JPanel();
        rightMargin.setPreferredSize(new Dimension(150, 0)); // width 150px empty

        mainContainer.add(leftMargin, BorderLayout.WEST);
        mainContainer.add(rightMargin, BorderLayout.EAST);

        // 3. Content split panel in center of main container
        JPanel contentSplit = new JPanel(new GridLayout(1, 2, 40, 0)); // 40px horizontal gap

        // Left panel: Sign Up (simple example)
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BorderLayout()); // You can customize
        signUpPanel.setBorder(BorderFactory.createTitledBorder("Sign Up"));
        signUpPanel.add(new JButton("Register"), BorderLayout.CENTER);
        
        // Right panel: 4 rows panel
        JPanel rightPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        for (int i = 1; i <= 4; i++) {
            rightPanel.add(new JLabel("Row " + i));
        }
        rightPanel.setBorder(BorderFactory.createTitledBorder("Details"));

        // Add left and right panel to content split
        contentSplit.add(signUpPanel);
        contentSplit.add(rightPanel);

        mainContainer.add(contentSplit, BorderLayout.CENTER);

        frame.add(mainContainer, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}
