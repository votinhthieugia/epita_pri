package fr.epita.pri.rackrepresentator.models;

public class DataCenter extends Drawable {
	private int index;
	
	public DataCenter(String name, String description, int index) {
		super(name, description);
		this.index = index;

		//as it is SO FAR the first to appear...
		shouldDrawChildren = false;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}