package fr.epita.pri.rackrepresentator.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import fr.epita.pri.rackrepresentator.main.SessionController;

public class TopFrame extends BaseFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLoad;
	private JButton btnSettings;
	private JButton btnBack;
	private JButton btnExport; 
	
	public TopFrame(MainView manager) {
		super(manager);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(SessionController.getUser().getName());
		
		add(label);		
		
		add(new JLabel(" "));
		
		btnExport = new JButton("Export");
		btnExport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExport.addActionListener(this);
		add(btnExport);
		
		add(new JLabel(" "));
		btnBack = new JButton("Back");
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.addActionListener(this);
		add(btnBack);
	}

	void onLoadBtnClicked() {
		final JFileChooser fc = new JFileChooser();
		int response = fc.showOpenDialog(this);
		if (response == JFileChooser.APPROVE_OPTION) {
			MainFrame mainFrame = (MainFrame)manager.getFrame(FrameId.MAIN);
			mainFrame.loadFromFile(fc.getSelectedFile().getAbsolutePath(), null);
		}
	}
	
	void onBackBtnClicked() {
		MainFrame mainFrame = (MainFrame)manager.getFrame(FrameId.MAIN);
		mainFrame.back();
	}
	
	void onExportBtnClicked() {
		MainFrame mainFrame = (MainFrame)manager.getFrame(FrameId.MAIN);
		mainFrame.export();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoad) {
            onLoadBtnClicked();
		} else if (e.getSource() == btnSettings) {
		}else if (e.getSource() == btnBack) {
			onBackBtnClicked();
		}else if (e.getSource() == btnExport) {
			onExportBtnClicked();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
}
