package GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*; 



public class FirstPage {
	private JFrame frame;
	private JPanel panel1;
	private JLabel prmopt1;
	private JTextField kilometers;
	private JButton jbtCalculate, jbtClose;
	private JRadioButton rb1, rb2, rb3;
	private ButtonGroup group;
	
	public FirstPage(){
		init();
		
		NorthPanel northPanel =new NorthPanel(frame,"Pokemon TCG Card Tracking System");
	
		frame.setVisible(true);
	}
	
	
	
	public void init(){
		frame = new JFrame();
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	}

	public static void main (String [] args){
		new FirstPage();
	}
}