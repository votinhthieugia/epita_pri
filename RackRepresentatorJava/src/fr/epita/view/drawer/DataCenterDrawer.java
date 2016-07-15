package fr.epita.view.drawer;

import java.awt.Graphics;
import java.util.List;

import fr.epita.models.DataCenter;
import fr.epita.models.Drawable;
import fr.epita.models.Rack;

public class DataCenterDrawer implements IDrawer {
	
	private final int RACK_PADDING_Y = 50;
	private final int RACK_PADDING_X = 100;
	
	@Override
	public void draw(Graphics g, Drawable drawable) {			
		
		DataCenter dc = (DataCenter)drawable;
		if (dc.isShouldDraw()) {
			
		}
		
		if (dc.isShouldDrawChildren()) {
			List<Rack> racks = dc.getRacks();
			IDrawer rackDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.RACK);
			int i = 0;
			for (Rack rack : racks) {
				rack.setWidth(RackDrawer.RACK_COLUMNS*RackDrawer.SERVER_UNIT_PIXELS);
				rack.setHeight(RackDrawer.RACK_ROWS*RackDrawer.SERVER_UNIT_PIXELS);				
				rack.setX((RACK_PADDING_X+rack.getWidth())*i++ + RACK_PADDING_X);
				rack.setY(RACK_PADDING_Y);
				rackDrawer.draw(g, rack);
			}
		}
	}
}