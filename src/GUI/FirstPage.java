package GUI;

import Database.PlayerAccess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class FirstPage implements ActionListener{
	private JFrame frame;
	private JPanel northPanel, centralPanel, westPanel, centralRightPanel, centralLeftPanel;
	private JTextField usernameField, userIDField;
	private JPasswordField  passwordField, passwordField1, passwordField2;
	private JButton signInButton, registerButton,passwordMenu, signInMenu, registerMenu, resetPasswordButton;
	private JCheckBox showPasswordCheckBox;
	private Boolean showPasswordBoolean;
	
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
		frame.setResizable(false);
		frame.setTitle("Pokemon TCG Card Tracking System");
		ImageIcon logo = new ImageIcon("resources/LOGO/logo.jpg");
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		centralRightPanel= setUp.gridBagLayout();
		centralLeftPanel = setUp.gridBagLayout();
		showPasswordBoolean = false;
		showPasswordCheckBox = new JCheckBox("Show Password");

		
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
		setUp.setGBC(gbc, 0, 0, 2, gbc.CENTER, gbc.NONE, new Insets(0, 0, 0, 100), 0);
		signInMenu = new JButton("Sign In"); 
		signInMenu.addActionListener(this);
		fonts.Heading1(signInMenu);	
		signInMenu.setPreferredSize(menuDimension);
		centralLeftPanel.add(signInMenu, gbc);
		
		
		//Register button
		//Adjust gbc  
		setUp.setGBC(gbc, 0, 1, 2, gbc.CENTER, gbc.NONE, new Insets(80, 0, 160, 100), 0);
		registerMenu = new JButton("Register");
		registerMenu.addActionListener(this);
		registerMenu.setPreferredSize(menuDimension);
		centralLeftPanel.add(registerMenu, gbc);
		
		
		
		//Add to centralPanel then add to frame
		centralPanel.add(centralLeftPanel);
		signInCentralPanel();
		centralPanel.add(centralRightPanel);
		frame.add(centralPanel, BorderLayout.CENTER);	
	}
	
	
	public void signInCentralPanel() {
		// Title Label
		JLabel titleJLabel = new JLabel("Please Sign In");
		setUp.setGBC(gbc, 0, 0, 2, gbc.CENTER, gbc.NONE, new Insets(-200, 0, 0, 0), 0);
		fonts.Heading1(titleJLabel);
		centralRightPanel.add(titleJLabel, gbc);

		//Username 
		Insets userInsets = new Insets(-50, 0, 50, 0);
		JLabel usernameJLabel = new JLabel("Username: ");
		setUp.setGBC(gbc, 0, 1, 1, gbc.LINE_START, gbc.NONE, userInsets, 0);
		centralRightPanel.add(usernameJLabel, gbc);

		usernameField = new JTextField(20);
		setUp.setGBC(gbc, 1, 1, 1, gbc.LINE_START, gbc.HORIZONTAL, userInsets, 1);
		centralRightPanel.add(usernameField, gbc);

		//Password
		Insets passwordInsets = new Insets(-20, 0, 30, 0);
		JLabel passwordJLabel = new JLabel("Password: ");
		setUp.setGBC(gbc, 0, 2, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel, gbc);
		
		passwordField = new JPasswordField (20);
		setUp.setGBC(gbc, 1, 2, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField, gbc);
		
		showPasswordCheckBox.addActionListener(this);
		setUp.setGBC(gbc, 0, 3, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 1);
		centralRightPanel.add(showPasswordCheckBox, gbc);
		
		//Change the pattern to hide the text
		passwordField.setEchoChar('*'); 
		
		//Sign In Button
		signInButton = new JButton("Sign In");
		fonts.Heading2(signInButton);
		signInButton.addActionListener(this);
		setUp.setGBC(gbc, 0, 4, 2, gbc.CENTER, gbc.HORIZONTAL, new Insets(0, 0, 20, 0), 1);
		centralRightPanel.add(signInButton, gbc);
		
		
		passwordMenu = new JButton("Forgot Password");
		setUp.setGBC(gbc, 0, 5, 2, gbc.CENTER, gbc.NONE, new Insets(0, 0, 0, 0), 1);
		passwordMenu.addActionListener(this);
		centralRightPanel.add(passwordMenu, gbc);
	} 
	
	public void registerCentralPanel() {
		// Title Label
		JLabel titleJLabel = new JLabel("Please Register");
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
		Insets passwordInsets = new Insets(-20, 0, 30, 0);
		JLabel passwordJLabel1 = new JLabel("Password: ");
		setUp.setGBC(gbc, 0, 2, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel1, gbc);
		
		passwordField1 = new JPasswordField (10);
		setUp.setGBC(gbc, 1, 2, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField1, gbc);
		
		JLabel passwordJLabel2 = new JLabel("Confirm Password: ");
		setUp.setGBC(gbc, 0, 3, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel2, gbc);
		
		passwordField2 = new JPasswordField (10);
		setUp.setGBC(gbc, 1, 3, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField2, gbc);
		
		showPasswordCheckBox.addActionListener(this);
		setUp.setGBC(gbc, 0, 4, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(showPasswordCheckBox, gbc);
		
		//Change the pattern to hide the text
        passwordField1.setEchoChar('*'); 
        passwordField2.setEchoChar('*');

		//Register Button
		 registerButton = new JButton("Register");
		 fonts.Heading2(registerButton);
		 registerButton.addActionListener(this);
		 setUp.setGBC(gbc, 0, 5, 2, gbc.CENTER, gbc.HORIZONTAL, new Insets(0, 0, 20, 0), 1);
		 centralRightPanel.add(registerButton, gbc);
		
	} 
	
	public void forgotPasswordCentralPanel() {
		// Title Label
		JLabel titleJLabel = new JLabel("Forgot Password");
		setUp.setGBC(gbc, 0, 0, 2, gbc.CENTER, gbc.NONE, new Insets(-200, 0, 0, 0), 0);
		fonts.Heading1(titleJLabel);
		centralRightPanel.add(titleJLabel, gbc);

		
		//Username 
		Insets userInsets = new Insets(-50, 0, 50, 0);
		JLabel userIDJLabel = new JLabel("User ID: ");
		setUp.setGBC(gbc, 0, 1, 1, gbc.LINE_START, gbc.NONE, userInsets, 0);
		centralRightPanel.add(userIDJLabel, gbc);

		userIDField = new JTextField(10);
		setUp.setGBC(gbc, 1, 1, 1, gbc.LINE_START, gbc.HORIZONTAL, userInsets, 1);
		centralRightPanel.add(userIDField, gbc);
		
		//Password
		Insets passwordInsets = new Insets(-20, 0, 30, 0);
		JLabel passwordJLabel1 = new JLabel("Password: ");
		setUp.setGBC(gbc, 0, 2, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel1, gbc);
		
		passwordField1 = new JPasswordField (10);
		setUp.setGBC(gbc, 1, 2, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField1, gbc);
		
		JLabel passwordJLabel2 = new JLabel("Confirm Password: ");
		setUp.setGBC(gbc, 0, 3, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(passwordJLabel2, gbc);
		
		passwordField2 = new JPasswordField (10);
		setUp.setGBC(gbc, 1, 3, 1, gbc.LINE_START, gbc.HORIZONTAL, passwordInsets, 1);
		centralRightPanel.add(passwordField2, gbc);
		
		showPasswordCheckBox.addActionListener(this);
		setUp.setGBC(gbc, 0, 4, 1, gbc.LINE_START, gbc.NONE, passwordInsets, 0);
		centralRightPanel.add(showPasswordCheckBox, gbc);
		
		//Change the pattern to hide the text
        passwordField1.setEchoChar('*'); 
        passwordField2.setEchoChar('*');

		//reset Password Button
         resetPasswordButton = new JButton("Reset Password");
		 fonts.Heading2(resetPasswordButton);
		 resetPasswordButton.addActionListener(this);
		 setUp.setGBC(gbc, 0, 5, 2, gbc.CENTER, gbc.HORIZONTAL, new Insets(0, 0, 20, 0), 1);
		 centralRightPanel.add(resetPasswordButton, gbc);
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
		    centralRightPanel.revalidate();     //Recalculate layout
		    centralRightPanel.repaint();        //Repaint the panel
		    
		    //Reset the showPasswordCheckBox
		    showPasswordCheckBox.setSelected(false);
		}
		
		else if (event.getSource() == registerMenu) {
		    fonts.Heading1(registerMenu);    
		    fonts.BodyFont(signInMenu);
		    centralRightPanel.removeAll(); 
		    registerCentralPanel(); 
		    centralRightPanel.revalidate();     
		    centralRightPanel.repaint();
		    
		    //Reset the showPasswordCheckBox
		    showPasswordCheckBox.setSelected(false);
		}
		
		else if (event.getSource() == showPasswordCheckBox) {
				// Show text
				if (showPasswordCheckBox.isSelected()) {
	                passwordField.setEchoChar((char) 0); 
	                passwordField1.setEchoChar((char) 0); 
	                passwordField2.setEchoChar((char) 0); 
	            } else {
	                passwordField.setEchoChar('*'); // Hide text
	                passwordField1.setEchoChar('*'); 
	                passwordField2.setEchoChar('*');
	            }
		}
		

		else if (event.getSource() == passwordMenu) {
		    centralRightPanel.removeAll(); // Clear existing components
		    forgotPasswordCentralPanel(); // Call the method to set up the forgot password UI
		    centralRightPanel.revalidate(); // Refresh layout
		    centralRightPanel.repaint();    // Repaint to show changes
		    
		    // Reset the showPasswordCheckBox
		    showPasswordCheckBox.setSelected(false);
		}

		
		else if (event.getSource() == resetPasswordButton) {
			// Handle reset password
			PlayerAccess playerAccess = new PlayerAccess();
	        try {
	        	
	            char[] passwordChars1 = passwordField1.getPassword();
	            String password1 = new String(passwordChars1);
	            char[] passwordChars2 = passwordField2.getPassword();
	            String password2 = new String(passwordChars2);
	            
	            
	            //Check to change to the forgot password function
	            if (password1.equals(password2) &&
	                !userIDField.getText().isEmpty()) {
	            	
	                int id = playerAccess.insertUser(userIDField.getText(), password1);

	                if (id > 0) {
	                    JOptionPane.showMessageDialog(frame,
	                         "Registration successful!\nPlayer ID: " + id + 
	                         "\nUsername: " + usernameField.getText() + 
	                         "\nPassword: " + password1);  
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
		
		else if (event.getSource() == signInButton) {
			// Handle sign in
			PlayerAccess playerAccess = new PlayerAccess();
	        try {
	            char[] passwordChars = passwordField.getPassword();
	            String password = new String(passwordChars);

	            boolean isValid = playerAccess.validateLogin(usernameField.getText(), password);

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
			PlayerAccess playerAccess = new PlayerAccess();
	        try {
	            char[] passwordChars1 = passwordField1.getPassword();
	            String password1 = new String(passwordChars1);
	            char[] passwordChars2 = passwordField2.getPassword();
	            String password2 = new String(passwordChars2);
	            
	            if (password1.equals(password2) &&
	                !usernameField.getText().isEmpty()) {

	                int id = playerAccess.insertUser(usernameField.getText(), password1);

	                if (id > 0) {
	                    JOptionPane.showMessageDialog(frame,
	                         "Registration successful!\nYour User ID: " + id + 
	                         "\nUsername: " + usernameField.getText() + 
	                         "\nPassword: " + password1);  
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
}