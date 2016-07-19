package fr.epita.pri.rackrepresentator.view;

import javax.swing.JComponent;

public abstract class BaseFrame extends JComponent {
	private static final long serialVersionUID = 1L;
	
	protected MainView manager;
	
	public BaseFrame(MainView manager) {
		this.manager = manager;
	}

	public MainView getManager() {
		return manager;
	}

	public void setManager(MainView manager) {
		this.manager = manager;
	}
}