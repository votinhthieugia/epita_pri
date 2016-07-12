package fr.epita.models;

public class Server extends Drawable {
	private ServerState state;
	private ServerProp prop;
	private ServerScope scope;
	private int rackIndex;
	private int index;
	
	public Server(String name, String description, int index, int state, int prop, int scope) {
		super(name, description);
		this.index = index;
		this.state = new ServerState(state);
		this.prop = new ServerProp(prop);
		this.scope = new ServerScope(scope);
	}
	
	public Server(String name, String description, int index, int roomIndex, int state, int prop, int scope) {
		super(name, description);
		this.index = index;
		this.rackIndex = roomIndex;
		this.state = new ServerState(state);
		this.prop = new ServerProp(prop);
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
}