package fr.epita.pri.rackrepresentator.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;

public class ViewController implements IViewController {
	private static ViewController instance;
	
	public static ViewController Instance() {
		if (instance == null) {
			instance = new ViewController();
		}
		return instance;
	}
	
	private BaseView[] views;
	private BaseView currentView;
	
	private ViewController() {
		views = new BaseView[ViewId.Total];
	}
	
	@Override
	public BaseView getView(int viewId) {
		if (views[viewId] != null) return views[viewId];
		BaseView view = null;
		switch (viewId) {
		case ViewId.Login: view = new LoginView("Rack Representator - Login", this); break;
		case ViewId.Main: view = new MainView("Rack Representator - Main", this); break;
		case ViewId.ManageUser: view = new ManageUserView("Rack Representator - Manage User", this); break;
		case ViewId.ManageDrawable: view = new ManageDrawableView("Rack Representator - Manage Drawable", this); break;
		case ViewId.Setting: view = new SettingView("Rack Representator - Setting", this); break;
		case ViewId.About: view = new AboutView("Rack Representator - About Us", this); break;
		case ViewId.OpenFile: view = new OpenFileView("Rack Representator - Open File", this); break;
		default: break;
		}
		return view;
	}
	
	@Override
	public void addView(BaseView view, int viewId) {
		views[viewId] = view;
	}
	
	@Override
	public void removeView(int viewId) {
		views[viewId] = null;
	}
	
	@Override
	public void show(int viewId) {
		if (currentView != null && currentView.getViewId() != ViewId.Main) hide(currentView.getViewId());
		currentView = getView(viewId);
		if (currentView != null) currentView.setVisible(true);
	}
	
	@Override
	public void hide(int viewId) {
		views[viewId].setVisible(false);
	}
	
	@Override
	public void start() {
		JFrame.setDefaultLookAndFeelDecorated(true);                   
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ChallengerDeepSkin");
		SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceBrownTheme");
		SwingUtilities.invokeLater(new Runnable() {			      
			@Override
			public void run() {                
				runApp();
			}		
		});		
	}
	
	void runApp() {
		try {						
			show(ViewId.Login);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void restart() {
		if (views[ViewId.Main] != null) views[ViewId.Main].dispose();
		for (int i = 0; i < ViewId.Total; i++) {
			if (views[i] != null) views[i].dispose();
			views[i] = null;
		}
		
		currentView = null;
		runApp();
	}
}