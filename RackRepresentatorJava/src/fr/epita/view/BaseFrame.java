package fr.epita.view;

import javax.swing.JComponent;

public abstract class BaseFrame extends JComponent {
	private static final long serialVersionUID = 1L;
	
	protected Window manager;
	
	public BaseFrame(Window manager) {
		this.manager = manager;
	}

	public Window getManager() {
		return manager;
	}

	public void setManager(Window manager) {
		this.manager = manager;
	}
}