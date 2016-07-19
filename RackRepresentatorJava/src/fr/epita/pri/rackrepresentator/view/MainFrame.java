package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import fr.epita.pri.rackrepresentator.data.DataLoader;
import fr.epita.pri.rackrepresentator.data.IDataLoader;
import fr.epita.pri.rackrepresentator.main.Console;
import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.settings.Setting;
import fr.epita.pri.rackrepresentator.view.drawer.Drawer;
import fr.epita.pri.rackrepresentator.view.extras.DisplayMore;

public class MainFrame extends BaseFrame implements MouseListener {
	private static final long serialVersionUID = 1L;

	private Drawable drawableData;
	private IDataLoader dataLoader;
	private DisplayMore displayMore;
	private Drawable actualView;
	private Drawable currentView;
	private boolean isLoadedFromDb;
	private Timer timer;

	public MainFrame(MainView manager) {
		super(manager);
		setSize(2000, 2000);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//////TODO WORKAROUND FOR SCROLLPANEL's scrollbar.
		for (int i = 0; i < 75; i++)
			add(new JLabel(" "));
		/////////
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		dataLoader = DataLoader.Instance();
		loadFromDB(true);
		displayMore = new DisplayMore();
		addMouseListener(this);
		setTimer();
	}
	
	public void loadFromDB(boolean shouldRepaint) {
		actualView = drawableData = dataLoader.loadFromDb();
		isLoadedFromDb = true;
		if (shouldRepaint) repaint();
	}

	public void loadFromFile(String filePath, String password) {
		actualView = drawableData = dataLoader.loadFromFile(filePath, password);
		isLoadedFromDb = false;
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

	//TODO move method to proper place.
	public void export() {
		BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();

		try {
			paint2(g);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (chooser.showOpenDialog(this.getParent()) == 0) {

				File file = new File(chooser.getSelectedFile().getPath());
				
				if (Files.isWritable(file.toPath())) {
					String path = chooser.getSelectedFile().getPath() + "\\RackRepresentator_" + actualView.getName()
							+ ".png";
					file = new File(path);
					ImageIO.write(bi, "PNG", file);
					Console.info("saved file: " + path);
				} else {
					Console.error("Export: Permission denied! please choose another directory.");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			Console.error("ERROR saving file: " + e.getMessage());
		}
	}
	
	public void setTimer() {
		if (Setting.Instance().getReloadTime() > 0) {
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					updateDrawing();
				}
			};
			
			if (timer != null) timer.cancel();
			timer = new Timer(true);
			timer.scheduleAtFixedRate(task, Setting.Instance().getReloadTime() * 1000, Setting.Instance().getReloadTime() * 1000);
		}
	}
	
	private void updateDrawing() {
		if (!isLoadedFromDb) return;
		
		if (actualView instanceof DataSystem) {
			loadFromDB(true);
		} else if (actualView instanceof DataCenter) {
			loadFromDB(false);
			Drawable drawable = null;
			for (Drawable d : drawableData.getChildren()) {
				if (d.getIndex() == currentView.getIndex()) {
					drawable = d;
					break;
				}
			}
			
			if (drawable != null && drawable.hasChildrenToShow()) {
				for (Drawable d : drawable.getBrothers()) {
					d.setShouldDraw(false);
				}

				drawable.setShouldDrawChildren(true);
				for (Drawable d : drawable.getChildren()) {
					d.setShouldDraw(true);
				}

				actualView = drawable;
			}
			
			repaint();
		}
		
		((TopFrame)manager.getFrame(FrameId.TOP)).updateTime();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint2(g);
	}

	private void paint2(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);

		if (drawableData != null) {
			Drawer.Instance().draw(g, drawableData);
			displayMore.draw(g);
		}
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
					currentView = drawable;
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