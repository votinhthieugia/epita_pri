package fr.epita.pri.rackrepresentator.data;

import fr.epita.pri.rackrepresentator.models.Drawable;

public class DataLoader implements IDataLoader {
	private static DataLoader instance;
	
	public static DataLoader Instance() {
		if (instance == null) instance = new DataLoader();
		return instance;
	}
	
	private ExcelLoader excelLoader;
	private DatabaseLoader dbLoader;
	
	private DataLoader() {
		excelLoader = new ExcelLoader();
		dbLoader = new DatabaseLoader();
	}
	
	public Drawable loadFromDb() {
		return dbLoader.loadAll();
	}
	
	public Drawable loadFromFile(String filePath, String password) {
		return excelLoader.loadAllFromFile(filePath, password);
	}
}
