package fr.epita.pri.rackrepresentator.data;

import fr.epita.pri.rackrepresentator.models.Drawable;

public interface IDataLoader {
	public Drawable loadAll();
	public Drawable loadAllFromFile(String filePath, String password);
	public Drawable loadDataCenter(int index);
	public Drawable loadRack(int index);
	public Drawable loadServer(int index);
}
