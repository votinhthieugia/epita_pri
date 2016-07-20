package fr.epita.pri.rackrepresentator.view.extras;

import java.awt.Color;
import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Server;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;

public class Utils {
	
	public static void writeCenteredNameInDrawable(Graphics g, Drawable d, Color color) {
		String text = d.getName();
		if (d instanceof Server) text = ((Server)d).getModelBrand();
		Primitives.drawText(g, d.getX()+(d.getWidth()/2 - g.getFontMetrics().stringWidth(text)/2), d.getY()+(d.getHeight()/2), text, color);
	}
}
