package GUI;

import Database.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokedexPage implements ActionListener {
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel;
	private int screenWidth, screenHeight;
	private JButton leftMenuButton, profileMenuButton;
    private JPopupMenu leftpopupMenu, profileMenu;
    private JMenuItem[]  leftJMenuItems,profileMenuItems;
    
    
    private boolean showLeftMenu, showProfileMenu;
    
	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public static final int breadth = 1920, length = 1080, Margin = 300;
	
	//Constructor
		public PokedexPage(){
			init();
			NorthPanel();
			/*CentralPanel();
			WestPanel();
			EastPanel();*/
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
	            
	            break;
	        case "Logout":
	            System.out.println("Logging out");
	            break;
	        default:
	            System.out.println("Unknown action: " + command);
	            break;
	    }
	}
	
}
