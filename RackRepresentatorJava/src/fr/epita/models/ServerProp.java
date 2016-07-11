package fr.epita.models;

public class ServerProp {
	public static final int Other = -1;
	public static final int ComputerRoomInfrastructure = 0;
	public static final int NonWindowsRelatedEquipment = 1;
	public static final int WindowsRelatedEquipment = 2;
	
	private int prop;
	
	public ServerProp(int prop) {
		this.prop = prop;
	}
	
	@Override
	public String toString() {
		return getServerPropString(prop);
	}
	
	public static String getServerPropString(int prop) {
		switch (prop) {
		case ComputerRoomInfrastructure:
			return "Computer Room Infrastructure";
		case NonWindowsRelatedEquipment:
			return "Non Windows Related Equipment";
		case WindowsRelatedEquipment:
			return "Windows Related Equipment";
		default:
			return "Other";
		}
	}
}