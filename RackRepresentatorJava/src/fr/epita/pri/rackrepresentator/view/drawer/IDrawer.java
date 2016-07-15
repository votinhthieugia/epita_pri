package fr.epita.pri.rackrepresentator.view.drawer;

import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.Drawable;

public interface IDrawer {
	public void draw(Graphics g, Drawable drawable);
}
