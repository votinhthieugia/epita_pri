package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;

public class Drawer {
	public enum DrawableType {
		SERVER,
		RACK,
		DATA_CENTER,
		SYSTEM
	};
	
	private ServerDrawer serverDrawer;
	private RackDrawer rackDrawer;
	private DataCenterDrawer dcDrawer;
	private SystemDrawer systemDrawer;
	
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
		systemDrawer = new SystemDrawer();
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
		else if (drawable instanceof DataSystem) return systemDrawer;
		return null;
	}
	
	public IDrawer getDrawer(DrawableType type) {
		switch (type) {
		case SERVER: return serverDrawer;
		case RACK: return rackDrawer;
		case DATA_CENTER: return dcDrawer;
		case SYSTEM: return systemDrawer;
		default: return null;
		}
	}
}