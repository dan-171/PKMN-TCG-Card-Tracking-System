package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;



public class FirstPage implements ActionListener{
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel, centralRightPanel;
	private JTextField usernameField, passwordField, passwordField1, passwordField2;
	JButton signInButton, registerButton,signInMenu, registerMenu;
	private JRadioButton rb1, rb2, rb3;
	private ButtonGroup group;
	
	
	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	
	public static final int breadth = 1920, length = 1080, Margin = 300;
	
	//Constructor
	public FirstPage(){
		
		init();
		NorthPanel();
		CentralPanel();
		WestPanel();
		EastPanel();
		frame.setVisible(true);
	}
	
	public void init(){
		frame = new JFrame();
		frame.setSize(breadth,length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		centralRightPanel = setUp.gridLayout(4, 1);
		
	}
	
	public void NorthPanel() {
		
		northPanel = new JPanel();
		JLabel Title = new JLabel("Pokemon TCG Card Tracking System");
		fonts.HeaderFont(Title);
		northPanel.add(Title);
		
		
		frame.add(northPanel, BorderLayout.NORTH);
	}
	
	public void CentralPanel() {
				
		
		//Divide the central part into two, left part put the Sign In sentence
		centralPanel = setUp.gridLayout(1, 2);
		
		//Left Part
		JPanel leftPanel = setUp.gridLayout(2, 1);
		
		//Sign In button
		JPanel leftPanel1 = setUp.gridLayout(3, 1);
		
		leftPanel1.add(new JPanel()); 
		
		JPanel leftJPanel1_2 = setUp.gridLayout(1, 2);
		signInMenu = new JButton("Sign In"); 
		signInMenu.addActionListener(this);
		fonts.Heading1(signInMenu);	
		leftJPanel1_2.add(signInMenu);
		leftJPanel1_2.add(new JPanel());
		leftPanel1.add(leftJPanel1_2);
		
		leftPanel1.add(new JPanel());
		leftPanel.add(leftPanel1);
		
		
		//Register button
		JPanel leftPanel2 = setUp.gridLayout(3, 1);
		
		JPanel leftJPanel2_1 = setUp.gridLayout(1, 2);
		registerMenu = new JButton("Register");
		registerMenu.addActionListener(this);
		leftPanel2.add(registerMenu);
		leftJPanel2_1.add(registerMenu);
		leftJPanel2_1.add(new JPanel());
		leftPanel2.add(leftJPanel2_1);
		
		leftPanel2.add(new JPanel());
		leftPanel2.add(new JPanel());
		leftPanel.add(leftPanel2);
		
		
		centralPanel.add(leftPanel);
		signInCentralPanel();
		
		centralPanel.add(centralRightPanel);
		frame.add(centralPanel, BorderLayout.CENTER);
		
		
		
	}
	
	public void signInCentralPanel() {
		//Right Part
		JPanel righePanel1 = setUp.gridLayout(3, 2);
		JLabel usernameJLabel = new JLabel("Username: ");
		usernameField = new JTextField(10);
		setUp.formField(righePanel1, usernameJLabel, usernameField, 3, 3);
		centralRightPanel.add(righePanel1);
		
		
		JPanel righePanel2 = setUp.gridLayout(3, 2);
		JLabel passwordJLabel = new JLabel("Password: ");
		passwordField = new JTextField(10);
		
		setUp.formField(righePanel2, passwordJLabel, passwordField, 3, 2);
		
		centralRightPanel.add(righePanel2);
		
		
		JPanel righePanel3 = setUp.gridLayout(3, 1);
		signInButton = new JButton("Log In");
		fonts.Heading2(signInButton);
		signInButton.addActionListener(this);
		righePanel3.add(signInButton);
		righePanel3.add(new JLabel());
		
		JPanel right3_3 = new JPanel();
		right3_3.add(new JButton("Forgot Password"));
		
		righePanel3.add(right3_3);
		centralRightPanel.add(righePanel3);
		
		

	} 
	
	public void registerCentralPanel() {
		JPanel righePanel1 = setUp.gridLayout(3, 2);
		JLabel usernameJLabel = new JLabel("Username: ");
		usernameField = new JTextField(10);
		setUp.formField(righePanel1, usernameJLabel, usernameField, 3, 3);
		centralRightPanel.add(righePanel1);
		
		
		JPanel righePanel2 = setUp.gridLayout(3, 2);
		JLabel pwd1 = new JLabel("Password: ");
		passwordField1 = new JTextField(10);
		JLabel pwd2 = new JLabel("Confirm Password: ");
		passwordField2 = new JTextField(10);
		
		righePanel2.add(new JPanel());
		righePanel2.add(new JPanel());
		righePanel2.add(pwd1);
		righePanel2.add(passwordField1);
		righePanel2.add(pwd2);
		righePanel2.add(passwordField2);
		

		centralRightPanel.add(righePanel2);
		
		
		JPanel righePanel3 = setUp.gridLayout(3, 1);
		registerButton = new JButton("Register");
		fonts.Heading2(registerButton);
		registerButton.addActionListener(this);
		
		righePanel3.add(new JLabel());
		righePanel3.add(registerButton);
		
		centralRightPanel.add(righePanel3);
	} 
	
	public void WestPanel() {
		
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(Margin, 0));
		
		frame.add(westPanel, BorderLayout.WEST);
	}
	
	public void EastPanel() {
		
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(Margin, 0));
		
		frame.add(eastPanel, BorderLayout.EAST);
	}
	
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == signInMenu) {
		    fonts.Heading1(signInMenu);
		    fonts.BodyFont(registerMenu);
		    centralRightPanel.removeAll(); // Clear existing components
		    signInCentralPanel(); 

		}

		else if (event.getSource() == registerMenu) {
		    fonts.Heading1(registerMenu);    
		    fonts.BodyFont(signInMenu);
		    centralRightPanel.removeAll(); 
		    registerCentralPanel(); 
		   
		}
		
		else if (event.getSource() == signInButton) {
		    
		}
		
		else if (event.getSource() == registerButton) {

		}
		
		
		

	}
	
	public static void main (String [] args){
		new FirstPage();
	}
}