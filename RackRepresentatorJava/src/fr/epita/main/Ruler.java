package fr.epita.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ruler {

	int x1, x2, y1, y2;
	private final double TICK_DIST = 20;
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

		g2d.drawLine(x1, y1, x2, y2);

		if(x1-x2 == 0)
		{
			for (double i = y1; i < len + y1; i += TICK_DIST)
				g2d.drawLine(x1 - 3, (int) i, x2 + 3, (int) i);
		}else
		{
			for (double i = x1; i < len + x1; i += TICK_DIST)
				g2d.drawLine((int) i, y1 - 3, (int) i, y2 + 3);	
		}
	}
}