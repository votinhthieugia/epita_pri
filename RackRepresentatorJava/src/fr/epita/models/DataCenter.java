package fr.epita.models;

import java.util.ArrayList;
import java.util.List;

public class DataCenter extends Drawable {
	private int index;
	private List<Rack> racks;
	
	public DataCenter(String name, String description, int index) {
		super(name, description);
		this.index = index;
		racks = new ArrayList<Rack>();
	}
	
	public void addRack(Rack rack) {
		racks.add(rack);
	}
	
	public void removeRack(Rack rack) {
		racks.remove(rack);
	}
	
	public Rack find(int rackIndex) {
		for (int i = 0; i < racks.size(); i++) {
			Rack rack = racks.get(i);
			if (rack.getIndex() == rackIndex) {
				return rack;
			}
		}
		
		return null;
	}
	
	public Rack findByIndex(int rackIndex) {
		return rackIndex < racks.size() ? racks.get(rackIndex) : null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Rack> getRacks() {
		return racks;
	}

	public void setRacks(List<Rack> racks) {
		this.racks = racks;
	}
}