package fr.epita.pri.rackrepresentator.data;

import fr.epita.pri.rackrepresentator.models.Drawable;

public interface IDataLoader {
	public Drawable loadFromDb();
	public Drawable loadFromFile(String filePath, String password);
}
