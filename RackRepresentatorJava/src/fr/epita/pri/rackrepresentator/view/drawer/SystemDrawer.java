package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;

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