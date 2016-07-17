package fr.epita.pri.rackrepresentator.main;

import javax.swing.JTextArea;

public class Console {
	
	private static JTextArea txtArea;
	
	public static void setConsoleComponent(JTextArea txtArea){
		Console.txtArea = txtArea;
	}
	
	public static void error(String str){
		info(str, true);
	}
	
	public static void info(String str){
		info(str, true);
	}
	
	public static void info(String str, boolean skipLine){
		txtArea.append(str);
		if(skipLine)
			txtArea.append("\n");
	}
}
