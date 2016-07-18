package fr.epita.pri.rackrepresentator.settings;

public class Setting {
	private static int numDisplayedRacks = 2;
	private static int reloadTime = 0;
	
	public static int getNumDisplayedRacks() {
		return numDisplayedRacks;
	}
	
	public static void setNumDisplayedRacks(int numDisplayedRacks) {
		Setting.numDisplayedRacks = numDisplayedRacks;
	}
	
	public static int getReloadTime() {
		return reloadTime;
	}
	
	public static void setReloadTime(int reloadTime) {
		Setting.reloadTime = reloadTime;
	}
}