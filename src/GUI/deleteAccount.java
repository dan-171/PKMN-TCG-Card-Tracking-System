package GUI;

import Database.AppSession;
import Database.JDBC;
import Database.Player;
import Database.Pokedex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

public class deleteAccount extends JFrame {
	 private Player player;
	 PlayerProfile playerProfile;

	 public deleteAccount(JFrame parentFrame, int playerId) {
	        super("Delete Account");
			ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
			setIconImage(logo.getImage());
	        setSize(400, 200);
	        setLocationRelativeTo(parentFrame);
	        setLayout(new BorderLayout());
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel("Enter your password to delete the account:", SwingConstants.CENTER);
	        add(label, BorderLayout.NORTH);

	        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
	        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	        inputPanel.add(new JLabel("Player ID:"));
	        JTextField idField = new JTextField(String.valueOf(playerId));
	        idField.setEditable(false);
	        inputPanel.add(idField);

	        inputPanel.add(new JLabel("Password:"));
	        JPasswordField passwordField = new JPasswordField();
	        inputPanel.add(passwordField);
	        add(inputPanel, BorderLayout.CENTER);

	        JPanel buttonPanel = new JPanel();
	        JButton deleteButton = new JButton("Delete");
	        JButton cancelButton = new JButton("Cancel");

	        buttonPanel.add(deleteButton);
	        buttonPanel.add(cancelButton);
	        add(buttonPanel, BorderLayout.SOUTH);

	        deleteButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String password = new String(passwordField.getPassword());
	                if (password.isEmpty()) {
	                    JOptionPane.showMessageDialog(deleteAccount.this, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                boolean success = Player.delPlayer(playerId, password);
	                if (success) {
	                    JOptionPane.showMessageDialog(deleteAccount.this, "Account deleted successfully.");
	                    dispose();
	                    parentFrame.dispose();
	                    new FirstPage();
	                } else {
	                    JOptionPane.showMessageDialog(deleteAccount.this, "Failed to delete account. Check password.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        cancelButton.addActionListener(e -> dispose());
	        setAlwaysOnTop(true);
	        setResizable(false);
	        setVisible(true);
	    }
}
