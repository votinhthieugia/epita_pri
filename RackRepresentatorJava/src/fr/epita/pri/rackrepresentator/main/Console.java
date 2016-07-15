package fr.epita.pri.rackrepresentator.main;

import javax.swing.JTextArea;

public class Console {
	
	private JTextArea txtArea;
	private static Console singleton = new Console();
	
	private Console(){
//		this.txtArea = txtArea;
	}
	
	private Console(JTextArea txtArea){
		this.txtArea = txtArea;
	}
	
	public void setConsoleComponent(JTextArea txtArea){
		this.txtArea = txtArea;
	}
	
	public static Console Instance(){		
			return singleton;
	}
	
	public void info(String str){
		txtArea.append("\n" + str);
	}
}
