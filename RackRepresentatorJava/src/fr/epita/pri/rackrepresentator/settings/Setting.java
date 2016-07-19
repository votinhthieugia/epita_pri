package fr.epita.pri.rackrepresentator.settings;

import fr.epita.pri.rackrepresentator.daos.SettingDao;

public class Setting {
	private static Setting instance;
	public static Setting Instance() {
		if (instance == null) instance = new Setting();
		return instance;
	}
	
	private int numDisplayedRacks = 2;
	private int reloadTime = 0;
	private SettingDao settingDao;
	
	private Setting() {
		settingDao = new SettingDao();
	}
	
	public int getNumDisplayedRacks() {
		return numDisplayedRacks;
	}
	
	public void setNumDisplayedRacks(int numDisplayedRacks) {
		this.numDisplayedRacks = numDisplayedRacks;
	}
	
	public int getReloadTime() {
		return reloadTime;
	}
	
	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public void load() {
		try {
			settingDao.load(instance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void save() {
		try {
			settingDao.save(instance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}