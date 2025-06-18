package GUI;

import Database.AppSession;
import Database.JDBC;
import Database.Player;
import Database.Pokedex;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CardDisplay extends JPanel {
    private String cardDescription;
    private JButton backButton;
    private Boolean backBoolean = false;
    private PokedexPage pokedexPage;
    private Pokedex pokedex;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    double coefficient = 0.25;
    int panelPicW = (int) (screenWidth * coefficient);
    int panelPicH = (int) (screenHeight * coefficient * 2.5);
    
    Fonts fonts = new Fonts();
    SetUp setUp = new SetUp();

    public CardDisplay(int cardIndex, Pokedex pokedex, JFrame frame) {
    	this.pokedex = pokedex;
    	
        setLayout(new BorderLayout());

        // Create header panel for the back button
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(0xFFF44F));

        // Create back button
        backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.setBackground(new Color(255, 77, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
        	new PokedexPage(this.pokedex);
        });
        
        headerPanel.add(backButton);
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(headerPanel, BorderLayout.NORTH);

        
        // Create center panel
        JPanel centreJPanel = new JPanel();
        centreJPanel.setLayout(new GridLayout(1, 2)); 
        centreJPanel.setBackground(new Color(0xFFF44F)); 

        // Card image
        ImageIcon icon = new ImageIcon(pokedex.fetchCardImg(cardIndex));
        Image scaledImage = icon.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel imageJLabel = new JLabel(icon);
        
        JPanel leftJPanel = new JPanel();
        leftJPanel.add(imageJLabel);
        centreJPanel.add(leftJPanel);
        
        // Card description and quantity control
        JPanel rightPanel = setUp.gridLayout(4, 1);
        JLabel pageTitle = new JLabel("Card's Profile", SwingConstants.CENTER);
        pageTitle.setForeground(Color.black);
        pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        rightPanel.add(pageTitle);
        
        
        
        cardDescription = pokedex.fetchCardDescription(String.format("BS%03d", cardIndex));
        JLabel label1 = new JLabel(cardDescription, SwingConstants.CENTER);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label1.setForeground(Color.black);
        rightPanel.add(label1); 
        
        
        
        // Quantity control
        JPanel quantityPanel = createQuantityControl(String.format("BS%03d", cardIndex));
        rightPanel.add(quantityPanel);
        
        
        
        centreJPanel.add(rightPanel);
        add(centreJPanel, BorderLayout.CENTER);
    }

    public JPanel createQuantityControl(String cardID) {
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // Fetch initial quantity from Pokedex
        int initialQuantity = pokedex.fetchCardCount(cardID);

        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel(String.valueOf(initialQuantity), SwingConstants.CENTER);
        JButton plusButton = new JButton("+");
        JButton confirmButton = new JButton("Confirm");

        minusButton.setFont(new Font("Arial", Font.BOLD, 20));
        plusButton.setFont(new Font("Arial", Font.BOLD, 20));
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        quantityLabel.setPreferredSize(new Dimension(40, 30));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Disable minus button if quantity is zero
        minusButton.setEnabled(initialQuantity > 0);

        // Action listener for minus button
        minusButton.addActionListener(e -> {
            int currentQty = Integer.parseInt(quantityLabel.getText());
            if (currentQty > 0) {
                int newQty = currentQty - 1;
                pokedex.changeCardCount(cardID, -1); // Decrement in DB
                quantityLabel.setText(String.valueOf(newQty));
                if (newQty == 0) {
                    minusButton.setEnabled(false);
                }
            }
        });

        // Action listener for plus button
        plusButton.addActionListener(e -> {
            int currentQty = Integer.parseInt(quantityLabel.getText());
            int newQty = currentQty + 1;
            pokedex.changeCardCount(cardID, 1); // Increment in DB
            quantityLabel.setText(String.valueOf(newQty));
            if (newQty > 0) {
                minusButton.setEnabled(true);
            }
        });
        
        
        
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);
        

        return quantityPanel;
    }	
    
    public boolean getBackBoolean () {
    	return backBoolean;
    }
}
