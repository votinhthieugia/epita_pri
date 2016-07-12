package fr.epita.data;

import fr.epita.models.Drawable;
import fr.epita.models.Server;

public class ExcelLoader implements IDataLoader {
	@Override
	public Drawable loadAll() {
		Server server = new Server("s1", "desc", 0, 0, 0, 0);
		server.position(0, 0, 100, 100);
		return server;
	}

	@Override
	public Drawable loadDataCenter(int index) {
		return null;
	}

	@Override
	public Drawable loadRack(int index) {
		return null;
	}

	@Override
	public Drawable loadServer(int index) {
		return null;
	}

	@Override
	public Drawable loadAllFromFile(String filePath) {
		Drawable drawable = null;
		return drawable;
	}
}