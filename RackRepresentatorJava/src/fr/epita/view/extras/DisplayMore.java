package fr.epita.view.extras;

import java.awt.Graphics;

import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;

public class DisplayMore {
	private IDisplayMore serverDisplayMore;
	private IDisplayMore rackDisplayMore;
	
	public DisplayMore() {
		serverDisplayMore = new ServerDisplayMore();
		rackDisplayMore = new RackDisplayMore();
	}
	
	public void show(Drawable drawable, int x, int y) {
		hideAll();
		
		IDisplayMore instance = null;
		
		if (drawable instanceof Server) instance = serverDisplayMore;
		else if (drawable instanceof Rack) instance = rackDisplayMore;
		
		if (instance != null) {
			instance.init(drawable, x, y);
			instance.show();
		}
	}
	
	public void draw(Graphics g) {
		serverDisplayMore.draw(g);
		rackDisplayMore.draw(g);
	}
	
	public void hide(Drawable drawable) {
		if (drawable instanceof Server) serverDisplayMore.hide();
		else if (drawable instanceof Rack) rackDisplayMore.hide();
	}
	
	public void hideAll() {
		serverDisplayMore.hide();
		rackDisplayMore.hide();
	}
}