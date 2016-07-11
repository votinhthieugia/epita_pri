package fr.epita.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextArea;

public class BottomFrame extends JComponent {
	public BottomFrame() {
		setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrConsoleExample = new JTextArea();
		txtrConsoleExample.setText("Console Example \n Ipsum \n Lorem \n Test");
		add(txtrConsoleExample);
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
//	    setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
	}
}
