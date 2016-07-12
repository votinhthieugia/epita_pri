package fr.epita.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class BottomFrame extends JComponent {

	private static JTextArea txtAreaConsole = new JTextArea();

	public JTextArea getTxtAreaConsole() {
		return txtAreaConsole;
	}

	public BottomFrame() {
		setLayout(new BorderLayout(0, 0));
		txtAreaConsole.setEditable(false);

		JScrollPane scroll = new JScrollPane(txtAreaConsole, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		txtAreaConsole.setText("Console Example \n Ipsum \n Lorem \n Test");
		add(scroll);
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
	}
}
