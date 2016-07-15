package fr.epita.view.drawer.primitives;

import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.epita.view.drawer.RackDrawer;

public class Ruler {

	int x1, x2, y1, y2;
	private final int TICK_DIST = RackDrawer.SERVER_UNIT_PIXELS;
	private final int  TICK_SIZE = 3;
	private final int  STRING_PADDING = 19;
	double len;
	double dx, dy;

	public Ruler(int x1, int y1, int x2, int y2) {

		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;

		dx = x2 - x1;
		dy = y2 - y1;
		len = Math.sqrt(dx * dx + dy * dy);
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

//		g2d.drawLine(x1, y1, x2, y2);

		if(x1-x2 == 0)
		{
			for (int i = y1, pos = (int) ((len + y1)/TICK_DIST)+1; i <= len + y1; i += TICK_DIST, pos--){
				g2d.drawLine(x1 - TICK_SIZE, i, x2 + TICK_SIZE, i);
				g2d.drawString("" + pos, x1 - STRING_PADDING, i);
			}
		}else
		{
			for (double i = x1; i < len + x1; i += TICK_DIST)
				g2d.drawLine((int) i, y1 - TICK_SIZE, (int) i, y2 + TICK_SIZE);	
		}
	}
}