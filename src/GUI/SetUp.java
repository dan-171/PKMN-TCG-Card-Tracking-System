package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*; 
import java.util.Iterator;

public class SetUp {
	
	public JPanel gridLayout(int row, int column) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(row, column));
		return panel;
	}
	
	
	public void addText(JPanel panel, String message) {
		JLabel prmopt = new JLabel(message);
		panel.add(prmopt);
	}
	
	
	//Overloading
	public void formField(JPanel panel, JLabel jLabel, JTextField jtextfield, int totalRow, int targetRow) {
		for (int i = 0; i < totalRow;i++) {
			
			if (i == (targetRow-1)) {
				panel.add(jLabel);
				panel.add(jtextfield);
			}
			
			else {
				panel.add(new JLabel());
				panel.add(new JLabel());
			}
			
		}
	}
	
	public void formatButton(JPanel panel, JButton jButton, int totalCells, int targetCells) {
		for (int i = 0; i < totalCells;i++) {
			
			if (i == (targetCells-1)) {
				panel.add(jButton);
			}
			
			else {
				panel.add(new JPanel());
			}
			
		}
	}
}
