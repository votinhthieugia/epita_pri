package fr.epita.view.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.epita.models.Drawable;
import fr.epita.models.Server;
import fr.epita.models.ServerProp;
import fr.epita.models.ServerState;
import fr.epita.view.drawer.primitives.Primitives;

public class ServerDrawer implements IDrawer {

	private static final int TEXT_PADDING_HORIZONTAL_Y = 13;
	private static final int TEXT_PADDING_VERTICAL_X = 13;

	@Override
	public void draw(Graphics g, Drawable drawable) {
		Server server = (Server) drawable;

		Primitives.fillRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(),
				Color.BLACK);
		Primitives.drawRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), Color.WHITE);

		if (server.getWidth() < server.getHeight()) {

			Graphics2D g2 = (Graphics2D) g.create();
			Primitives.drawTextRotate(g2, server.getX()+TEXT_PADDING_VERTICAL_X, server.getY() + server.getHeight(), -90, server.getName(),
					Color.YELLOW);
		} else
			Primitives.drawText(g, server.getX(), server.getY() + TEXT_PADDING_HORIZONTAL_Y, server.getName(), Color.YELLOW);
	}

	private Color getServerColor(Server server) {
		Color color = null;
		int prop = server.getProp().getPropValue();
		switch (prop) {
		case ServerProp.NonWindowsRelatedEquipment:
			break;
		case ServerProp.ComputerRoomInfrastructure:
			break;
		case ServerProp.WindowsRelatedEquipment:
			break;
		default:
			break;
		}

		int state = server.getState().getStateValue();
		switch (state) {
		case ServerState.Free:
			color = Color.BLUE;
			break;
		case ServerState.InUse:
			color = Color.GREEN;
			break;
		case ServerState.PowerOffToRemove:
			color = Color.RED;
			break;
		case ServerState.PlanToRemove:
			color = Color.ORANGE;
			break;
		default:
			break;
		}

		return color;
	}

}