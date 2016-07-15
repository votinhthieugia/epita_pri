package fr.epita.pri.rackrepresentator.models;

import java.util.ArrayList;
import java.util.List;

public class DataSystem extends Drawable {
	List<DataCenter> centers;
	
	public DataSystem(String name, String description) {
		super(name, description);
		centers = new ArrayList<DataCenter>();
	}

	public void addCenter(DataCenter center) {
		centers.add(center);
	}
	
	public void removeCenter(DataCenter center) {
		centers.remove(center);
	}
	
	public DataCenter findByName(String name) {
		for (int i = 0; i < centers.size(); i++) {
			if (centers.get(i).getName().equals(name)) {
				return centers.get(i);
			}
		}
		return null;
	}

	public List<DataCenter> getCenters() {
		return centers;
	}

	public void setCenters(List<DataCenter> centers) {
		this.centers = centers;
	}
}