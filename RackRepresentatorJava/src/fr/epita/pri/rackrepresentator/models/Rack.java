package fr.epita.pri.rackrepresentator.models;

public class Rack extends Drawable {
	private int index;
	private int dataCenterIndex;
	
	public Rack(String name, String description, int dataCenterIndex, int index) {
		super(name, description);
		
		this.index = index;
		this.dataCenterIndex = dataCenterIndex;
	}
	
	public Server find(int index) {
		for (int i = 0; i < children.size(); i++) {
			Server server = (Server) children.get(i);
			if (server.getIndex() == index) {
				return server;
			}
		}
		
		return null;
	}
	
	public Server findByIndex(int index) {
		return (Server) (index < children.size() ? children.get(index) : null);
	}
	
	public Server findByLowHigh(int low, int high) {
		for (int i = 0; i < children.size(); i++) {
			Server server = (Server) children.get(i);
			if (server.getLow() == low && server.getHigh() == high) {
				return server;
			}
		}
		
		return null;
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
	
	public int getNumServers() {
		int count = 0;
		for (Drawable s : children) count += ((Server) s).count();
		return count;
	}
}