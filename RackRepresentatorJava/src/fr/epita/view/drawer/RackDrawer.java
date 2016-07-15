package fr.epita.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Server;
import fr.epita.view.drawer.primitives.Primitives;

public class RackDrawer implements IDrawer {

	public static final int SERVER_UNIT_PIXELS = 25;
	public static final int RACK_ROWS = 42;
	public static final int RACK_COLUMNS = 8;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		
		Rack rack = (Rack)drawable;

		if (rack.isShouldDraw()) {
			Primitives.drawRectangleWithRuler(g, rack.getX(), rack.getY(), rack.getWidth(), rack.getHeight());
		}		
		
		if (rack.isShouldDrawChildren()) {			
			List<Server> servers = rack.getServers();
			IDrawer serverDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.SERVER);
			for (Server server : servers){ 
				server.setY(rack.getY() + (RACK_ROWS-server.getLow())*SERVER_UNIT_PIXELS-(SERVER_UNIT_PIXELS*server.getNumU()));
				server.setX(rack.getX());
				server.setWidth(RACK_COLUMNS*SERVER_UNIT_PIXELS);
				server.setHeight(SERVER_UNIT_PIXELS*server.getNumU());
				serverDrawer.draw(g, server);
			}
		}
	}
}