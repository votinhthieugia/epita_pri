package fr.epita.pri.rackrepresentator.view.extras;

import java.awt.Color;
import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.Server;
import fr.epita.pri.rackrepresentator.view.drawer.primitives.Primitives;

public class ServerDisplayMore implements IDisplayMore {
	private static final int LINE_OFFSET = 20;
	private static final int LINE_HEIGHT = 23;
	private Server server;
	private int x;
	private int y;
	private boolean shouldDraw;

	@Override
	public void init(Object data, int x, int y) {
		if (data instanceof Server) server = (Server)data;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		if (shouldDraw && server != null) {
			Primitives.fillRoundRectangle(g, x, y, 400, 200, 20, 20, Color.lightGray);
			int xPosition = x + 5;
			int yPosition = y + LINE_OFFSET;
			yPosition = drawElement(g, xPosition, yPosition, "Server:" + server.getName());
			yPosition = drawElement(g, xPosition, yPosition, "Rack:" + server.getParent().getName());
			yPosition = drawElement(g, xPosition, yPosition, "Center:" + server.getParent().getParent().getName());
			yPosition = drawElement(g, xPosition, yPosition, "Serial:" + server.getSerialNumber());
			yPosition = drawElement(g, xPosition, yPosition, "Description:" + server.getDescription());
			yPosition = drawElement(g, xPosition, yPosition, "Model Brand:" + server.getModelBrand() + ", " + server.getModelNature());
			yPosition = drawElement(g, xPosition, yPosition, "Model:" + server.getModel());
			yPosition = drawElement(g, xPosition, yPosition, "Category:" + server.getCategory());
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
	public void hide() {
		shouldDraw = false;
	}

	@Override
	public void show() {
		shouldDraw = true;
	}
}