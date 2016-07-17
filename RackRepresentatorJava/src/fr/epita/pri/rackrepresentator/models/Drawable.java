package fr.epita.pri.rackrepresentator.models;

import java.util.ArrayList;
import java.util.List;

public class Drawable {
	protected Drawable parent;
	protected String name;
	protected String description;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean shouldDraw;
	protected boolean shouldDrawChildren;
	protected List<Drawable> sons;
	
	public Drawable(String name, String description) {
		this.name = name;
		this.description = description;
		shouldDraw = true;
		shouldDrawChildren = true;
		sons = new ArrayList<>();
	}
	
	public void addSon(Drawable son) {
		son.setParent(this);
		sons.add(son);
	}

	public void removeSon(Drawable son) {
		sons.remove(son);
	}
	

	public List<Drawable> getSons() {
		return sons;
	}

	public void setCenters(List<Drawable> sons) {
		this.sons = sons;
	}
	
	public void position(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isShouldDraw() {
		return shouldDraw;
	}

	public void setShouldDraw(boolean shouldDraw) {
		this.shouldDraw = shouldDraw;
	}

	public boolean isShouldDrawChildren() {
		return shouldDrawChildren;
	}

	public void setShouldDrawChildren(boolean shouldDrawChildren) {
		this.shouldDrawChildren = shouldDrawChildren;
	}
	
	public Drawable getParent() {
		return parent;
	}

	public List<Drawable> getBrothers() {
		return parent.getSons();
	}
	
	public void setParent(Drawable parent) {
		this.parent = parent;
	}
	
	public Drawable findByName(String name) {
		for (int i = 0; i < sons.size(); i++) {
			if (sons.get(i).getName().equals(name)) {
				return sons.get(i);
			}
		}
		return null;
	}
	
	public boolean isInPosition(int x, int y) {
		return this.x <= x && x <= this.x + width &&
				this.y <= y && y <= this.y + height;
	}

	public Drawable findDrawableWithPosition(int x, int y) {
		Drawable found = null;
		for (int i = 0; i < sons.size(); i++) {
			Drawable d = sons.get(i);
			
			found = d.findDrawableWithPosition(x, y);
			if (found != null) break;
			found = null;
		}
		if (found == null && isInPosition(x, y)) found = this;
		return found;
	}
	
//	public Drawable findDrawableWithPosition(int x, int y) {
//		if (isInPosition(x, y)) return this;
//		return null;
//	}
}