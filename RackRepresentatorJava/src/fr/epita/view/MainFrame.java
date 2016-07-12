package fr.epita.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import fr.epita.main.Ruler;
import fr.epita.models.DataLoader;
import fr.epita.models.Drawable;
import fr.epita.view.drawer.Drawer;

public class MainFrame extends JComponent {
	Drawable drawableData;
	
	public MainFrame() {
		setBackground(Color.WHITE);
		drawableData = DataLoader.load();
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
		
		Drawer.Instance().draw(g, drawableData);
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
			}
		});
	}
}
