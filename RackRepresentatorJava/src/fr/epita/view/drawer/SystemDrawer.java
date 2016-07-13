package fr.epita.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.models.DataCenter;
import fr.epita.models.DataSystem;
import fr.epita.models.Drawable;

public class SystemDrawer implements IDrawer {

	@Override
	public void draw(Graphics g, Drawable drawable) {
		DataSystem system = (DataSystem)drawable;
		if (system.isShouldDraw()) {
			
		}
		
		if (system.isShouldDrawChildren()) {
			IDrawer dataCenterDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.DATA_CENTER);
			List<DataCenter> centers = system.getCenters();
			for (DataCenter center : centers) dataCenterDrawer.draw(g, center);
		}
	}
}