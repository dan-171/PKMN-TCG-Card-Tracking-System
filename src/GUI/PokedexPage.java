package GUI;

import Database.AppSession;
import Database.Player;
import Database.Pokedex;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PokedexPage implements ActionListener{
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel;
	private int screenWidth, screenHeight;
	private JButton leftMenuButton, profileMenuButton;
    private JPopupMenu leftpopupMenu, profileMenu;
    private JMenuItem[]  leftJMenuItems,profileMenuItems;
    
    private Player player;
    private Pokedex pokedex;
    private ArrayList<JButton> cardButton;
    
    
    private boolean showLeftMenu, showProfileMenu;
    private boolean ignoreProfileMenuHide = false;
    private boolean ignoreLeftMenuHide = false;
    
	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public static final int Margin = 300;
	
	//Constructor
		public PokedexPage(Pokedex pokedex){
			this.pokedex  = pokedex;
			this.cardButton = new ArrayList<>();
			init();
			NorthPanel();
			displayPanel();
			//CentralPanel();
			//WestPanel();
			//EastPanel();*/
			frame.setVisible(true);
			
		}
	
	public void init(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		
		frame = new JFrame();
		frame.setSize(screenSize);
		frame.setResizable(false);
		frame.setTitle("Pokemon TCG Card Tracking System");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		showLeftMenu= false;
		showProfileMenu= false;
	}
	
	public void NorthPanel() {
		int top = 50;
		int bottom = 0;
		//North Panel
		northPanel = setUp.gridBagLayout();
		northPanel.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
		northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		northPanel.setBackground(new Color(0xe70023));
			
		// Create left menu
		leftMenuButton = new JButton("Left Menu"); 
		
        fonts.Heading2(leftMenuButton);
		leftpopupMenu = new JPopupMenu();
        String[] leftpopupMenuLabel = {"Test 1", "Test 2", "Test 3"}; 
        leftJMenuItems = new JMenuItem[leftpopupMenuLabel.length]; 
        
        for (int i = 0; i < leftpopupMenuLabel.length; i++) {
        	leftJMenuItems[i] = new JMenuItem(leftpopupMenuLabel[i]);
            fonts.Heading2(leftJMenuItems[i]);
            leftJMenuItems[i].addActionListener(this);
            leftpopupMenu.add(leftJMenuItems[i]);
        }

     // Left Menu Button
        leftMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (leftpopupMenu.isShowing()) {
                    ignoreLeftMenuHide = true;
                    leftpopupMenu.setVisible(false);
                } else {
                    leftpopupMenu.show(leftMenuButton, 0, leftMenuButton.getHeight());
                }
            }
        });
        
        setUp.setGBC(gbc, 0, 0, 1, gbc.LINE_START, gbc.NONE, new Insets(top, 20, bottom, 0), 0);
        leftMenuButton.setBackground(Color.WHITE);
        leftMenuButton.setForeground(new Color(0x333333));
        leftMenuButton.setFocusable(false);
		northPanel.add(leftMenuButton, gbc);
        
		//Title
		//rescaled the profile Pic
		ImageIcon oriBgPic = new ImageIcon("resources/LOGO/pokedexTitle.png");
		Image scaledBgPic = oriBgPic.getImage().getScaledInstance(screenWidth/3, screenHeight/10, Image.SCALE_SMOOTH);
		ImageIcon bgPic = new ImageIcon(scaledBgPic);

		JLabel bgImage = new JLabel(bgPic);
		bgImage.setLayout(null);


		setUp.setGBC(gbc, 1, 0, 1, gbc.CENTER, gbc.NONE, new Insets(0, screenWidth/9 +30, 0, 0), 1.0);
		northPanel.add(bgImage, gbc);

        
		
		// Create profile menu
		profileMenuButton = new JButton("Profile Menu"); 
        fonts.Heading2(profileMenuButton);
        profileMenu = new JPopupMenu();
        String[] menuLabels = {"Profile", "Logout"}; // Menu item labels
        profileMenuItems = new JMenuItem[menuLabels.length]; 
        
        for (int i = 0; i < menuLabels.length; i++) {
        	profileMenuItems[i] = new JMenuItem(menuLabels[i]);
            fonts.Heading2(profileMenuItems[i]);
            profileMenuItems[i].addActionListener(this);
            profileMenu.add(profileMenuItems[i]);
        }
     // Profile Menu Button
        profileMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (profileMenu.isShowing()) {
                    profileMenu.setVisible(false);
                } else {
                    profileMenu.show(profileMenuButton, 0, profileMenuButton.getHeight());
                }
            }
        });

     
        setUp.setGBC(gbc, 2, 0, 1, gbc.LINE_END, gbc.NONE, new Insets(top, 0, bottom, 20), 1.0);
        profileMenuButton.setBackground(Color.WHITE);
        profileMenuButton.setForeground(new Color(0x333333));
        profileMenuButton.setFocusable(false);

        northPanel.add(profileMenuButton, gbc);
		
        
        
		northPanel.setPreferredSize(new Dimension (screenWidth,screenHeight/7));
		
		frame.add(northPanel, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
	    switch (command) {
	        case "Profile":
	        	frame.dispose();
	        	PlayerProfile playerProfile = new PlayerProfile();	           
	        	//Clear the 
	        	Integer currentId = AppSession.getCurrentPlayerId();

	            if (currentId == null) {
	                JOptionPane.showMessageDialog(null, "No player is currently authenticated.");
	                return;
	            }

	            playerProfile.init();
	            playerProfile.loadProfile(currentId);
	        	
	            break;
	        case "Logout":
	        	//Username sets to null etc
	        	
	            break;
	        default:
	            System.out.println("Unknown action: " + command);
	            break;
	    }
	}
	
	
    public void displayPanel() {
        int panelWidth = (int) (screenWidth * 0.9);
        int panelHeight = (int) (screenHeight * 0.85);

        centralPanel = new JPanel();
        centralPanel.setBackground(Color.white);
        centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        int panelPicW = (int) (screenWidth * 0.07);
        int panelPicH = (int) (screenHeight * 0.17);

        // Loop from BS001 to BS102
        for (int i = 1; i <= 102; i++) {
            final int cardIndex = i; // Create a final variable to hold the current index
            ImageIcon icon = new ImageIcon(pokedex.fetchCardImg(cardIndex));
            Image scaledImage = icon.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            JButton cardButton = new JButton(pokedex.fetchCardLabel(String.format("BS%03d", cardIndex)), icon);
            cardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
            cardButton.setPreferredSize(new Dimension(panelPicW + 20, panelPicH + 40));
            
         // Add action listener to the cardButton
            cardButton.addActionListener(e -> {
            	
            	System.out.println("Card button clicked: BS" + String.format("%03d", cardIndex));
            	
            
            
            });
            
            
            
            centralPanel.add(cardButton);
        }

        
    
        
        // Set preferred size larger than the visible area to trigger scroll
        centralPanel.setPreferredSize(new Dimension(panelWidth, panelHeight * 3));

        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(centralPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        frame.add(scrollPane);
        //frame.add(centralPanel);
    }
	
	
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}
