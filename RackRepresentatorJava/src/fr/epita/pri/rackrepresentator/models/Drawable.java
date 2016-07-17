package fr.epita.pri.rackrepresentator.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {
	protected Drawable parent;
	protected String name;
	protected String description;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean shouldDraw;
	protected boolean shouldDrawChildren;
	protected List<Drawable> children;
	
	public Drawable(String name, String description) {
		this.name = name;
		this.description = description;
		shouldDraw = true;
		shouldDrawChildren = true;
		children = new ArrayList<>();
	}
	
	public void addSon(Drawable son) {
		son.setParent(this);
		children.add(son);
	}

	public void removeSon(Drawable son) {
		children.remove(son);
	}
	

	public List<Drawable> getChildren() {
		return children;
	}

	public void setCenters(List<Drawable> sons) {
		this.children = sons;
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
		return parent.getChildren();
	}
	
	public void setParent(Drawable parent) {
		this.parent = parent;
	}
	
	public Drawable findByName(String name) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getName().equals(name)) {
				return children.get(i);
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
		for (int i = 0; i < children.size(); i++) {
			Drawable d = children.get(i);
			
			found = d.findDrawableWithPosition(x, y);
			if (found != null) {
				break;
			}
			found = null;
		}
		
		if (found == null && isInPosition(x, y) && shouldDraw) 
			found = this;
		
		return found;
	}
	
	public abstract boolean hasChildrenToShow();
}