package fr.epita.view.extras;

import java.awt.Color;
import java.awt.Graphics;

import fr.epita.models.Rack;
import fr.epita.view.drawer.primitives.Primitives;

public class RackDisplayMore implements IDisplayMore {
	private static final int LINE_OFFSET = 20;
	private static final int LINE_HEIGHT = 23;
	
	private Rack rack;
	private int x;
	private int y;
	private boolean shouldDraw;

	@Override
	public void init(Object data, int x, int y) {
		if (data instanceof Rack) rack = (Rack)data;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		if (shouldDraw && rack != null) {
			Primitives.fillRoundRectangle(g, x, y, 400, 100, 20, 20, Color.lightGray);
			int xPosition = x + 5;
			int yPosition = y + LINE_OFFSET;
			yPosition = drawElement(g, xPosition, yPosition, "Rack:" + rack.getName());
			yPosition = drawElement(g, xPosition, yPosition, "Center:" + rack.getParent().getName());
			yPosition = drawElement(g, xPosition, yPosition, "Num Servers:" + rack.getNumServers());
		}
	}
	
	private int drawElement(Graphics g, int x, int y, String text) {
		return drawElement(g, x, y, text, Color.YELLOW);
	}

	private int drawElement(Graphics g, int x, int y, String text, Color color) {
		Primitives.drawText(g, x, y, text, color);
		return y + LINE_HEIGHT;
	}

	@Override
	public void show() {
		shouldDraw = true;
	}

	@Override
	public void hide() {
		shouldDraw = false;
	}
}