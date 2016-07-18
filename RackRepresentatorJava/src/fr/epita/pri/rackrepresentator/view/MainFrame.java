package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import fr.epita.pri.rackrepresentator.data.ExcelLoader;
import fr.epita.pri.rackrepresentator.data.IDataLoader;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.view.drawer.Drawer;
import fr.epita.view.extras.DisplayMore;

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

		dataLoader = new ExcelLoader();
//		drawableData = dataLoader.loadAll();
		displayMore = new DisplayMore();
		addMouseListener(this);
	}

	public void loadFromFile(String filePath, String password) {
		drawableData = dataLoader.loadAllFromFile(filePath, password);
		repaint();
	}

	public void back() {
		for (Drawable d : actualView.getBrothers()) {
			d.setShouldDraw(true);
		}

		actualView.setShouldDrawChildren(false);

		for (Drawable d : actualView.getChildren()) {
			d.setShouldDraw(false);
		}

		repaint();
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

		if (drawableData != null) {
			Drawable drawable = drawableData.findDrawableWithPosition(e.getX(), e.getY());

			if (drawable != null) {
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
			} else {
				displayMore.hideAll();
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