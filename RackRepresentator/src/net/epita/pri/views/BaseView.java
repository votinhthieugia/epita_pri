package net.epita.pri.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.epita.pri.controllers.IViewController;

public abstract class BaseView extends JFrame implements IView {
	private static final long serialVersionUID = 1L;
	protected int viewType;
	protected JPanel contentPane;
	
	public BaseView(int viewType) {
		this.viewType = viewType;
		init();
	}

	@Override
	public void register(IViewController viewController) {
		viewController.addView(viewType, this);
	}
}