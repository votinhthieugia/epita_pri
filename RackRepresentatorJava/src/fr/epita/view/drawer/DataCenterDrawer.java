package fr.epita.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.models.DataCenter;
import fr.epita.models.Drawable;
import fr.epita.models.Rack;

public class DataCenterDrawer implements IDrawer {

	@Override
	public void draw(Graphics g, Drawable drawable) {
		DataCenter dc = (DataCenter)drawable;
		if (dc.isShouldDraw()) {
			
		}
		
		if (dc.isShouldDrawChildren()) {
			List<Rack> racks = dc.getRacks();
			IDrawer rackDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.RACK);
			for (Rack rack : racks) rackDrawer.draw(g, rack);
		}
	}
}