package fr.epita.pri.rackrepresentator.view.drawer.primitives;

import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.epita.pri.rackrepresentator.view.drawer.RackDrawer;

public class Ruler {
	
	private static final int TICK_DIST = RackDrawer.SERVER_UNIT_PIXELS;
	private static final int  TICK_SIZE = 3;
	private static final int  STRING_PADDING = 19;

	public static void draw(Graphics g, int x1, int y1, int x2, int y2, boolean writeNumbers) {

		Graphics2D g2d = (Graphics2D) g.create();

		int dx = x2 - x1;
		int dy = y2 - y1;
		int len = (int) Math.sqrt(dx * dx + dy * dy);
		
		if(x1-x2 == 0)
		{
			for (int i = y1 + TICK_DIST, pos = (int) (len /TICK_DIST); i <= len + y1; i += TICK_DIST, pos--){
				g2d.drawLine(x1 - TICK_SIZE, i, x2 + TICK_SIZE, i);
				if(writeNumbers)
					g2d.drawString("" + pos, x1 - STRING_PADDING, i);
			}
		}else
		{
			for (double i = x1; i < len + x1; i += TICK_DIST)
				g2d.drawLine((int) i, y1 - TICK_SIZE, (int) i, y2 + TICK_SIZE);	
		}
	}
}