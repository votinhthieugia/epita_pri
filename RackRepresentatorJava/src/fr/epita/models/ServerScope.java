package fr.epita.models;

public class ServerScope {
	public static final int Lan = 0;
	public static final int Orix = 1;
	public static final int Electro = 2;
	
	private int scope;
	
	public ServerScope(int scope) {
		this.scope = scope;
	}
	
	@Override
	public String toString() {
		return getScopeString(scope);
	}
	
	public static String getScopeString(int scope) {
		switch (scope) {
		case Lan: return "LAN";
		case Orix: return "Orix";
		case Electro: return "Electro";
		default: return "";
		}
	}
}
