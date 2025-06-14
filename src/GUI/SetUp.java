package GUI;

import javax.swing.*;
import java.awt.*;

public class SetUp {
		
	public JPanel gridLayout(int row, int column) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(row, column));
		return panel;
	}
	
	public JPanel gridBagLayout() {
		JPanel panel  = new JPanel(new GridBagLayout());
		return panel;
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
	
	public void setGBC(GridBagConstraints gbc, int x, int y, int gridWidth, int anchor, int fill, Insets insets, double weightx) {
	    gbc.gridx = x;
	    gbc.gridy = y;
	    gbc.gridwidth = gridWidth;
	    gbc.anchor = anchor;
	    gbc.fill = fill;
	    gbc.insets = insets;
	    gbc.weightx = weightx;
	}

	
	public void setSize(JTextField jTextField, Dimension dimension) {
		jTextField.setPreferredSize(dimension);
	}
}
