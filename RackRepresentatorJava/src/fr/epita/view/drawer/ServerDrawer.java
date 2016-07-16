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

		Primitives.drawRoundRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), 20, 20, Color.gray);
		Primitives.fillRoundRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), 20, 20, getServerColor(server));
		Primitives.drawText(g, server.getX(), server.getY() + TEXT_PADDING_Y, server.getName() , Color.BLACK);
	}
	
	private Color getServerColor(Server server) {
		Color color = Color.green;
		int prop = server.getProp().getPropValue();
		switch (prop) {
		case ServerProp.NonWindowsRelatedEquipment: break;
		case ServerProp.ComputerRoomInfrastructure: break;
		case ServerProp.WindowsRelatedEquipment: break;
		default: break;
		}
		
		int state = server.getState().getStateValue();
		switch (state) {
		case ServerState.Free: color = Color.WHITE; break;
		case ServerState.InUse: color = Color.CYAN; break;
		case ServerState.PlanToRemove: color = Color.ORANGE; break;
		case ServerState.PowerOffToRemove: color = Color.RED; break;
		default: break;
		}
		
		return color;
	}

}