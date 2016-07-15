package fr.epita.models;

public class ServerScope {
	public static final int Other = -1;
	public static final int Lan = 0;
	public static final int Orix = 1;
	public static final int Electro = 2;
	
	private int scopeValue;
	
	public ServerScope() {
		scopeValue = Other;
	}
	
	public ServerScope(int scope) {
		this.scopeValue = scope;
	}
	
	public int getScopeValue() {
		return scopeValue;
	}

	public void setScopeValue(int scopeValue) {
		this.scopeValue = scopeValue;
	}

	@Override
	public String toString() {
		return getScopeString(scopeValue);
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
