package fr.epita.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.epita.main.Console;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private BaseFrame[] frames;

	public Window(String name){
		super(name);
		
        frames = new BaseFrame[FrameId.TOTAL];
        
        BottomFrame bottomFrame = new BottomFrame(this);
        frames[FrameId.BOTTOM] = bottomFrame;
        getContentPane().setLayout(new BorderLayout(0, 0));
        SideFrame sideFrame = new SideFrame(this);
        sideFrame.setBackground(Color.DARK_GRAY);
        frames[FrameId.SIDE] = sideFrame;
		
        getContentPane().add(sideFrame, BorderLayout.WEST);
        getContentPane().add(bottomFrame, BorderLayout.SOUTH);
        
        Console.Instance().setConsoleComponent(bottomFrame.getTxtAreaConsole());
        
        MainFrame mainFrame = new MainFrame(this);
        mainFrame.setBounds(111, 116, 2000, 1000);
        frames[FrameId.MAIN] = mainFrame;

        JScrollPane scroll = new JScrollPane(mainFrame, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        
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
}