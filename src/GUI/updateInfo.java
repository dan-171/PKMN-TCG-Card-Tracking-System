package GUI;

import javax.swing.*;
import java.awt.*;

import Database.Player;

public class updateInfo extends JFrame {
    private final Player player;
    private final int playerId;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PlayerProfile profileRef;

    public updateInfo(JFrame parentFrame, PlayerProfile profileRef, int playerId) {
        super("Update Account Info");
        this.profileRef = profileRef;
        this.playerId = playerId;
        this.player = Player.loadPlayerProfile(playerId);

        if (this.player == null) {
            JOptionPane.showMessageDialog(parentFrame, "Player not found.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        setSize(400, 250);
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
        setIconImage(logo.getImage());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createMainMenu(), "MENU");
        cardPanel.add(createUsernamePanel(), "USERNAME");
        cardPanel.add(createPasswordPanel(), "PASSWORD");

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "MENU");
        pack();
        setVisible(true);
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel label = new JLabel("Which personal info do you want to update?", SwingConstants.CENTER);
        panel.add(label);

        JButton updateNameBtn = new JButton("Name");
        updateNameBtn.setFocusable(false);
        JButton updatePasswordBtn = new JButton("Password");
        updatePasswordBtn.setFocusable(false);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusable(false);

        updateNameBtn.addActionListener(e -> cardLayout.show(cardPanel, "USERNAME"));
        updatePasswordBtn.addActionListener(e -> cardLayout.show(cardPanel, "PASSWORD"));
        cancelBtn.addActionListener(e -> dispose());

        panel.add(updateNameBtn);
        panel.add(updatePasswordBtn);
        panel.add(cancelBtn);

        return panel;
    }

    private JPanel createUsernamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel label = new JLabel("Enter new username:");
        JTextField usernameField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(label);
        inputPanel.add(usernameField);
        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        saveBtn.setFocusable(false);
        JButton backBtn = new JButton("Back");
        backBtn.setFocusable(false);

        saveBtn.addActionListener(e -> {
            String newUsername = usernameField.getText().trim();
            if (newUsername.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (newUsername.equals(player.getPlayerName())) {
                JOptionPane.showMessageDialog(this, "New username is the same as current.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean updated = player.resetUsername(playerId, newUsername);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Username updated successfully!");
                    profileRef.loadProfile();  // reload profile data
                    // Update session's playerName as well
                    player.setPlayerName(newUsername);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update username.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "MENU"));

        buttonPanel.add(backBtn);
        buttonPanel.add(saveBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPasswordField currentPwdField = new JPasswordField();
        JPasswordField newPwdField = new JPasswordField();

        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.add(new JLabel("Enter current password:"));
        inputPanel.add(currentPwdField);
        inputPanel.add(new JLabel("Enter new password:"));
        inputPanel.add(newPwdField);

        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        saveBtn.addActionListener(e -> {
            String currentPwd = new String(currentPwdField.getPassword());
            String newPwd = new String(newPwdField.getPassword());

            if (!currentPwd.equals(player.getPassword())) {
                JOptionPane.showMessageDialog(this, "Incorrect current password.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (newPwd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "New password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (newPwd.equals(currentPwd)) {
                JOptionPane.showMessageDialog(this, "New password is the same as current.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean updated = player.resetPassword(playerId, newPwd);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                    // Update session password
                    player.setPassword(newPwd);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "MENU"));

        buttonPanel.add(backBtn);
        buttonPanel.add(saveBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}
