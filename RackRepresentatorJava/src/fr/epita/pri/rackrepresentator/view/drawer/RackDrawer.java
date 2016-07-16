package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;

public class RackDrawer implements IDrawer {

	public static final int SERVER_UNIT_PIXELS = 25;
	public static final int RACK_ROWS = 42;
	public static final int RACK_COLUMNS = 8;
	
	private static final int BLADE_SERVER_WIDTH = SERVER_UNIT_PIXELS;
	private static final int TEXT_PADDING_Y = 13;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		
		Rack rack = (Rack)drawable;

		if (rack.isShouldDraw()) {
			Primitives.drawRectangleWithRuler(g, rack.getX(), rack.getY(), rack.getWidth(), rack.getHeight());
			Primitives.drawText(g, rack.getX(), rack.getY() - TEXT_PADDING_Y, rack.getName() , Color.BLACK);
		}		
		
		if (rack.isShouldDrawChildren()) {			
			List<Server> servers = rack.getServers();
			IDrawer serverDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.SERVER);
			for (Server server : servers){ 
				
				//is blade server?
				if(server.count() > 1){
					
					int i = 0;
					while(server.hasNext()){
						Server innerServer = server.next();
						innerServer.setWidth(BLADE_SERVER_WIDTH);
						innerServer.setHeight(SERVER_UNIT_PIXELS*server.getNumU());
						
						innerServer.setY(rack.getY() + (RACK_ROWS-server.getLow())*SERVER_UNIT_PIXELS-(SERVER_UNIT_PIXELS*server.getNumU()));
						innerServer.setX(rack.getX()+(i++*BLADE_SERVER_WIDTH));
						
						serverDrawer.draw(g, innerServer);
					}
					
					server.resetIterator();
					
				}else{
					server.setY(rack.getY() + (RACK_ROWS-server.getLow())*SERVER_UNIT_PIXELS-(SERVER_UNIT_PIXELS*server.getNumU()));
					server.setX(rack.getX());
					server.setWidth(RACK_COLUMNS*SERVER_UNIT_PIXELS);
					server.setHeight(SERVER_UNIT_PIXELS*server.getNumU());
					
					serverDrawer.draw(g, server);
				}

				
			}
		}
	}
}