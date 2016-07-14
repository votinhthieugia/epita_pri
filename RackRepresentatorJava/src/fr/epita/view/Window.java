package fr.epita.view;

import javax.swing.JFrame;

import fr.epita.main.Console;
import fr.epita.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Window extends JFrame {

	public Window(String name){
		super(name);
        getContentPane().setLayout(new BorderLayout(0, 0));
		
        BottomFrame bottomFrame = new BottomFrame();
        SideFrame sideFrame = new SideFrame();
        sideFrame.setBackground(Color.DARK_GRAY);
        
        getContentPane().add(sideFrame, BorderLayout.WEST);
        getContentPane().add(bottomFrame, BorderLayout.SOUTH);
        
        Console.Instance().setConsoleComponent(bottomFrame.getTxtAreaConsole());
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MainFrame mainFrame = new MainFrame();
//        scrollPane.add(mainFrame);

//        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(mainFrame, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        this.setVisible(true);
	}
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
