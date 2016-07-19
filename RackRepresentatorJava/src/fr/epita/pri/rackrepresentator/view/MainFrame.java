package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import fr.epita.pri.rackrepresentator.data.DataLoader;
import fr.epita.pri.rackrepresentator.data.IDataLoader;
import fr.epita.pri.rackrepresentator.main.Console;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.view.drawer.Drawer;
import fr.epita.pri.rackrepresentator.view.extras.DisplayMore;

public class MainFrame extends BaseFrame implements MouseListener {
	private static final long serialVersionUID = 1L;

	private Drawable drawableData;
	private IDataLoader dataLoader;
	private DisplayMore displayMore;
	private Drawable actualView;

	public MainFrame(Window manager) {
		super(manager);
		setSize(2000, 2000);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		////// WORKAROUND FOR SCROLLPANEL's scrollbar.
		for (int i = 0; i < 75; i++)
			add(new JLabel(" "));
		/////////
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		dataLoader = DataLoader.Instance();
		loadFromDB();
		displayMore = new DisplayMore();
		addMouseListener(this);
	}
	
	public void loadFromDB() {
		drawableData = dataLoader.loadFromDb();
		repaint();
	}

	public void loadFromFile(String filePath, String password) {
		drawableData = dataLoader.loadFromFile(filePath, password);
		repaint();
	}

	public void back() {
		if (actualView != null && actualView.getParent() != null) {
			for (Drawable d : actualView.getBrothers()) {
				d.setShouldDraw(true);
			}

			actualView.setShouldDrawChildren(false);

			for (Drawable d : actualView.getChildren()) {
				d.setShouldDraw(false);
			}

			actualView = actualView.getParent();
			Console.info("showing: " + actualView.getName());
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);

		if (drawableData != null) {
			Drawer.Instance().draw(g, drawableData);
		}

		displayMore.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		displayMore.hideAll();
		
		if (drawableData != null) {
			Drawable drawable = drawableData.findDrawableWithPosition(e.getX(), e.getY());
			
			if (drawable != null) {
				Console.info("showing: " + drawable.getName());
				if (e.getButton() == MouseEvent.BUTTON3) {
					displayMore.show(drawable, drawable.getX() + drawable.getWidth(), e.getY());

				} else if ((e.getButton() == MouseEvent.BUTTON1) && drawable.hasChildrenToShow()) {

					for (Drawable d : drawable.getBrothers()) {
						d.setShouldDraw(false);
					}

					drawable.setShouldDrawChildren(true);
					for (Drawable d : drawable.getChildren()) {
						d.setShouldDraw(true);
					}

					actualView = drawable;
				}
			} 

			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}