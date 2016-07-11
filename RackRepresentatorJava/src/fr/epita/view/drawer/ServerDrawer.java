package fr.epita.view.drawer;

import fr.epita.models.Drawable;
import fr.epita.models.Server;

public class ServerDrawer implements IDrawer {

	@Override
	public void draw(Drawable drawable) {
		Server server = (Server)drawable;
	}
}