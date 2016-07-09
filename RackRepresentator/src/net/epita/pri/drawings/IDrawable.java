package net.epita.pri.drawings;

public interface IDrawable {
	public void register(IDrawer drawer);
	public void unregister(IDrawer drawer);
}