package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Ruler;

public class RackDrawer implements IDrawer {

	public static final int SERVER_UNIT_PIXELS = 25;
	
	static final int RACK_ROWS = 42;
	static final int RACK_COLUMNS = 8;	
	static final int BLADE_SERVER_WIDTH = SERVER_UNIT_PIXELS;
	
	private static final int TEXT_PADDING_Y = 13;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		
		Rack rack = (Rack)drawable;

		if (rack.isShouldDraw()) {
			Primitives.drawRectangleWithRuler(g, rack.getX(), rack.getY(), rack.getWidth(), rack.getHeight());
			Primitives.drawText(g, rack.getX(), rack.getY() - TEXT_PADDING_Y, rack.getName() , Color.BLACK);
		}		
		
		if (rack.isShouldDrawChildren()) {			
			List<Drawable> servers = rack.getChildren();
			IDrawer serverDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.SERVER);

			for (int i = 0; i < servers.size(); i++) {
				Server server = (Server) servers.get(i); 
				
				//is blade server?
				if(server.count() > 1){
					
					int y = rack.getY() + (RACK_ROWS-server.getLow())*SERVER_UNIT_PIXELS-(SERVER_UNIT_PIXELS*server.getNumU());
					int h = SERVER_UNIT_PIXELS*server.getNumU();
					int padding_x = 20;
					int padding_y = 10;
					
					Primitives.fillRectangle(g, rack.getX()- padding_x , y, padding_x, h - padding_y, Color.WHITE);
					Primitives.fillRectangle(g, rack.getX() , y, rack.getWidth(), h, Color.LIGHT_GRAY);
					Primitives.drawRectangle(g, rack.getX() , y, rack.getWidth(), h, Color.BLACK);
					Ruler.draw(g, rack.getX(), y+h/2, rack.getX() + rack.getWidth(), y+h/2, false);
					g.drawLine(rack.getX(), y+h/2, rack.getX() + rack.getWidth(), y+h/2);
					
					int j = 0;
					while(server.hasNext()){
						
						Server innerServer = server.next();
						innerServer.setWidth(BLADE_SERVER_WIDTH);
						innerServer.setHeight(h/2);
						innerServer.setX(rack.getX()+((j%RACK_COLUMNS)*BLADE_SERVER_WIDTH));
						
						if(j < 8){
							innerServer.setY(y);
						}else{
							innerServer.setY(y+innerServer.getWidth());
							
						}
						
						j++;
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