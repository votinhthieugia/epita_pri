package fr.epita.pri.rackrepresentator.models;

import java.util.Iterator;

public class Server extends Drawable implements Iterator<Server> {
	private ServerState state;
	private ServerProp prop;
	private ServerScope scope;
	private int rackIndex;
	private int index;
	private int low;
	private int high;
	private int startsAt;
	private int numU;
	private int criticality;
	private String modelBrand;
	private String modelNature;
	private String category;
	private String model;
	private String xReference;
	private String serialNumber;
	private Server next;
	private Server iterator;
	
	public Server(String name, String description, int index) {
		super(name, description);
		this.index = index;
		this.iterator = this;
		this.prop = new ServerProp();
		this.scope = new ServerScope();
		this.state = new ServerState();
	}
	
	public Server(String name, String description, int index, int state, int prop, int scope) {
		super(name, description);
		this.index = index;
		this.state = new ServerState(state);
		this.prop = new ServerProp(prop);
		this.scope = new ServerScope(scope);
		this.iterator = this;
	}
	
	public Server(String name, String description, int index, int roomIndex, int state, int prop, int scope) {
		super(name, description);
		this.index = index;
		this.rackIndex = roomIndex;
		this.state = new ServerState(state);
		this.prop = new ServerProp(prop);
		this.iterator = this;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public ServerState getState() {
		return state;
	}

	public void setState(ServerState state) {
		this.state = state;
	}

	public ServerProp getProp() {
		return prop;
	}

	public void setProp(ServerProp prop) {
		this.prop = prop;
	}

	public int getRackIndex() {
		return rackIndex;
	}

	public void setRackIndex(int rackIndex) {
		this.rackIndex = rackIndex;
	}

	public ServerScope getScope() {
		return scope;
	}

	public void setScope(ServerScope scope) {
		this.scope = scope;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(int startsAt) {
		this.startsAt = startsAt;
	}

	public int getNumU() {
		return numU;
	}

	public void setNumU(int numU) {
		this.numU = numU;
	}

	public String getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(String modelBrand) {
		this.modelBrand = modelBrand;
	}

	public String getModelNature() {
		return modelNature;
	}

	public void setModelNature(String modelNature) {
		this.modelNature = modelNature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getxReference() {
		return xReference;
	}

	public void setxReference(String xReference) {
		this.xReference = xReference;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getCriticality() {
		return criticality;
	}

	public void setCriticality(int criticality) {
		this.criticality = criticality;
		this.state.setStateValue(criticality);
	}

	public Server getNext() {
		return next;
	}

	public void setNext(Server next) {
		this.next = next;
		if (next != null) next.setParent(parent);
	}

	@Override
	public Drawable findDrawableWithPosition(int x, int y) {
		Drawable found = null;
		resetIterator();
		
		while (hasNext()) {
			found = next();
			if (found.isInPosition(x, y)) break; 
			found = null;
		}
		
		resetIterator();
		return found;
	}

	public int count() {
		int c = 0;
		Server s = this;
		while (s != null) {
			c++;
			s = s.getNext();
		}
		return c;
	}
	
	public void resetIterator() {
		iterator = this;
	}

	@Override
	public boolean hasNext() {
		return iterator != null;
	}

	@Override
	public Server next() {
		Server s = iterator;
		iterator = iterator.getNext();
		return s;
	}

	@Override
	public void remove() {
	}
}