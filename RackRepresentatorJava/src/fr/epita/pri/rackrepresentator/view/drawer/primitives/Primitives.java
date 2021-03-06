package fr.epita.pri.rackrepresentator.view.drawer.primitives;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Primitives {
	
	public static void drawRectangle(Graphics g, int x, int y, int width, int height, Color color) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.drawRect(x, y, width, height);
	}
	
	public static void drawRectangle(Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.drawRect(x, y, width, height);
	}
	
	public static void drawRectangleWithRuler(Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.drawRect(x, y, width, height);
		Ruler.draw(g2d, x, y, x, height+y, true);
//		Ruler.draw(g2d, x, y+height, x+width, y+height);
	}
	
	public static void fillRectangle(Graphics g, int x, int y, int width, int height, Color color) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height);
	}
	
	public static void drawRoundRectangle(Graphics g, int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	public static void fillRoundRectangle(Graphics g, int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	public static void drawText(Graphics g, int x, int y, String text) {
		drawText(g, x, y, text, Color.BLACK);
	}
	
	public static void drawText(Graphics g, int x, int y, String text, Color color) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.drawString(text, x, y);
	}
	
	public static void drawTextRotate(Graphics2D g2d, int x, int y, int angle, String text, Color color) 
	{    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.setColor(color);
	    g2d.drawString(text,0,0);	    
	} 
}