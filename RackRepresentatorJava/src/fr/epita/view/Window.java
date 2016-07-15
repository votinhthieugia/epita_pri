package fr.epita.view;

import javax.swing.JFrame;

import fr.epita.main.Console;
import fr.epita.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private BaseFrame[] frames;

	public Window(String name){
		super(name);
        getContentPane().setLayout(new BorderLayout(0, 0));
		
        frames = new BaseFrame[FrameId.TOTAL];
        
        BottomFrame bottomFrame = new BottomFrame(this);
        frames[FrameId.BOTTOM] = bottomFrame; 
        MainFrame mainFrame = new MainFrame(this);
        frames[FrameId.MAIN] = mainFrame;
        SideFrame sideFrame = new SideFrame(this);
        frames[FrameId.SIDE] = sideFrame;
        sideFrame.setBackground(Color.DARK_GRAY);
        
        getContentPane().add(sideFrame, BorderLayout.WEST);
        getContentPane().add(bottomFrame, BorderLayout.SOUTH);
        getContentPane().add(mainFrame, BorderLayout.CENTER);
        
        Console.Instance().setConsoleComponent(bottomFrame.getTxtAreaConsole());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        this.setVisible(true);
	}
	
	public BaseFrame getFrame(int frameId) {
		if (frameId >= 0 && frameId < FrameId.TOTAL) {
			return frames[frameId];
		}
		
		return null;
	}
	
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
