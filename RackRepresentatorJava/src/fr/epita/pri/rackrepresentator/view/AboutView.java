package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class AboutView extends BaseView {
	private static final long serialVersionUID = 1L;

	public AboutView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.About);
		
		setBounds(100, 100, 401, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(100, 100, 401, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Rack Representator");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(130, 0, 400, 100);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 100, 400, 200);
		contentPane.add(panel);
		
		JTextArea lblCopyright = new JTextArea("@2016 Epita Master\nArmel Qojdeshi\nHoang Anh Doan\nGustavo Calheiros\nPhillipe Paes\nVadivambiga Ramachandran\n");
		lblCopyright.setBackground(getBackground());
		lblCopyright.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCopyright.setBounds(0, 10, 400, 200);
		panel.add(lblCopyright);
		
		setResizable(false);
		setLocationRelativeTo(null);
	}
}