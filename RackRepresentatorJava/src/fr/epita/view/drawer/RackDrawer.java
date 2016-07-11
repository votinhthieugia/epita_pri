package fr.epita.view.drawer;

import java.util.List;

import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Server;

public class RackDrawer implements IDrawer {

	@Override
	public void draw(Drawable drawable) {
		Rack rack = (Rack)drawable;
		List<Server> servers = rack.getServers();
		IDrawer serverDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.SERVER);
		for (Server server : servers) serverDrawer.draw(server);
	}
}