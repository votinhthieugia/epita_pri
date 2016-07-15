package fr.epita.pri.rackrepresentator.main;

import fr.epita.pri.rackrepresentator.view.Window;

public class Main {
	
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Window("Rack Representator");
            }
        });
    }
}
