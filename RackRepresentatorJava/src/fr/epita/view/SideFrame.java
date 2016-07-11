package fr.epita.view;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;

public class SideFrame extends JComponent {
	public SideFrame() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Admin");
		add(label);		
		
		add(new JLabel(" "));
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnSettings);
		
		add(new JLabel(" "));
				
		JButton btnBlaBlaBla = new JButton("Profile");
		btnBlaBlaBla.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnBlaBlaBla);

		add(new JLabel(" "));
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnLoad);
		
		add(new JLabel(" "));
		JButton btnExport = new JButton("Export");
		btnExport.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
	}
}
