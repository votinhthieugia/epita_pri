package fr.epita.models;

public class DataLoader {
	public static Drawable load() {
		Server server = new Server("s1", "desc", 0, 0, 0, 0);
		server.position(0, 0, 100, 100);
		return server;
	}
}