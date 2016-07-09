package net.epita.pri.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;

import net.epita.pri.views.BaseView;
import net.epita.pri.views.IView;
import net.epita.pri.views.MainView;
import net.epita.pri.views.ViewType;

public class ViewController implements IViewController {
	private static ViewController instance;
	private IView currentView;
	private Map<Integer, IView> views;
	
	public static ViewController Instance() {
		if (instance == null) {
			instance = new ViewController();
		}
		
		return instance;
	}
	
	private ViewController() {
		views = new HashMap<Integer, IView>();
	}
	
	@Override
	public void addView(int index, IView view) {
		views.put(index, view);
	}
	
	@Override
	public IView getView(int index) {
		return views.get(index);
	}
	
	@Override
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {			      
			@Override
			public void run() {                
				display();
			}		
		});
	}
	
	private void display() {
		JFrame.setDefaultLookAndFeelDecorated(true);                   
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ChallengerDeepSkin");
		SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceBrownTheme");		
		try {						
			BaseView startView = new MainView(ViewType.MAIN);
			startView.setVisible(true);
			setCurrentView(startView);
			startView.display();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Override
	public void showView(int index) {
		showView(index, null);
	}
	
	public void showView(int index, Object data) {
		if (currentView != null) {
			currentView.conceal();			
		}		
		
		switch (index) {
		case ViewType.LOGIN:
			break;
		}
	}
	
	public IView getCurrentView() {
		return currentView;
	}
	
	public void setCurrentView(IView view) {
		currentView = view;
	}
}
