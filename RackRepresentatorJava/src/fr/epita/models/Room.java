package fr.epita.models;

import java.util.ArrayList;
import java.util.List;

public class Room extends Drawable {
	private int dataCenterIndex;
	private int index;
	private List<Rack> racks;
	
	public Room(String name, String description, int dataCenterIndex, int index) {
		super(name, description);
		this.dataCenterIndex = dataCenterIndex;
		this.index = index;
		this.racks = new ArrayList<Rack>();
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

	public int getDataCenterIndex() {
		return dataCenterIndex;
	}

	public void setDataCenterIndex(int dataCenterIndex) {
		this.dataCenterIndex = dataCenterIndex;
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
