package fr.epita.view.extras;

import java.awt.Graphics;

public interface IDisplayMore {
	public void init(Object data, int x, int y);
	public void draw(Graphics g);
	public void show();
	public void hide();
}
