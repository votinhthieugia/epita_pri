package fr.epita.pri.rackrepresentator.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class SideFrame extends BaseFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton btnLoad;
	JButton btnSettings;
	
	public SideFrame(Window manager) {
		super(manager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Admin");
		add(label);		
		
		add(new JLabel(" "));
		
		btnSettings = new JButton("Settings");
		btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnSettings);
		
		add(new JLabel(" "));
				
		JButton btnBlaBlaBla = new JButton("Profile");
		btnBlaBlaBla.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnBlaBlaBla);

		add(new JLabel(" "));
		
		btnLoad = new JButton("Load");
		btnLoad.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLoad.addActionListener(this);
		
		add(btnLoad);
		
		add(new JLabel(" "));
		JButton btnExport = new JButton("Export");
		btnExport.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	void onLoadBtnClicked() {
		final JFileChooser fc = new JFileChooser();
		int response = fc.showOpenDialog(this);
		if (response == JFileChooser.APPROVE_OPTION) {
			MainFrame mainFrame = (MainFrame)manager.getFrame(FrameId.MAIN);
			mainFrame.loadFromFile(fc.getSelectedFile().getAbsolutePath());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoad) {
            onLoadBtnClicked();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
}
