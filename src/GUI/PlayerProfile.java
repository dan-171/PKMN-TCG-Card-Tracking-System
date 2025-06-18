package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

import Database.AppSession;
import Database.Player;
import Database.Pokedex;
import GUI.TriangleLabel.Direction;


public class PlayerProfile implements ActionListener{
	private JFrame playerProfile;
	private JPanel panelHeader,centerBg,titleBg,playerInfoPanel,
	playerInfoArea,centerPanel,westPanel,northPanel,eastPanel,southPanel;
	private JLabel pageTitle,profileImage,playerName,playerId,numOfcards,regDate;
	private JButton updatePlayerInfo;
	private RoundIconButton BackBtn;
	
	String name ,playerID,registerDate;
	int numOfCards;
	private Player player;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = screenSize.width;
	private int screenHeight = screenSize.height;

	public PlayerProfile(){
		init();			
		headerPanel();
		playerInfo();
		backGround();
		playerProfile.setVisible(true);
		
	}
	public void init() {

		playerProfile = new JFrame();
		playerProfile.setSize(screenSize);
		playerProfile.setResizable(false);
		playerProfile.setTitle("Pokemon TCG Card Tracking System");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		playerProfile.setIconImage(logo.getImage());
		playerProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerProfile.setLocationRelativeTo(null);//default location in center
		playerProfile.setLayout(null);
		
	}
	public void backGround() {
		//create background
		//left area
		TriangleLabel topLeft2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.TOP_LEFT);
		topLeft2.setOpaque(true);
		topLeft2.setBounds(0, 0, screenWidth / 8, screenHeight / 2);
		playerProfile.add(topLeft2);
		
		TriangleLabel topLeft1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.TOP_LEFT);
		topLeft1.setOpaque(true);	
		topLeft1.setBounds(0, 0, screenWidth / 4, screenHeight);
		playerProfile.add(topLeft1);
		
		//right area
		TriangleLabel bottomRight2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight2.setOpaque(true);
		bottomRight2.setBounds(screenWidth - screenWidth / 8, screenHeight / 2, screenWidth / 8, screenHeight / 2);
		playerProfile.add(bottomRight2);
		
		TriangleLabel bottomRight1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight1.setOpaque(true);
		bottomRight1.setBounds(screenWidth - screenWidth / 4, 0, screenWidth / 4, screenHeight);
		playerProfile.add(bottomRight1);
		
		//center area
		centerBg = new JPanel();
		centerBg.setOpaque(true);
		centerBg.setBackground(new Color(0xFFFF00));
		centerBg.setBounds(0, 0, screenWidth, screenHeight);
		playerProfile.add(centerBg);
	}
	public void headerPanel() {
		//create header panel
		panelHeader = new JPanel();
        panelHeader.setBounds(0, 0, screenWidth, screenHeight / 10);
		panelHeader.setOpaque(false);
		panelHeader.setLayout(null);
		
		//rescaled the image icon
		ImageIcon oriBackIcon = new ImageIcon("resources/profileUse/back-button.png");
		Image scaledIcon = oriBackIcon.getImage().getScaledInstance(screenHeight / 18, screenHeight / 18, Image.SCALE_SMOOTH);
		ImageIcon backIcon = new ImageIcon(scaledIcon);
		
		//create back button
		BackBtn = new RoundIconButton(backIcon);
		BackBtn.setBounds(0, 0, screenHeight / 18, screenHeight / 18);
		BackBtn.setFocusable(false);
		BackBtn.setBackground(new Color(255,77,77));
		BackBtn.addActionListener(this);
		panelHeader.add(BackBtn);
		
		playerProfile.add(panelHeader);
	}
	public void playerInfo() {
		int panelX = (int)(screenWidth * 0.17);
	    int panelY = (int)(screenHeight * 0.14);
	    int panelW = (int)(screenWidth * 0.64);
	    int panelH = (int)(screenHeight * 0.74);
	
		//create profile picture
		playerInfoPanel = new JPanel();
		playerInfoPanel.setBounds(panelX, panelY, panelW, panelH);
		playerInfoPanel.setLayout(null);
		playerProfile.add(playerInfoPanel);
		
		int panelPicW = (int)(screenWidth * 0.16);
		int panelPicH = (int)(screenHeight * 0.72);
		
		//rescaled the profile Pic
		ImageIcon oriProfilePic = new ImageIcon("resources/profileUse/mcPic.png");
		Image scaledProfilePic = oriProfilePic.getImage().getScaledInstance(panelPicW, panelPicH, Image.SCALE_SMOOTH);
		ImageIcon profilePic = new ImageIcon(scaledProfilePic);
		
		profileImage = new JLabel();
		profileImage.setIcon(profilePic);
		
		//locate panel for profile picture
		JPanel ImagePanel = new JPanel();
		ImagePanel.setBackground(new Color(0x89CFF0));
		ImagePanel.add(profileImage); //add profile into image panel
		ImagePanel.setBorder(BorderFactory.createLineBorder(new Color(0xB3D9FF), 5));

		ImagePanel.setBounds(0, 0, panelW / 3, panelH);
		playerInfoPanel.add(ImagePanel); //add image panel into center panel
		
		//create a panel for player info
		playerInfoArea = new JPanel();
		playerInfoArea.setLayout(new BorderLayout());
		playerInfoArea.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		playerInfoArea.setBackground(new Color(0xB3D9FF));
		playerInfoArea.setBounds(panelW / 3, 0, panelW * 2 / 3, panelH);
		playerInfoPanel.add(playerInfoArea);
		
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension (50,50));
		westPanel.setBackground(new Color(0xB3D9FF));
		playerInfoArea.add(westPanel,BorderLayout.WEST);

		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension (70,70));
		northPanel.setBackground(new Color(0xB3D9FF));
		
		//create title
		titleBg = new JPanel();
		titleBg.setBackground(new Color(0xB3D9FF));
		
		pageTitle = new JLabel();
		pageTitle.setText("Trainer's Profile");
		pageTitle.setForeground(Color.black);
		pageTitle.setFont(new Font("Times New Roman",Font.BOLD,40));
		titleBg.add(pageTitle);
		
		northPanel.add(titleBg);
		
		playerInfoArea.add(northPanel,BorderLayout.NORTH);
		
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension (50,50));
		eastPanel.setBackground(new Color(0xB3D9FF));
		playerInfoArea.add(eastPanel,BorderLayout.EAST);
		
		//display player info	
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (500,500));
		centerPanel.setBackground(new Color(0xB3D9FF));
		centerPanel.setLayout(new GridLayout(4,1));
		playerInfoArea.add(centerPanel,BorderLayout.CENTER);

		playerName = new JLabel();
		playerName.setText("Name: "+name);
		playerName.setFont(new Font("Times New Roman",Font.BOLD,30));
		playerName.setForeground(Color.black);
		playerName.setVerticalTextPosition(JLabel.TOP);
		playerName.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(playerName);
		
		playerId = new JLabel();
		playerId.setText("Player ID: "+playerID);
		playerId.setFont(new Font("Times New Roman",Font.BOLD,30));
		playerId.setForeground(Color.black);
		playerId.setVerticalTextPosition(JLabel.TOP);
		playerId.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(playerId);

		numOfcards = new JLabel();
		numOfcards.setText("Numbers of cards: "+numOfCards);
		numOfcards.setFont(new Font("Times New Roman",Font.BOLD,30));
		numOfcards.setForeground(Color.black);
		numOfcards.setVerticalTextPosition(JLabel.TOP);
		numOfcards.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(numOfcards);

		regDate = new JLabel();
		regDate.setText("Registered date: "+registerDate);
		regDate.setFont(new Font("Times New Roman",Font.BOLD,30));
		regDate.setForeground(Color.black);
		regDate.setVerticalTextPosition(JLabel.TOP);
		regDate.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(regDate);
		
		//create button of update player info 
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension (100,100));
		southPanel.setBackground(new Color(0xB3D9FF));
		southPanel.setLayout(new FlowLayout());
		playerInfoArea.add(southPanel,BorderLayout.SOUTH);
		
		updatePlayerInfo = new JButton("Update Personal Info");
		updatePlayerInfo.setFont(new Font("Times New Roman",Font.BOLD,30));
		updatePlayerInfo.setFocusable(false);
		updatePlayerInfo.setForeground(Color.black);
		updatePlayerInfo.setBackground(Color.LIGHT_GRAY);
		updatePlayerInfo.setPreferredSize(new Dimension (350,50));
		updatePlayerInfo.setBorder(BorderFactory.createLineBorder(Color.black,3));
		updatePlayerInfo.addActionListener(this);
		southPanel.add(updatePlayerInfo);
	}
	public void updateInfo() {
		//define the option
		Integer currentId = AppSession.getCurrentPlayerId();
	    Player player = null;
	    if (currentId == null) {
	        JOptionPane.showMessageDialog(playerInfoPanel, "No player is currently authenticated.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    } else {
	        player = Player.loadPlayerProfile(currentId);
	        if (player == null) {
	            JOptionPane.showMessageDialog(playerInfoPanel, "Player not found.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }
	    
		String[]updateOption = {"Name","Password","Cancel"};
		
		//create option dialog
		int choice = JOptionPane.showOptionDialog(playerInfoPanel, 
				"Which personal info you want update?", 
				"Personal Info Update", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, 
				null, 
				updateOption,0);
		
		switch(choice) {
			case 0://player name
				
				String newUsername = JOptionPane.showInputDialog(playerInfoPanel, "Enter new username:");
	            if (newUsername == null) { // User pressed cancel
	                break;
	            } else if (newUsername.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(playerInfoPanel, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
	            } else if (newUsername.equals(player.getPlayerName())) {
	                JOptionPane.showMessageDialog(playerInfoPanel, "Username cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                boolean updated = player.resetUsername(currentId, newUsername);
	                if ((updated)) {
	                    JOptionPane.showMessageDialog(playerInfoPanel, "Username updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	                    // Update label to reflect new name
	                    playerName.setText("Name: " + newUsername);
	                    centerPanel.revalidate();
	                    centerPanel.repaint();
	                } else {
	                    JOptionPane.showMessageDialog(playerInfoPanel, "Failed to update username.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	            break;
				
			case 1://password
				 boolean passwordCorrect = false;

		            String storedPassword = player.getPassword();
		            System.out.println("now the password is: " + storedPassword);

		            while (!passwordCorrect) {
		                String originalPassword = JOptionPane.showInputDialog(playerInfoPanel, "Enter your current password:");
		                if (originalPassword == null) { // User pressed cancel
		                    break;
		                } else if (originalPassword.equals(storedPassword)) {
		                    passwordCorrect = true;

		                    while (true) {
		                        String newPassword = JOptionPane.showInputDialog(playerInfoPanel, "Enter new password:");
		                        if (newPassword == null) { // User pressed cancel
		                            break;
		                        } else if (newPassword.trim().isEmpty()) {
		                            JOptionPane.showMessageDialog(playerInfoPanel, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
		                        } else if (newPassword.equals(originalPassword)) {
		                            JOptionPane.showMessageDialog(playerInfoPanel, "New password cannot be the same as the current password.", "Error", JOptionPane.ERROR_MESSAGE);
		                        } else {
		                            // Update password in database
		                            boolean updated = player.resetPassword(currentId, newPassword);
		                            if ((updated)) {
		                                JOptionPane.showMessageDialog(playerInfoPanel, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(playerInfoPanel, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
		                                break;
		                            }
		                        }
		                    }

		                } else {
		                    JOptionPane.showMessageDialog(playerInfoPanel, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            break;
 

		        case 2: //Cancel
		        default:
		            //Close dialog
		            return;
			}
		}
	//load player profile
	public void loadProfile(int pId) {
	    Player player = Player.loadPlayerProfile(pId);

	    if (player != null) {
	        //refresh the GUI components:
	    	playerName.setText("Name: " + player.getPlayerName());
	        playerId.setText("Player ID: " + player.getPlayerID());
	        numOfcards.setText("Numbers of cards: " + player.getCardQuantity());
	        regDate.setText("Registered date: " + player.getRegistrationDate().toString());
	    } else
	        JOptionPane.showMessageDialog(playerProfile, "Player not found.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BackBtn)  {
			System.out.println("Back button clicked!");
			
			
			Integer currentId = AppSession.getCurrentPlayerId();
			player = new Player(currentId);
	      
			Pokedex px = new Pokedex(player); 
			playerProfile.dispose();
            PokedexPage pokedex = new PokedexPage(px);
		}
		else if(e.getSource() == updatePlayerInfo) {
//		if(e.getSource() == BackBtn){
//		      playerProfile.dispose();
//		      PokedexPage pokedexPage = new PokedexPage(new Pokedex(new Player(1)));
//		}
//		else if(e.getSource() == updatePlayerInfo) {
			System.out.println("Update Player button clicked!");
			updateInfo();
		}

	}
	//add here
//	public static void main(String[] args) {
//
//		PlayerProfile profile = new PlayerProfile();
//	    //for example:
//	    profile.loadProfile(1);
//	    profile.playerProfile.setVisible(true);
//	}
}
