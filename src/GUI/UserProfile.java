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


public class UserProfile implements ActionListener{
	private JFrame userProfile;
	private JPanel panelHeader,centerBg,titleBg,userInfoPanel,userInfoArea;
	private JLabel pageTitle,profileImage,userName,userId,numOfcards,regDate;
	private JButton updateUserInfo;
	private RoundIconButton BackBtn;
	
	GridBagConstraints gbc = new GridBagConstraints();
	String name,userID,registerDate;
	int numOfCards;
	
	public UserProfile(){
		init();			
		HeaderPanel();
		userInfo();
		backGround();
		userProfile.setVisible(true);
	}
	
	public void init() {
		userProfile = new JFrame();
		userProfile.setSize(1920,1080);
		userProfile.setResizable(false);
		userProfile.setTitle("Pokemon TCG Card Tracking System");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		userProfile.setIconImage(logo.getImage());
		userProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userProfile.setLayout(null);
		
	}
	public void backGround() {
		//create background
		//left area
		TriangleLabel topLeft2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.TOP_LEFT);
		topLeft2.setOpaque(true);	
		topLeft2.setBounds(0, 0, 250, 500);
		
		userProfile.add(topLeft2);	TriangleLabel topLeft1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.TOP_LEFT);
		topLeft1.setOpaque(true);	
		topLeft1.setBounds(0, 0, 500, 1050);
		userProfile.add(topLeft1);	
		
		//right area
		TriangleLabel bottomRight2 = new TriangleLabel(new Color(0xFFAE42), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight2.setOpaque(true);
		bottomRight2.setBounds(1660, 543, 250, 500);
		userProfile.add(bottomRight2);
		
		TriangleLabel bottomRight1 = new TriangleLabel(new Color(0xFFBF00), TriangleLabel.Direction.BOTTOM_RIGHT);
		bottomRight1.setOpaque(true);
		bottomRight1.setBounds(1407, 0, 500, 1050);
		userProfile.add(bottomRight1);	
		
		//center area
		centerBg = new JPanel();
		centerBg.setOpaque(true);
		centerBg.setBackground(new Color(0xFFFF00));
		centerBg.setBounds(0, 0, 1920, 1080);	
		userProfile.add(centerBg);
	
	}
	public void HeaderPanel() {
		//create header panel
		panelHeader = new JPanel();
		panelHeader.setBounds(0, 0, 1920, 100);
		panelHeader.setOpaque(false);
		panelHeader.setLayout(null);
		
		//rescaled the image icon
		ImageIcon oriBackIcon = new ImageIcon("resources/profileUse/back-button.png");
		Image scaledIcon = oriBackIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon backIcon = new ImageIcon(scaledIcon);
		
		//create back button
		BackBtn = new RoundIconButton(backIcon);
		BackBtn.setBounds(0,0,60,60);
		BackBtn.setFocusable(false);
		BackBtn.setBackground(new Color(255,77,77));
		BackBtn.addActionListener(this);
		panelHeader.add(BackBtn);
		
		userProfile.add(panelHeader);
	}
	public void userInfo() {
		//create profile picture
		userInfoPanel = new JPanel();
		userInfoPanel.setBounds(325,150,1225,800);
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
		ImagePanel.setBounds(0,0,400,800);
		userInfoPanel.add(ImagePanel); //add image panel into center panel
		
		//create a panel for user info
		userInfoArea = new JPanel();
		userInfoArea.setLayout(new BorderLayout());
		userInfoArea.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		userInfoArea.setBackground(new Color(0xB3D9FF));
		userInfoArea.setBounds(400,0,825,800);
		userInfoPanel.add(userInfoArea);
		
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension (50,50));
		westPanel.setBackground(new Color(0xB3D9FF));
		userInfoArea.add(westPanel,BorderLayout.WEST);

		JPanel northPanel = new JPanel();
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
		
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension (50,50));
		eastPanel.setBackground(new Color(0xB3D9FF));
		userInfoArea.add(eastPanel,BorderLayout.EAST);
		
		//display user info	
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (500,500));
		centerPanel.setBackground(new Color(0xB3D9FF));
		centerPanel.setLayout(new GridLayout(4,1));
		userInfoArea.add(centerPanel,BorderLayout.CENTER);

		userName = new JLabel();
		userName.setText("Name: "+name);
		userName.setFont(new Font("Times New Roman",Font.BOLD,30));
		userName.setForeground(Color.black);
		userName.setVerticalTextPosition(JLabel.TOP);
		userName.setHorizontalTextPosition(JLabel.LEFT);
		centerPanel.add(userName);
		
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
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension (100,100));
		southPanel.setBackground(new Color(0xB3D9FF));
		southPanel.setLayout(new FlowLayout());
		userInfoArea.add(southPanel,BorderLayout.SOUTH);
		
		updateUserInfo = new JButton("Update Personal Info");
		updateUserInfo.setFont(new Font("Times New Roman",Font.BOLD,30));
		updateUserInfo.setFocusable(false);
		updateUserInfo.setForeground(Color.black);
		updateUserInfo.setBackground(Color.LIGHT_GRAY);
		updateUserInfo.setBorder(BorderFactory.createLineBorder(Color.black,3));
		updateUserInfo.addActionListener(this);
		southPanel.add(updateUserInfo);
	}
	
	//add here
	public void loadProfile(int PlayerId) {
	    Player player = new Player(PlayerId);
	    boolean found = player.loadPlayerProfile(PlayerId);

	    if (found) {
	        this.name = player.getName();
	        this.userID = String.valueOf(PlayerId);
	        this.numOfCards = player.getCardQuantity();
	        this.registerDate = player.getRegistrationDate().toString();

	        // Now refresh the GUI components:
	        userName.setText("Name: " + name);
	        userId.setText("User ID: " + userID);
	        numOfcards.setText("Numbers of cards: " + numOfCards);
	        regDate.setText("Registered date: " + registerDate);
	    } else {
	        JOptionPane.showMessageDialog(userProfile, "Player not found.");
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == BackBtn){
		      userProfile.dispose();
		      FirstPage FirstPage = new FirstPage();
		}
		else if(e.getSource() == updateUserInfo)
			System.out.println("Update User button clicked!");
	}
	
	//add here
	public static void main(String[] args) {
	    userProfile profile = new userProfile();

	    // for example:
	    profile.loadProfile(4);
	    profile.userProfile.setVisible(true);
	}
}
