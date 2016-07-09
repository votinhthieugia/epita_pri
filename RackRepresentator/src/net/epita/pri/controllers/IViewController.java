package net.epita.pri.controllers;

import net.epita.pri.views.IView;

public interface IViewController {
	public void addView(int viewType, IView view);
	public IView getView(int viewType);
	public void showView(int viewType);
	public void start();
}