package fr.epita.view.drawer;

import java.util.List;

import fr.epita.models.DataCenter;
import fr.epita.models.Drawable;
import fr.epita.models.Room;

public class DataCenterDrawer implements IDrawer {

	@Override
	public void draw(Drawable drawable) {
		DataCenter dc = (DataCenter)drawable;
		List<Room> rooms = dc.getRooms();
		IDrawer roomDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.ROOM);
		for (Room room : rooms) roomDrawer.draw(room); 
	}
}