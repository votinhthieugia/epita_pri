package fr.epita.view;

import java.awt.Graphics;

import javax.swing.JComponent;

import fr.epita.main.Ruler;
import java.awt.Color;

public class MainFrame extends JComponent {
	public MainFrame() {
		setBackground(Color.WHITE);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.black);
		
		int distancia = 300;
		
		new Ruler(50, 300,300, 300).draw(g);		
		new Ruler(50, 50, 50, 300).draw(g);
		
		new Ruler(50+distancia, 300,300+distancia, 300).draw(g);		
		new Ruler(50+distancia, 50, 50+distancia, 300).draw(g);
	}
}
