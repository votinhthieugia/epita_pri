package fr.epita.view;

import javax.swing.JFrame;

import fr.epita.main.Console;
import fr.epita.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Window extends JFrame {

	public Window(String name){
		super(name);
        getContentPane().setLayout(new BorderLayout(0, 0));
		
        BottomFrame bottomFrame = new BottomFrame();
        MainFrame mainFrame = new MainFrame();
        SideFrame sideFrame = new SideFrame();
        sideFrame.setBackground(Color.DARK_GRAY);
        
        getContentPane().add(sideFrame, BorderLayout.WEST);
        getContentPane().add(bottomFrame, BorderLayout.SOUTH);
        getContentPane().add(mainFrame, BorderLayout.CENTER);
        
        Console.Instance().setConsoleComponent(bottomFrame.getTxtAreaConsole());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        this.setVisible(true);
	}
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.      
        
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
