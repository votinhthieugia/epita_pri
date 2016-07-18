package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import fr.epita.pri.rackrepresentator.main.Console;
import fr.epita.pri.rackrepresentator.main.SessionController;

public class Window extends BaseView implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private BaseFrame[] frames;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	public Window(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.Main);
		
		frames = new BaseFrame[FrameId.TOTAL];
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        TopFrame topFrame = new TopFrame(this);
        topFrame.setBackground(Color.DARK_GRAY);
        frames[FrameId.TOP] = topFrame;
        
        BottomFrame bottomFrame = new BottomFrame(this);
        frames[FrameId.BOTTOM] = bottomFrame;
        bottomFrame.setMinimumSize(new Dimension(0, 100));
        Console.setConsoleComponent(bottomFrame.getTxtAreaConsole());
        
        MainFrame mainFrame = new MainFrame(this);
        frames[FrameId.MAIN] = mainFrame;
        
        JScrollPane scroll = new JScrollPane(mainFrame, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        getContentPane().add(topFrame);
        getContentPane().add(scroll);
        getContentPane().add(bottomFrame);
                
        createMenu();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
	}
	
	private void createMenu() {
        menuBar = new JMenuBar();
		menu = new JMenu("Rack Representator");
		menuItem = new JMenuItem("About Us");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Setting");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("File");
		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open Encrypt");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		if (SessionController.isAdminSession()) {
			menu = new JMenu("Manage");
			menuItem = new JMenuItem("User");
			menuItem.addActionListener(this);
			menu.add(menuItem);
			menuBar.add(menu);
		}
		
		menu = new JMenu(SessionController.getUser().getName());
		menuItem = new JMenuItem("Logout");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
	}
	
	public BaseFrame getFrame(int frameId) {
		if (frameId >= 0 && frameId < FrameId.TOTAL) {
			return frames[frameId];
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem)e.getSource();
			switch (menuItem.getText()) {
			case "About Us":
				controller.show(ViewId.About);
				break;
			case "Setting":
				controller.show(ViewId.Setting);
				break;
			case "Open":
				final JFileChooser fc = new JFileChooser();
				int response = fc.showOpenDialog(this);
				if (response == JFileChooser.APPROVE_OPTION) {
					MainFrame mainFrame = (MainFrame)getFrame(FrameId.MAIN);
					mainFrame.loadFromFile(fc.getSelectedFile().getAbsolutePath(), null);
				}
				break;
			case "Open Encrypt":
				controller.show(ViewId.OpenFile);
				break;
			case "Logout":
				SessionController.destroy();
				controller.restart();
				break;
			case "User":
				controller.show(ViewId.ManageUser);
				break;
			default:
				break;
			}
		}
	}
}