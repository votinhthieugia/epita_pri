package fr.epita.pri.rackrepresentator.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.epita.pri.rackrepresentator.settings.Setting;

public class SettingView extends BaseView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> fieldComboBox;
	private JTextField timeTextField;
	private JButton buttonAccept;
	private JButton buttonCancel;

	public SettingView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.Setting);
		
		setBounds(0, 0, 401, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 401, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Num racks displayed:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(40, 20, 150, 100);
		contentPane.add(lblTitle);
		
		fieldComboBox = new JComboBox<Integer>();
		fieldComboBox.setBounds(180, 60, 120, 20);
		for (int i = 2; i <= 5; i++) {
			fieldComboBox.addItem(i);
			if (Setting.getNumDisplayedRacks() == i) {
				fieldComboBox.setSelectedIndex(i - 2);
			}
		}
		
		contentPane.add(fieldComboBox);
		
		lblTitle = new JLabel("Reload Time (seconds):");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(40, 60, 150, 100);
		contentPane.add(lblTitle);
		
		timeTextField = new JTextField(String.valueOf(Setting.getReloadTime()));
		timeTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		timeTextField.setBounds(180, 100, 50, 20);
		contentPane.add(timeTextField);
		timeTextField.setColumns(10);
		
		buttonAccept = new JButton("Accept");
		buttonAccept.addActionListener(this);
		buttonAccept.setBounds(67, 150, 100, 25);
		contentPane.add(buttonAccept);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(227, 150, 100, 25);
		buttonCancel.addActionListener(this);
		contentPane.add(buttonCancel);
		
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAccept) {
			Setting.setNumDisplayedRacks(Integer.valueOf(fieldComboBox.getSelectedItem().toString()));
			Setting.setReloadTime(Integer.valueOf(timeTextField.getText()));
		} else if (e.getSource() == buttonCancel) {
			controller.hide(viewId);
		}
	}
}