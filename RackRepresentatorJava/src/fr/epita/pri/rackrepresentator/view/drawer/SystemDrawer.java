package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;

public class SystemDrawer implements IDrawer {

	private static int DATA_CENTER_WIDTH = 200;
	private static int DATA_CENTER_PADDING_SPACE = 100;
	private static int DATA_CENTER_X = 100;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {
		DataSystem system = (DataSystem)drawable;
		if (system.isShouldDraw()) {
			
		}
		
		if (system.isShouldDrawChildren()) {
			IDrawer dataCenterDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.DATA_CENTER);
			List<Drawable> centers = system.getChildren();
			
			int i=0;
			for (Drawable center : centers){				
				center.setWidth(DATA_CENTER_WIDTH);
				center.setHeight(DATA_CENTER_WIDTH);				
				center.setX((DATA_CENTER_PADDING_SPACE + DATA_CENTER_WIDTH)*i++ + DATA_CENTER_PADDING_SPACE);
				center.setY(DATA_CENTER_X);
				
				dataCenterDrawer.draw(g, center);
			}
		}
	}
}