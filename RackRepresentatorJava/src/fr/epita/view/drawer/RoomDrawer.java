package fr.epita.view.drawer;

import java.util.List;

import fr.epita.models.Drawable;
import fr.epita.models.Rack;
import fr.epita.models.Room;

public class RoomDrawer implements IDrawer {

	@Override
	public void draw(Drawable drawable) {
		Room room = (Room)drawable;
		List<Rack> racks = room.getRacks();
		IDrawer rackDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.RACK);
		for (Rack rack : racks) rackDrawer.draw(rack);
	}
}