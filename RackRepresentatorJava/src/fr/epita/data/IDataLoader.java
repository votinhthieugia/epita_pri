package fr.epita.data;

import fr.epita.models.Drawable;

public interface IDataLoader {
	public Drawable loadAll();
	public Drawable loadAllFromFile(String filePath);
	public Drawable loadDataCenter(int index);
	public Drawable loadRack(int index);
	public Drawable loadServer(int index);
}
