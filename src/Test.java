import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        // Set up the frame
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null); // Center on screen

        // Create a panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ===== Label =====
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;                // column 0
        gbc.gridy = 0;                // row 0
        gbc.insets = new Insets(10, 10, 10, 10); // padding
        gbc.anchor = GridBagConstraints.LINE_END; // right-align
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;              // no space taken
        formPanel.add(usernameLabel, gbc);

        // ===== TextField =====
        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;                // column 1
        gbc.gridy = 0;                // same row
        gbc.anchor = GridBagConstraints.LINE_START; // left-align
        gbc.fill = GridBagConstraints.HORIZONTAL;   // stretch if needed
        gbc.weightx = 1.0;            // take extra horizontal space
        formPanel.add(usernameField, gbc);

        // Add form panel to the frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
