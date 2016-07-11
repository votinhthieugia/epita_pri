package fr.epita.view.drawer;

import fr.epita.models.DataCenter;
import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Room;
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
	private RoomDrawer roomDrawer;
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
		roomDrawer = new RoomDrawer();
		dcDrawer = new DataCenterDrawer();
	}
	
	public IDrawer draw(Drawable drawable) {
		IDrawer drawer = getDrawer(drawable);
		drawer.draw(drawable);
		return drawer;
	}
	
	public IDrawer getDrawer(Drawable drawable) {
		if (drawable instanceof DataCenter) return dcDrawer;
		else if (drawable instanceof Room) return roomDrawer;
		else if (drawable instanceof Rack) return rackDrawer;
		else if (drawable instanceof Server) return serverDrawer;
		return null;
	}
	
	public IDrawer getDrawer(DrawableType type) {
		switch (type) {
		case SERVER: return serverDrawer;
		case RACK: return rackDrawer;
		case ROOM: return roomDrawer;
		case DATA_CENTER: return dcDrawer;
		default: return null;
		}
	}
}