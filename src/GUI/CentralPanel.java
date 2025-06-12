package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CentralPanel {
	private JPanel panelCentral ;
	private JLabel lblTitle;
	
	//Overloading Constructor
	public CentralPanel (JFrame frame, String lbTitle) {
		panelCentral = new JPanel();
		lblTitle = new JLabel(lbTitle);
		panelCentral.add(lblTitle);
		frame.add(panelCentral, BorderLayout.CENTER);
	}
	
	public CentralPanel (JFrame frame, int row, int column) {
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(row, column));
		frame.add(panelCentral, BorderLayout.CENTER);
	}
	
	
	
	public void setTitleFonts(Font font) {
		this.lblTitle.setFont(font);
	}
	
	public void addPanel(JPanel panel) {
		panelCentral.add(panel);
	}
	
	public void addLabel(String Text) {
		JLabel label = new JLabel(Text);
		panelCentral.add(label);
	}
}
