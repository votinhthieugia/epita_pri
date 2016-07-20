package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Server;
import fr.epita.pri.rackrepresentator.models.ServerProp;
import fr.epita.pri.rackrepresentator.models.ServerState;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;
import fr.epita.pri.rackrepresentator.view.extras.Utils;

public class ServerDrawer implements IDrawer {

	private static final int TEXT_PADDING_VERTICAL_X = 13;
	private static final int ROUND_VALUE = 20;

	@Override
	public void draw(Graphics g, Drawable drawable) {
		Server server = (Server) drawable;

		Primitives.fillRoundRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), ROUND_VALUE, ROUND_VALUE, getServerColor(server));
		Primitives.drawRoundRectangle(g, server.getX(), server.getY(), server.getWidth(), server.getHeight(), ROUND_VALUE, ROUND_VALUE, Color.gray);

		if (server.getWidth() == RackDrawer.BLADE_SERVER_WIDTH) {

			Graphics2D g2 = (Graphics2D) g.create();
//			g2.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
			Primitives.drawTextRotate(g2, server.getX()+TEXT_PADDING_VERTICAL_X, server.getY() + server.getHeight(), -90, server.getModelBrand(),
					Color.black);
		} else
			Utils.writeCenteredNameInDrawable(g, server, Color.BLACK);
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