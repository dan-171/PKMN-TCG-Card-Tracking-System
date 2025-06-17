package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;


import Database.Player;

import GUI.TriangleLabel.Direction;


public class PlayerProfile implements ActionListener{
	private JFrame userProfile;
	private JPanel panelHeader,centerBg,titleBg,userInfoPanel,
	userInfoArea,centerPanel,westPanel,northPanel,eastPanel,southPanel;
	private JLabel pageTitle,profileImage,playerName,userId,numOfcards,regDate;
	private JButton updateUserInfo;
	private RoundIconButton BackBtn;
	

	String name ,userID,registerDate;
	int numOfCards;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = screenSize.width;
	private int screenHeight = screenSize.height;

	
	public PlayerProfile(){
		init();			
		HeaderPanel();
		userInfo();
		backGround();
		userProfile.setVisible(true);
		
	}
	public void init() {
		userProfile = new JFrame();
		userProfile.setSize(screenSize);
		userProfile.setResizable(false);
		userProfile.setTitle("Pokemon TCG Card Tracking System");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		userProfile.setIconImage(logo.getImage());
		userProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userProfile.setLocationRelativeTo(null);//default location in center
		userProfile.setLayout(null);
		
	}
	public void backGround() {
		//create background
		//left area
		TriangleLabel topLeft2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.TOP_LEFT);
		topLeft2.setOpaque(true);
		topLeft2.setBounds(0, 0, screenWidth / 8, screenHeight / 2);
		userProfile.add(topLeft2);	
		
		TriangleLabel topLeft1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.TOP_LEFT);
		topLeft1.setOpaque(true);	
		topLeft1.setBounds(0, 0, screenWidth / 4, screenHeight);
		userProfile.add(topLeft1);	
		
		//right area
		TriangleLabel bottomRight2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight2.setOpaque(true);
		bottomRight2.setBounds(screenWidth - screenWidth / 8, screenHeight / 2, screenWidth / 8, screenHeight / 2);
		userProfile.add(bottomRight2);
		
		TriangleLabel bottomRight1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight1.setOpaque(true);
		bottomRight1.setBounds(screenWidth - screenWidth / 4, 0, screenWidth / 4, screenHeight);
		userProfile.add(bottomRight1);	
		
		//center area
		centerBg = new JPanel();
		centerBg.setOpaque(true);
		centerBg.setBackground(new Color(0xFFFF00));
		centerBg.setBounds(0, 0, screenWidth, screenHeight);
		userProfile.add(centerBg);
	
	}
	public void HeaderPanel() {
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
		
		userProfile.add(panelHeader);
	}
	public void userInfo() {
		 int panelX = (int)(screenWidth * 0.17);
	     int panelY = (int)(screenHeight * 0.14);
	     int panelW = (int)(screenWidth * 0.64);
	     int panelH = (int)(screenHeight * 0.74);
		
		//create profile picture
		userInfoPanel = new JPanel();
		userInfoPanel.setBounds(panelX, panelY, panelW, panelH);
		userInfoPanel.setLayout(null);
		userProfile.add(userInfoPanel);
		
		ImageIcon profilePic = new ImageIcon("resources/profileUse/mcPic.png");
		
		profileImage = new JLabel();
		profileImage.setIcon(profilePic);
		
		//locate panel for profile picture
		JPanel ImagePanel = new JPanel();
		ImagePanel.setBackground(new Color(0x89CFF0));
		ImagePanel.add(profileImage); //add profile into image panel
		ImagePanel.setBorder(BorderFactory.createLineBorder(new Color(0xB3D9FF), 5));
        ImagePanel.setBounds(0, 0, panelW / 3, panelH);
		userInfoPanel.add(ImagePanel); //add image panel into center panel
		
		//create a panel for user info
		userInfoArea = new JPanel();
		userInfoArea.setLayout(new BorderLayout());
		userInfoArea.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		userInfoArea.setBackground(new Color(0xB3D9FF));
        userInfoArea.setBounds(panelW / 3, 0, panelW * 2 / 3, panelH);
		userInfoPanel.add(userInfoArea);
		
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension (50,50));
		westPanel.setBackground(new Color(0xB3D9FF));
		userInfoArea.add(westPanel,BorderLayout.WEST);

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
		
		userInfoArea.add(northPanel,BorderLayout.NORTH);
		
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension (50,50));
		eastPanel.setBackground(new Color(0xB3D9FF));
		userInfoArea.add(eastPanel,BorderLayout.EAST);
		
		//display user info	
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (500,500));
		centerPanel.setBackground(new Color(0xB3D9FF));
		centerPanel.setLayout(new GridLayout(4,1));
		userInfoArea.add(centerPanel,BorderLayout.CENTER);

		playerName = new JLabel();
		playerName.setText("Player name: "+name);
		playerName.setFont(new Font("Times New Roman",Font.BOLD,30));
		playerName.setForeground(Color.black);
		playerName.setVerticalTextPosition(JLabel.TOP);
		playerName.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(playerName);
		
		userId = new JLabel();
		userId.setText("User ID: "+userID);
		userId.setFont(new Font("Times New Roman",Font.BOLD,30));
		userId.setForeground(Color.black);
		userId.setVerticalTextPosition(JLabel.TOP);
		userId.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(userId);

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
		
		//create button of update user info 
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension (100,100));
		southPanel.setBackground(new Color(0xB3D9FF));
		southPanel.setLayout(new FlowLayout());
		userInfoArea.add(southPanel,BorderLayout.SOUTH);
		
		updateUserInfo = new JButton("Update Personal Info");
		updateUserInfo.setFont(new Font("Times New Roman",Font.BOLD,30));
		updateUserInfo.setFocusable(false);
		updateUserInfo.setForeground(Color.black);
		updateUserInfo.setBackground(Color.LIGHT_GRAY);
		updateUserInfo.setPreferredSize(new Dimension (350,50));
		updateUserInfo.setBorder(BorderFactory.createLineBorder(Color.black,3));
		updateUserInfo.addActionListener(this);
		southPanel.add(updateUserInfo);
	}

	public void updateInfo() {
		//define the option
		String[]updateOption = {"Player Name","Password","Cancel"};
		
		//create option dialog
		int choice = JOptionPane.showOptionDialog(userInfoPanel, 
				"Which personal info you want update?", 
				"Personal Info Update", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, 
				null, 
				updateOption,0);
		
		switch(choice) {
			case 0://player name
				String newPlayerName = null;
				
				 while (true) {
				        newPlayerName = JOptionPane.showInputDialog(
				            userInfoPanel,
				            "Enter new player name:",
				            "Update player name",
				            JOptionPane.PLAIN_MESSAGE
				        );

				        if (newPlayerName == null) {
				            // User clicked Cancel or closed the dialog – cancel the update
				            return;
				        }

				        newPlayerName = newPlayerName.trim();

				        if (newPlayerName.isEmpty()) {
				            JOptionPane.showMessageDialog(
				                userInfoPanel,
				                "Name cannot be empty. Please try again.",
				                "WARNING",
				                JOptionPane.WARNING_MESSAGE
				            );
				        } else if (newPlayerName.equals(name)) {
				            JOptionPane.showMessageDialog(
				                userInfoPanel,
				                "Repeated name. Please try again.",
				                "WARNING",
				                JOptionPane.WARNING_MESSAGE
				            );
				        } else {
				            break; // valid input, exit loop
				        }
				    }

				
				//update the name
				name = newPlayerName;
				
				centerPanel.removeAll();
				playerName.setText("Player name: " + name);
		        centerPanel.add(playerName);
		        centerPanel.add(userId);
		        centerPanel.add(numOfcards);
		        centerPanel.add(regDate);
		        
		        centerPanel.revalidate();  // Refresh layout
				centerPanel.repaint();
				break;
				
			case 1://password
				 boolean passwordCorrect = false;
		         String originalPassword;
		         String storedPassword = "12345"; //link database later

		         while (!passwordCorrect) {
		        	 originalPassword = JOptionPane.showInputDialog(userInfoPanel, "Enter original password:");
  
		             if (originalPassword == null) {
		                    // User pressed Cancel
		                    break;
		                } else if (originalPassword.equals(storedPassword)) {
		                    passwordCorrect = true;
		                    String newPassword;
		                    while (true) {
		                        newPassword = JOptionPane.showInputDialog(userInfoPanel, "Enter new password:");

		                        if (newPassword == null) {
		                            // Cancel update
		                            return;
		                        } else if (newPassword.equals(originalPassword)) {
		                            JOptionPane.showMessageDialog(userInfoPanel, "New password cannot be the same as the original.", "Error", JOptionPane.ERROR_MESSAGE);
		                        } else if (newPassword.trim().isEmpty()) {
		                            JOptionPane.showMessageDialog(userInfoPanel, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
		                        } else {
		                            //update to database
		                            System.out.println("New Password: " + newPassword);//can delete later
		                            JOptionPane.showMessageDialog(userInfoPanel, "Update Successfully!", "Password Update", JOptionPane.PLAIN_MESSAGE);
		                            break;
		                        }
		                    }

		                } else {
		                    JOptionPane.showMessageDialog(userInfoPanel, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
	public void loadProfile(int playerId) {
	    Player player = Player.loadPlayerProfile(playerId);

	    if (player != null) {
	        //refresh the GUI components:
	    	playerName.setText("Name: " + player.getPlayerName());
	        userId.setText("Player ID: " + player.getPlayerID());
	        numOfcards.setText("Numbers of cards: " + player.getCardQuantity());
	        regDate.setText("Registered date: " + player.getRegistrationDate().toString());
	    } else
	        JOptionPane.showMessageDialog(userProfile, "Player not found.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BackBtn)  {
			System.out.println("Back button clicked!");
		}
		else if(e.getSource() == updateUserInfo) {
		if(e.getSource() == BackBtn){
		      userProfile.dispose();
		      FirstPage FirstPage = new FirstPage();
		}
		else if(e.getSource() == updateUserInfo)
			System.out.println("Update User button clicked!");
			updateInfo();
		}
	}
	
	//add here
	public static void main(String[] args) {
	    PlayerProfile profile = new PlayerProfile();

	    //for example:
	    profile.loadProfile(1);
	    profile.userProfile.setVisible(true);
	}
}
