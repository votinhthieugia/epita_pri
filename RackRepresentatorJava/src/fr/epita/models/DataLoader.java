package fr.epita.models;

public class DataLoader {
	public static Drawable load() {
		
		Rack rack = new Rack("r1", "desc", 0, 0);
		rack.position(50, 200, 100, 400);
		
		Server server = new Server("s1", "desc", 0, 0, 0, 0);
		server.position(0, 0, 4, 1);
		
		rack.addServer(server);
		return rack;
	}
}