package fr.epita.view.extras;

import java.awt.Color;
import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;

public class Utils {
	
	public static void writeCenteredNameInDrawable(Graphics g, Drawable d, Color color) {
		Primitives.drawText(g, d.getX()+(d.getWidth()/2 - g.getFontMetrics().stringWidth(d.getName())/2), d.getY()+(d.getHeight()/2), d.getName(), color);
	}
}