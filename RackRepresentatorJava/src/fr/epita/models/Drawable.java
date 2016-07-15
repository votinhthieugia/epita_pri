package fr.epita.models;

public class Drawable {
	protected String name;
	protected String description;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean shouldDraw;
	protected boolean shouldDrawChildren;
	
	public Drawable(String name, String description) {
		this.name = name;
		this.description = description;
		shouldDraw = true;
		shouldDrawChildren = true;
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
}