package fr.epita.pri.rackrepresentator.models;

public class DataSystem extends Drawable {
	
	public DataSystem(String name, String description) {
		super(name, description);
	}
	
	@Override
	public boolean hasChildrenToShow() {
		return true;
	}
}