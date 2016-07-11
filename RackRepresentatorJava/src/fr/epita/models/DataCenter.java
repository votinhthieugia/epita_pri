package fr.epita.models;

import java.util.ArrayList;
import java.util.List;

public class DataCenter extends Drawable {
	private int index;
	private List<Room> rooms;
	
	public DataCenter(String name, String description, int index) {
		super(name, description);
		this.index = index;
		rooms = new ArrayList<Room>();
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room);
	}
	
	public Room find(int roomIndex) {
		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			if (room.getIndex() == roomIndex) {
				return room;
			}
		}
		
		return null;
	}
	
	public Room findByIndex(int roomIndex) {
		return roomIndex < rooms.size() ? rooms.get(roomIndex) : null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}