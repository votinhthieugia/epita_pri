package fr.epita.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import fr.epita.data.ExcelLoader;
import fr.epita.data.IDataLoader;
import fr.epita.main.Console;
import fr.epita.models.Drawable;
import fr.epita.view.drawer.Drawer;

public class MainFrame extends BaseFrame {
	private static final long serialVersionUID = 1L;

	Drawable drawableData;
	IDataLoader dataLoader;
	
	public MainFrame(Window manager) {
		super(manager);
		setSize(2000, 2000);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(int i = 0 ; i< 100;i++)
		add(new JLabel(" "));
		
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		dataLoader = new ExcelLoader();
		drawableData = dataLoader.loadAll();
	}


	public void loadFromFile(String filePath) {
		drawableData = dataLoader.loadAllFromFile(filePath);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		System.out.println("w: " + getWidth() + "\nh: " + getHeight());
		
		g.setColor(Color.black);
		
		if (drawableData != null) {
			Drawer.Instance().draw(g, drawableData);
		}
		
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
