package fr.epita.pri.rackrepresentator.models;

public class ServerState {
	public static final int Other = -1;
	public static final int Free = 0;
	public static final int InUse = 2;
	public static final int PlanToRemove = 3;
	public static final int PowerOffToRemove = 4;
	
	private int stateValue;
	
	public ServerState() {
		stateValue = Other;
	}
	
	public ServerState(int state) {
		this.stateValue = state;
	}
	
	public int getStateValue() {
		return stateValue;
	}

	public void setStateValue(int state) {
		this.stateValue = state;
	}

	@Override
	public String toString() {
		return getStateString(stateValue);
	}
	
	public static String getStateString(int state) {
		switch (state) {
		case PowerOffToRemove: return "Power Off, to be removed";
		case PlanToRemove: return "Planned to be removed";
		case InUse: return "";
		case Free: return "Free";
		default: return "Other";
		}
	}
}