package fr.epita.view.drawer;

import java.awt.Color;
import java.awt.Graphics;

import fr.epita.models.Drawable;
import fr.epita.models.Server;
import fr.epita.models.ServerProp;
import fr.epita.models.ServerState;
import fr.epita.view.drawer.primitives.Primitives;

public class ServerDrawer implements IDrawer {

	static final int TEXT_PADDING_Y = 13;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		Server server = (Server)drawable;

		Primitives.fillRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), getServerColor(server));
		Primitives.drawText(g, server.getX(), server.getY() + TEXT_PADDING_Y, server.getName() , Color.YELLOW);
	}
	
	private Color getServerColor(Server server) {
		Color color = null;
		int prop = server.getProp().getPropValue();
		switch (prop) {
		case ServerProp.NonWindowsRelatedEquipment: break;
		case ServerProp.ComputerRoomInfrastructure: break;
		case ServerProp.WindowsRelatedEquipment: break;
		default: break;
		}
		
		int state = server.getState().getStateValue();
		switch (state) {
		case ServerState.Free: color = Color.BLUE; break;
		case ServerState.InUse: color = Color.GREEN; break;
		case ServerState.PowerOffToRemove: color = Color.RED; break;
		case ServerState.PlanToRemove: color = Color.ORANGE; break;
		default: break;
		}
		
		return color;
	}

}