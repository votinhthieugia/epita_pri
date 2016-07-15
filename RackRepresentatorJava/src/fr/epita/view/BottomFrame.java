package fr.epita.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BottomFrame extends BaseFrame {
	private static final long serialVersionUID = 1L;
	private static JTextArea txtAreaConsole = new JTextArea();

	public JTextArea getTxtAreaConsole() {
		return txtAreaConsole;
	}

	public BottomFrame(Window manager) {
		super(manager);
		setLayout(new BorderLayout(0, 0));
		txtAreaConsole.setEditable(false);

		JScrollPane scroll = new JScrollPane(txtAreaConsole, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		txtAreaConsole.setText("Console Example \r\n Ipsum \r\n Lorem \r\n Test");
		add(scroll, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}

	@Override
	protected void paintComponent(Graphics g) {
	}
}
