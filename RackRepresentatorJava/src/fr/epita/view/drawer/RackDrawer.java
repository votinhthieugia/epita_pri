package fr.epita.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Server;
import fr.epita.view.drawer.primitives.Primitives;

public class RackDrawer implements IDrawer {

	public static final int SERVER_UNIT_PIXELS = 15;
	public static final int RACK_ROWS = 42;
	public static final int RACK_COLUMNS = 8;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		
		Rack rack = (Rack)drawable;
		
		if (rack.isShouldDraw()) {
			Primitives.drawRectangleWithRuler(g, 0+100, 0, RACK_COLUMNS*SERVER_UNIT_PIXELS, RACK_ROWS*SERVER_UNIT_PIXELS);
		}
		
		
		if (rack.isShouldDrawChildren()) {			
			List<Server> servers = rack.getServers();
			IDrawer serverDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.SERVER);
			for (Server server : servers) serverDrawer.draw(g, server);
		}
	}
}