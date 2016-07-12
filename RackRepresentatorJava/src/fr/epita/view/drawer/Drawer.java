package fr.epita.view.drawer;

import java.awt.Graphics;

import fr.epita.models.DataCenter;
import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Server;

public class Drawer {
	public enum DrawableType {
		SERVER,
		RACK,
		ROOM,
		DATA_CENTER
	};
	
	private ServerDrawer serverDrawer;
	private RackDrawer rackDrawer;
	private DataCenterDrawer dcDrawer;
	
	private static Drawer instance;
	
	public static Drawer Instance() {
		if (instance == null) {
			instance = new Drawer();
		}
		return instance;
	}
	
	private Drawer() {
		serverDrawer = new ServerDrawer();
		rackDrawer = new RackDrawer();
		dcDrawer = new DataCenterDrawer();
	}
	
	public IDrawer draw(Graphics g, Drawable drawable) {
		IDrawer drawer = getDrawer(drawable);
		drawer.draw(g, drawable);
		return drawer;
	}
	
	public IDrawer getDrawer(Drawable drawable) {
		if (drawable instanceof DataCenter) return dcDrawer;
		else if (drawable instanceof Rack) return rackDrawer;
		else if (drawable instanceof Server) return serverDrawer;
		return null;
	}
	
	public IDrawer getDrawer(DrawableType type) {
		switch (type) {
		case SERVER: return serverDrawer;
		case RACK: return rackDrawer;
		case DATA_CENTER: return dcDrawer;
		default: return null;
		}
	}
}