package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;
import fr.epita.pri.rackrepresentator.view.extras.Utils;

public class DataCenterDrawer implements IDrawer {

	private final int RACK_PADDING_Y = 50;
	private final int RACK_PADDING_X = 100;

	@Override
	public void draw(Graphics g, Drawable drawable) {

		DataCenter dc = (DataCenter) drawable;
		if (dc.isShouldDraw()) {
			Primitives.fillRoundRectangle(g, dc.getX(), dc.getY(), dc.getWidth(), dc.getHeight(), 20, 20, Color.YELLOW);
			Utils.writeCenteredNameInDrawable(g, dc, Color.BLACK);
		}

		if (dc.isShouldDrawChildren()) {
			List<Drawable> racks = dc.getChildren();
			IDrawer rackDrawer = Drawer.Instance().getDrawer(Drawer.DrawableType.RACK);
			for (int i = 0; i < racks.size(); i++) {
				Rack rack = (Rack) racks.get(i);

				rack.setWidth(RackDrawer.RACK_COLUMNS * RackDrawer.SERVER_UNIT_PIXELS);
				rack.setHeight(RackDrawer.RACK_ROWS * RackDrawer.SERVER_UNIT_PIXELS);
				rack.setX((RACK_PADDING_X + rack.getWidth()) * i + RACK_PADDING_X);
				rack.setY(RACK_PADDING_Y);
				rackDrawer.draw(g, rack);
			}
		}
	}
}