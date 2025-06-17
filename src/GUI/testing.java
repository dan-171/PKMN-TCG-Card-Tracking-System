package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class testing extends JFrame implements ActionListener {

    private JPanel displayPanel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    testing() {
        this.setSize(screenSize);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout()); // Use layout manager

        displayPanel(); // Build card panel
        this.setVisible(true);
    }

    public void displayPanel() {
        int panelWidth = (int) (screenWidth * 0.9);
        int panelHeight = (int) (screenHeight * 0.85);

        displayPanel = new JPanel();
        displayPanel.setBackground(Color.LIGHT_GRAY);
        displayPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        int panelPicW = (int) (screenWidth * 0.07);
        int panelPicH = (int) (screenHeight * 0.17);

        // Loop from BS001 to BS102
        for (int i = 1; i <= 102; i++) {
            String cardCode = String.format("BS%03d", i);
            String imagePath = "resources/images/" + cardCode + ".jpg";

            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            JButton cardButton = new JButton(cardCode, icon);
            cardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
            cardButton.setPreferredSize(new Dimension(panelPicW + 20, panelPicH + 40));

            displayPanel.add(cardButton);
        }
        
        // Set preferred size larger than the visible area to trigger scroll
        displayPanel.setPreferredSize(new Dimension(panelWidth, panelHeight * 3));

        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(displayPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        this.add(scrollPane);
    }

    public static void main(String[] args) {
        new testing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
//package GUI;
//import Database.Pokedex;
//
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
//
//import java.util.List;
//import java.util.ArrayList;
//
//public class testing extends JFrame implements ActionListener {
//
//    private JPanel displayPanel;
//    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    private ArrayList<Card> allCards = new ArrayList<>();
//
//    private int screenWidth = screenSize.width;
//    private int screenHeight = screenSize.height;
//    List<JButton> cardButtonList;
//
//    private Pokedex pokedex;
//    private ArrayList<JButton> cardButton;
//    
//    testing() {
//        this.setSize(screenSize);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLayout(new BorderLayout()); // Use layout manager
//        cardButtonList = new ArrayList<>();
//        
//        displayPanel(); // Build card panel
//        this.setVisible(true);
//        
//    }
//
//    public void displayPanel() {
//        int panelWidth = (int) (screenWidth * 0.9);
//        int panelHeight = (int) (screenHeight * 0.85);
//
//        displayPanel = new JPanel();
//        displayPanel.setBackground(Color.LIGHT_GRAY);
//        displayPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
//
//        int panelPicW = (int) (screenWidth * 0.07);
//        int panelPicH = (int) (screenHeight * 0.17);
//
//        // Loop from BS001 to BS102
//        for (int i = 1; i <= 102; i++) {
//            String cardCode = String.format("BS%03d", i);
//            ImageIcon icon = new ImageIcon(pokedex.fetchCardImg(i));
//            Image scaledImage = icon.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
//            icon = new ImageIcon(scaledImage);
//
//
//            cardButton.add(new JButton(String.format("BS%03d", i), icon));
//            cardButton.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
//            cardButton.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
//            cardButton.get(i).setPreferredSize(new Dimension(panelPicW + 20, panelPicH + 40));
//
//            displayPanel.add(cardButton.get(i));
//        }
//        
//        // Set preferred size larger than the visible area to trigger scroll
//        displayPanel.setPreferredSize(new Dimension(panelWidth, panelHeight * 3));
//
//        // Wrap in scroll pane
//        JScrollPane scrollPane = new JScrollPane(displayPanel,
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
//        this.add(scrollPane);
//    }
//
//    public static void main(String[] args) {
//        new testing();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}

