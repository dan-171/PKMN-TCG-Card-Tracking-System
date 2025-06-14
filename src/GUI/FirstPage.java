package GUI;

import Database.JDBC;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class FirstPage implements ActionListener{
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel, centralRightPanel, centralLeftPanel;
	private JTextField usernameField, passwordField, passwordField1, passwordField2;
	JButton signInButton, registerButton,signInMenu, registerMenu, forgotPassword;
	
	Fonts fonts = new Fonts();
	SetUp setUp = new SetUp();
	GridBagConstraints gbc = new GridBagConstraints();
	
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
		centralRightPanel= setUp.gridBagLayout();
		centralLeftPanel = setUp.gridLayout(2, 1);
		
	}
	
	public void NorthPanel() {
		
		northPanel = setUp.gridBagLayout();
		JLabel Title = new JLabel("Welcome to the Pokemon TCG Card Tracking System");
		fonts.HeaderFont(Title);
		
		setUp.setGBC(gbc, 0, 1, 1, gbc.CENTER, gbc.HORIZONTAL, new Insets(30, 0, 0, 0), 0);
		northPanel.add(Title, gbc);
		
		frame.add(northPanel, BorderLayout.NORTH);
	}
	
	public void CentralPanel() {
		//Divide the central part into two
		centralPanel = setUp.gridLayout(1, 2);
		
		//Declare gbc 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER; 
		Dimension menuDimension = new Dimension(300, 100);
        
		//Left Part
		//Sign In button
		gbc.insets = new Insets(150, 0, 0, 100); // Top, Left, Bottom, Right padding
		
		JPanel leftPanel1 = setUp.gridBagLayout();
		signInMenu = new JButton("Sign In"); 
		signInMenu.addActionListener(this);
		fonts.Heading1(signInMenu);	
		signInMenu.setPreferredSize(menuDimension);
		
		leftPanel1.add(signInMenu, gbc);
		centralLeftPanel.add(leftPanel1);
		
		
		//Register button
		//Adjust gbc for the leftPanel2
		gbc.insets = new Insets(0, 0, 280, 100); 
	
		JPanel leftPanel2 = setUp.gridBagLayout();	
		registerMenu = new JButton("Register");
		registerMenu.addActionListener(this);
		leftPanel2.add(registerMenu);
		registerMenu.setPreferredSize(menuDimension);
		
		leftPanel2.add(registerMenu, gbc);
		centralLeftPanel.add(leftPanel2);
		
		
		
		//Add to centralPanel then add to frame
		centralPanel.add(centralLeftPanel);
		signInCentralPanel();
		centralPanel.add(centralRightPanel);
		frame.add(centralPanel, BorderLayout.CENTER);	
	}
	
	
	public void signInCentralPanel() {
		//Right Part
		
		
		// Title Label
		JLabel titleJLabel = new JLabel("Please Sign In.");
		setUp.setGBC(gbc, 0, 0, 2, gbc.CENTER, gbc.NONE, new Insets(-200, 0, 0, 0), 0);
		fonts.Heading1(titleJLabel);
		centralRightPanel.add(titleJLabel, gbc);

		//Username 
		Insets userInsets = new Insets(-50, 0, 50, 0);
		JLabel usernameJLabel = new JLabel("Username: ");
		setUp.setGBC(gbc, 0, 1, 1, gbc.LINE_START, gbc.NONE, userInsets, 0);
		centralRightPanel.add(usernameJLabel, gbc);

		usernameField = new JTextField(10);
		setUp.setGBC(gbc, 1, 1, 1, gbc.LINE_START, gbc.HORIZONTAL, userInsets, 1);
		centralRightPanel.add(usernameField, gbc);

		//Password
		Insets passwordInsets = new Insets(-20, 0, 50, 0);
		JLabel passwordJLabel = new JLabel("Password: ");
		setUp.setGBC(gbc, 0, 2, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel, gbc);
		
		passwordField = new JTextField(10);
		setUp.setGBC(gbc, 1, 2, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField, gbc);
		
		//Sign In Button
		signInButton = new JButton("Log In");
		fonts.Heading2(signInButton);
		signInButton.addActionListener(this);
		setUp.setGBC(gbc, 0, 3, 2, gbc.CENTER, gbc.HORIZONTAL, new Insets(0, 0, 20, 0), 1);
		centralRightPanel.add(signInButton, gbc);
		
		
		forgotPassword	= new JButton("Forgot Password");
		setUp.setGBC(gbc, 0, 4, 2, gbc.CENTER, gbc.NONE, new Insets(0, 0, 0, 0), 1);
		forgotPassword.addActionListener(this);
		centralRightPanel.add(forgotPassword, gbc);
		
		
	} 
	
	public void registerCentralPanel() {
		
		JLabel usernameJLabel = new JLabel("Username: ");
		usernameField = new JTextField(10);
		setUp.formField(centralRightPanel, usernameJLabel, usernameField, 3, 3);
		
		
		
		JLabel pwd1 = new JLabel("Password: ");
		passwordField1 = new JTextField(10);
		JLabel pwd2 = new JLabel("Confirm Password: ");
		passwordField2 = new JTextField(10);
		
		
		
		centralRightPanel.add(new JPanel());
		centralRightPanel.add(new JPanel());
		centralRightPanel.add(pwd1);
		centralRightPanel.add(passwordField1);
		centralRightPanel.add(pwd2);
		centralRightPanel.add(passwordField2);
		
		
		
		registerButton = new JButton("Register");
		fonts.Heading2(registerButton);
		registerButton.addActionListener(this);
		
		centralRightPanel.add(new JLabel());
		centralRightPanel.add(registerButton);
		
		centralRightPanel.add(centralRightPanel);
		
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
			// Handle login
	        try {
	            JDBC db = new JDBC();
	            boolean isValid = db.validateLogin(usernameField.getText(), passwordField.getText());

	            if (isValid) {
	                JOptionPane.showMessageDialog(frame, "Login successful!");
	                // proceed to the main application
	            } else {
	                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(frame, "Error while logging in.");
	        }
		}
		
		else if (event.getSource() == registerButton) {
			// Handle registration
	        try {
	            JDBC db = new JDBC();

	            if (passwordField1.getText().equals(passwordField2.getText()) &&
	                !usernameField.getText().isEmpty()) {

	                int id = db.insertUser(usernameField.getText(), passwordField1.getText());

	                if (id > 0) {
	                    JOptionPane.showMessageDialog(frame,
	                         "Registration successful!\nYour User ID: " + id + 
	                         "\nUsername: " + usernameField.getText() + 
	                         "\nPassword: " + passwordField1.getText());  
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Registration failed.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(frame, "Passwords do not match or Username is empty.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(frame, "Error while registering.");
	        }
		}

	}
	
	public static void main (String [] args){
		new FirstPage();

	}
}