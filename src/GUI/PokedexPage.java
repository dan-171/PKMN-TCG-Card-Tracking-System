package GUI;

import Database.AppSession;
import Database.Player;
import Database.Pokedex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PokedexPage implements ActionListener {
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
		frame.setTitle("Pokedex");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		showLeftMenu= false;
		showProfileMenu= false;
	}
	
	public void NorthPanel() {
		northPanel = setUp.gridBagLayout();
		int top = 30;
			
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

        // Show profile menu on button click
        leftMenuButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	showLeftMenu = !showLeftMenu; 
            	if (showLeftMenu) {
            		leftpopupMenu.show(leftMenuButton, 0, leftMenuButton.getHeight()); // Show menu
            	} else {
            		leftpopupMenu.setVisible(false); // Hide menu
            	}
            }
        });
        setUp.setGBC(gbc, 0, 0, 1, gbc.LINE_START, gbc.NONE, new Insets(top, 50, 0, 0), 0);
		northPanel.add(leftMenuButton, gbc);
        
        
		//Title
		JLabel Title = new JLabel("Pokedex");
		fonts.HeaderFont(Title);
		setUp.setGBC(gbc, 1, 0, 1, gbc.CENTER, gbc.NONE, new Insets(top, 350, 0, 0), 1.0);
		northPanel.add(Title, gbc);
		


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

        // Show profile menu on button click
        profileMenuButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	showProfileMenu = !showProfileMenu; 
            	if (showProfileMenu) {
            		profileMenu.show(profileMenuButton, 0, profileMenuButton.getHeight()); // Show menu
            	} else {
            		profileMenu.setVisible(false); // Hide menu
            	}
            }
        });
        setUp.setGBC(gbc, 2, 0, 1, gbc.LINE_END, gbc.NONE, new Insets(top, 0, 0, 50), 1.0);
        northPanel.add(profileMenuButton, gbc);
		
		
		
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
        centralPanel.setBackground(Color.LIGHT_GRAY);
        centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        int panelPicW = (int) (screenWidth * 0.07);
        int panelPicH = (int) (screenHeight * 0.17);

        // Loop from BS001 to BS102
        for (int i = 1; i <= 102; i++) {
            ImageIcon icon = new ImageIcon(pokedex.fetchCardImg(i));
            Image scaledImage = icon.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            cardButton.add(new JButton(pokedex.fetchCardLabel(String.format("BS%03d", i)), icon));
            cardButton.get(i-1).setVerticalTextPosition(SwingConstants.BOTTOM);
            cardButton.get(i-1).setHorizontalTextPosition(SwingConstants.CENTER);
            cardButton.get(i-1).setPreferredSize(new Dimension(panelPicW + 20, panelPicH + 40));

            centralPanel.add(cardButton.get(i-1));
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
