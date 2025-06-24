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
	
	public void setGBC(GridBagConstraints gbc, int x, int y, int gridWidth, int anchor, int fill, Insets insets, double weightx) {
	    gbc.gridx = x;
	    gbc.gridy = y;
	    gbc.gridwidth = gridWidth;
	    gbc.anchor = anchor;
	    gbc.fill = fill;
	    gbc.insets = insets;
	    gbc.weightx = weightx;
	}

}
