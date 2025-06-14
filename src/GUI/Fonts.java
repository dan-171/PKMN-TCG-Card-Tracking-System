package GUI;

import javax.swing.*;
import java.awt.*;


public class  Fonts{
	private Font header,heading1, heading2,  bodyFont;
	private final int headerFontSize = 50, heading1Size = 36 , heading2Size = 30,  bodyFontSize = 20; 
	
	public Fonts() {
		this.header = new Font("Times New Roman", Font.BOLD, headerFontSize);
		this.heading1 = new Font("Times New Roman", Font.BOLD, heading1Size);
		this.heading2 = new Font("Times New Roman", Font.BOLD, heading2Size);
		this.bodyFont = new Font("Times New Roman", Font.PLAIN, bodyFontSize);
		
		
		//Change the default fonts
		UIManager.put("Label.font", bodyFont);
        UIManager.put("Button.font", bodyFont);
        UIManager.put("TextField.font", bodyFont);
        UIManager.put("PasswordField.font", bodyFont);
	}
	
	
	//Overloading 
	public void HeaderFont(JLabel jLabel) {
		jLabel.setFont(header);
	}
	
	public void HeaderFont(JButton jButton ) {
		jButton.setFont(header);
	}
	
	
	public void Heading1(JLabel jLabel ) {
		jLabel.setFont(heading1);
	}
		
	public void Heading1 (JButton jButton ) {
		jButton.setFont(heading1);
	}
	
	public void Heading2(JLabel jLabel) {
		jLabel.setFont(heading2);
	}
	
	public void Heading2 (JButton jButton ) {
		jButton.setFont(heading2);
	}
	
	public void BodyFont(JLabel jLabel) {
		jLabel.setFont(bodyFont);
	}
	
	public void BodyFont (JButton jButton ) {
		jButton.setFont(bodyFont);
	}
	
	
	
	//TRING
	public Font fontOfText() {
		return header;
	}
	
	
	
}
