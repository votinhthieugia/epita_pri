package net.epita.pri.drawings;

public interface IDrawer {
	public void add(IDrawable drawable);
	public void remove(IDrawable drawable);
	public void draw(IDrawable drawable);
	public void handleEvent(int eventId, Object data);
}
