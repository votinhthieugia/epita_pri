package net.epita.pri.views;

import net.epita.pri.controllers.IViewController;

public interface IView {
	public void init();
	public void register(IViewController viewController);
	public void display();
	public void conceal();
}
