package fr.epita.pri.rackrepresentator.view;

import javax.swing.JFrame;

public class BaseView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected int viewId;
	protected IViewController controller;

	public BaseView(String name, IViewController controller) {
		super(name);
		this.controller = controller;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
		if (controller != null) controller.addView(this, viewId);
	}
}
