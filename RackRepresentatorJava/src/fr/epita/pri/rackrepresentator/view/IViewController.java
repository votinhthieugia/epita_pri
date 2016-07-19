package fr.epita.pri.rackrepresentator.view;

public interface IViewController {
	public BaseView getView(int viewId);
	public void addView(BaseView view, int viewId);
	public void removeView(int viewId);
	public void show(int viewId);
	public void hide(int viewId);
	public void start();
	public void restart();
}
