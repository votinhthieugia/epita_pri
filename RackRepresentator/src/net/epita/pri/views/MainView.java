package net.epita.pri.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.epita.pri.drawings.IDrawer;
import net.epita.pri.drawings.MxGraphDrawer;

public class MainView extends BaseView {
	private static final long serialVersionUID = 1L;
	
	private IDrawer drawer;
	
	public MainView(int index) {
		super(index);
		drawer = new MxGraphDrawer(contentPane);
	}
	
	@Override
	public void init() {
		initUI();
		initRackData();
	}
	
	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = (JPanel) getContentPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton quitButton = new JButton("Remove");
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Removed");
				drawer.handleEvent(0, null);
			}
		});
		quitButton.setBounds(59, 227, 89, 23);
		contentPane.add(quitButton);
	}
	
	private void initRackData() {
		
	}
	
	@Override
	public void display() {
		drawer.draw();
	}

	@Override
	public void conceal() {
	}
}