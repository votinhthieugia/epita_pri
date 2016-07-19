package fr.epita.pri.rackrepresentator.models;

public class DataCenter extends Drawable {
	public DataCenter() {}
	
	public DataCenter(String name, String description, int index) {
		super(name, description);
		this.index = index;

		//as it is SO FAR the first to appear...
		shouldDrawChildren = false;
	}
	
	@Override
	public boolean hasChildrenToShow() {
		return true;
	}
}