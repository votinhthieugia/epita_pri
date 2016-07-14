package fr.epita.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import fr.epita.main.Console;
import fr.epita.models.DataLoader;
import fr.epita.models.Drawable;
import fr.epita.view.drawer.Drawer;
import fr.epita.view.drawer.primitives.Ruler;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JComponent {
	Drawable drawableData;
	
	public MainFrame() {
//		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setBackground(Color.WHITE);
		
//		JComponent scrollPane = new JPanel();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(0, 0, getWidth(), getHeight());
//		add(scrollPane);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(0, 0, getWidth(), getHeight());
//		add(scrollPane);
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
		
		System.out.println("w: " + getWidth() + "\nh: " + getHeight());
		
		g.setColor(Color.black);
		
//		int distancia = 300;		
//		new Ruler(50, 300,300, 300).draw(g);		
//		new Ruler(50, 50, 50, 300).draw(g);
//		
//		new Ruler(50+distancia, 300,300+distancia, 300).draw(g);		
//		new Ruler(50+distancia, 50, 50+distancia, 300).draw(g);
		
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
				Console.Instance().info(e.getX() + " " + e.getY());
			}
		});
	}
}
