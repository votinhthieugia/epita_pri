package fr.epita.models;

import java.util.ArrayList;
import java.util.List;

public class Rack extends Drawable {
	private int index;
	private int roomIndex;
	private List<Server> servers;
	
	public Rack(String name, String description, int roomIndex, int index) {
		super(name, description);
		this.index = index;
		this.roomIndex = roomIndex;
		this.servers = new ArrayList<Server>();
	}
	
	public void addServer(Server server) {
		servers.add(server);
	}
	
	public void removeServer(Server server) {
		servers.remove(server);
	}
	
	public Server find(int index) {
		for (int i = 0; i < servers.size(); i++) {
			Server server = servers.get(i);
			if (server.getIndex() == index) {
				return server;
			}
		}
		
		return null;
	}
	
	public Server findByIndex(int index) {
		return index < servers.size() ? servers.get(index) : null;
	}
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
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

	public int getRoomIndex() {
		return roomIndex;
	}

	public void setRoomIndex(int roomIndex) {
		this.roomIndex = roomIndex;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}