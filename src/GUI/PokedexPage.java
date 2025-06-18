package GUI;

import Database.AppSession;
import Database.JDBC;
import Database.Player;
import Database.Pokedex;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PokedexPage implements ActionListener{
	private String selectedTypeFilter = "";
	private String selectedStageFilter = "";
	private String selectedAcquiredFilter = "All";
	private String currentSearchKeyword = "";

	
	private JFrame frame;
	private JPanel northPanel, centralPanel,searchPanel, filterPanel;
	private int screenWidth, screenHeight;
	private JButton profileMenuButton,searchButton;
	private JPopupMenu profileMenu;
	private JMenuItem[]  profileMenuItems;
	JTextField searchField;
	
	
	private Integer currentId = AppSession.getCurrentPlayerId();
	private Player player = new Player(currentId);
	
	private Pokedex pokedex;
	private ArrayList<JButton> cardButton;



	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	CardDisplay cardDisplay;
	GridBagConstraints gbc = new GridBagConstraints();


	public static final int Margin = 300;

	//Constructor
	public PokedexPage(Pokedex pokedex){
		this.pokedex  = pokedex;
		this.cardButton = new ArrayList<>();
		init();
		NorthPanel();
		displayPanel();
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

	}

	public void NorthPanel() {
		int top = 50;
		int bottom = 0;
		
		//North Panel
		northPanel = setUp.gridBagLayout();
		northPanel.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
		northPanel.setBackground(new Color(0xD94446));
		
		//create search function
		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		searchField = new JTextField(10);
		searchButton = new JButton("Search");
		JLabel searchText = new JLabel ("Search:");
		searchText.setForeground(Color.WHITE);
		
		searchPanel.add(searchText);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.setBackground(new Color(0xD94446));
		searchButton.setActionCommand("Search");
		searchButton.addActionListener(this);
		setUp.setGBC(gbc, 0, 1, 1, gbc.LINE_START, gbc.NONE, new Insets(top, 30, bottom, 0), 0);
		northPanel.add(searchPanel,gbc);

		//filter panel
		filterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); 
		filterPanel.setBackground(new Color(0xD94446));
		
		//filter type
		JLabel filterText1 = new JLabel ("Type:");
		filterText1.setForeground(Color.WHITE);
		filterPanel.add(filterText1);
		
		String[] type = {"Any","Colorless","Fire","Water","Lightning","Grass","Fighting","Psychic"};
		JComboBox<String> typeBox = new JComboBox<>(type);
		typeBox.setActionCommand("FilterType");
		typeBox.addActionListener(this);
		filterPanel.add(typeBox);
		
		// filter stage
		JLabel filterText2 = new JLabel("Stage:");
		filterText2.setForeground(Color.WHITE);
		filterPanel.add(filterText2);

		String[] stage = {"All", "Basic", "Stage 1", "Stage 2"};
		JComboBox<String> stageBox = new JComboBox<>(stage);
		stageBox.setActionCommand("FilterStage");
		stageBox.addActionListener(this);
		filterPanel.add(stageBox);

		// filter acquired
		JLabel filterText3 = new JLabel("Acquired:");
		filterText3.setForeground(Color.WHITE);
		filterPanel.add(filterText3);

		String[] acquired = {"All", "Acquired", "Unacquired"};
		JComboBox<String> acquiredBox = new JComboBox<>(acquired);
		acquiredBox.setActionCommand("FilterAcquired");
		acquiredBox.addActionListener(this);
		filterPanel.add(acquiredBox);


		setUp.setGBC(gbc, 1, 1, 1, gbc.LINE_START, gbc.NONE, new Insets(top, 30, bottom, 0), 0);
		northPanel.add(filterPanel,gbc);


		//Title
		//rescaled the profile Pic
		ImageIcon oriBgPic = new ImageIcon("resources/LOGO/pokedexTitle.png");
		Image scaledBgPic = oriBgPic.getImage().getScaledInstance(screenWidth/3, screenHeight/10, Image.SCALE_SMOOTH);
		ImageIcon bgPic = new ImageIcon(scaledBgPic);

		JLabel bgImage = new JLabel(bgPic);
		bgImage.setLayout(null);


		setUp.setGBC(gbc, 0, 0, 2, gbc.CENTER, gbc.NONE, new Insets(0, screenWidth/4+ 30, 0, 0), 1.0);
		northPanel.add(bgImage, gbc);

		// Create profile menu
		profileMenuButton = new JButton("≡"); 
		
		
		fonts.BodyFont(profileMenuButton);
		profileMenu = new JPopupMenu();
		String[] menuLabels = {"Profile", "Logout"}; // Menu item labels
		profileMenuItems = new JMenuItem[menuLabels.length]; 
		

		for (int i = 0; i < menuLabels.length; i++) {
		    profileMenuItems[i] = new JMenuItem(menuLabels[i]);
		    fonts.BodyFont(profileMenuItems[i]);
		    profileMenuItems[i].setBackground(new Color(0xD94446));
		    profileMenuItems[i].setForeground(Color.WHITE);
		    profileMenuItems[i].addActionListener(this);
		    profileMenu.add(profileMenuItems[i]);
		}

		// Profile Menu Button
		profileMenuButton.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if (profileMenu.isShowing()) {
		            profileMenu.setVisible(false);
		        } else {
		            // Show the popup menu below the button
		        	int x = profileMenuButton.getWidth() - profileMenu.getPreferredSize().width;
		        	int y = profileMenuButton.getHeight();
		        	profileMenu.show(profileMenuButton, x, y);
		        }
		    }
		});

		setUp.setGBC(gbc, 2, 1, 1, gbc.LINE_END, gbc.NONE, new Insets(top, 0, bottom, 20), 1.0);
		profileMenuButton.setBackground(Color.WHITE);
		profileMenuButton.setForeground(new Color(0x333333));
		profileMenuButton.setFocusable(false);

		northPanel.add(profileMenuButton, gbc);

		frame.add(northPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event) {
		int panelPicW = (int) (screenWidth * 0.08);
		int panelPicH = (int) (screenHeight * 0.2);
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

			//playerProfile.init();
			playerProfile.loadProfile(currentId);

			break;
		case "Logout":
			// Clear the session
			AppSession.clearSession();

			// Show logout confirmation
			JOptionPane.showMessageDialog(null, "You have been logged out successfully.");

			// Dispose current frame
			frame.dispose();
			
			FirstPage FP = new FirstPage();
			break;

		case "Search":
			currentSearchKeyword = searchField.getText().trim().toLowerCase();
			applyAllFilters(panelPicW, panelPicH);
			break;

		case "FilterType":
			selectedTypeFilter = ((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
			String typeFilter = selectedTypeFilter.equalsIgnoreCase("Any") ? "" : selectedTypeFilter;
			applyAllFilters(panelPicW, panelPicH);
			break;

		case "FilterStage":
			selectedStageFilter = ((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
			applyAllFilters(panelPicW, panelPicH);
			break;

		case "FilterAcquired":
			selectedAcquiredFilter = ((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
			applyAllFilters(panelPicW, panelPicH);
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

		int panelPicW = (int) (screenWidth * 0.08);
		int panelPicH = (int) (screenHeight * 0.2);

		// Loop from BS001 to BS102
		for (int i = 1; i <= 102; i++) {
			final int cardIndex = i; // Create a final variable to hold the current index
			generateCardButton(String.format("BS%03d", cardIndex), panelPicW, panelPicH);
		}

		// Set preferred size larger than the visible area to trigger scroll
		centralPanel.setPreferredSize(new Dimension(panelWidth, panelHeight * 4));

		// Wrap in scroll pane
		JScrollPane scrollPane = new JScrollPane(centralPanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(50);
		frame.add(scrollPane);
	}

	private void applyAllFilters(int panelPicW, int panelPicH) {
	    // Normalize filters
	    String nameFilter = currentSearchKeyword.isEmpty() ? null : currentSearchKeyword;
	    String typeFilter = selectedTypeFilter.equalsIgnoreCase("Any") ? "" : selectedTypeFilter;
	    String stageFilter = selectedStageFilter.equalsIgnoreCase("Any") ? "" : selectedStageFilter;

	    // Filter by name + type + stage
	    ArrayList<String> filteredCards = pokedex.filterCards(nameFilter, typeFilter, stageFilter);

	    // Apply acquisition filter (Acquired/Unacquired/All)
	    ArrayList<String> finalFilteredCards = new ArrayList<>();
	    for (String cardId : filteredCards) {
	        boolean isMissing = pokedex.missingCard(cardId);

	        if (selectedAcquiredFilter.equalsIgnoreCase("All")) {
	            finalFilteredCards.add(cardId);
	        } else if (selectedAcquiredFilter.equalsIgnoreCase("Acquired") && !isMissing) {
	            finalFilteredCards.add(cardId);
	        } else if (selectedAcquiredFilter.equalsIgnoreCase("Unacquired") && isMissing) {
	            finalFilteredCards.add(cardId);
	        }
	    }

	    // Step 3: Display cards
	    centralPanel.removeAll();
	    for (String cardId : finalFilteredCards) {
	        generateCardButton(cardId, panelPicW, panelPicH);
	    }
	    centralPanel.revalidate();
	    centralPanel.repaint();
	}

	
	public void generateCardButton(String cardID, int w, int h){
		ImageIcon icon = new ImageIcon(pokedex.fetchCardImg(cardID));
        Image scaledImage = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        
        JButton cardButton = new JButton(pokedex.fetchCardLabel(cardID), icon);
        cardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cardButton.setFont(new Font("Roboto", Font.BOLD, 14));
        cardButton.setPreferredSize(new Dimension(w + 20, h + 40));

        // Add action listener if needed (e.g. show card details)
        cardButton.addActionListener(e -> {
        	cardDisplay = new CardDisplay(Integer.parseInt(cardID.substring(2)), pokedex, frame);
			centralPanel.removeAll();
			centralPanel.add(cardDisplay);
			centralPanel.revalidate();
			centralPanel.repaint();
	});
	centralPanel.add(cardButton);
	centralPanel.revalidate();
	centralPanel.repaint();
	}
}
