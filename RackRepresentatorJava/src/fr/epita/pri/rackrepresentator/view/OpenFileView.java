package fr.epita.pri.rackrepresentator.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OpenFileView extends BaseView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordTextField;
	private JButton buttonBrowse;
	private JButton buttonOpen;
	private JButton buttonCancel;
	private String filePath;

	public OpenFileView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.OpenFile);
		
		setBounds(100, 100, 401, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(20, 50, 200, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		buttonBrowse = new JButton("Browse");
		buttonBrowse.addActionListener(this);
		buttonBrowse.setBounds(230, 48, 100, 26);
		getContentPane().add(buttonBrowse);

		JLabel lblTitle = new JLabel("Password");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(20, 60, 100, 100);
		contentPane.add(lblTitle);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTextField.setBounds(100, 100, 230, 20);
		contentPane.add(passwordTextField);
		passwordTextField.addActionListener(this);
		
		buttonOpen = new JButton("Open");
		buttonOpen.addActionListener(this);
		buttonOpen.setBounds(80, 160, 100, 25);
		contentPane.add(buttonOpen);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		buttonCancel.setBounds(220, 160, 100, 25);
		contentPane.add(buttonCancel);
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonBrowse) {
			final JFileChooser fc = new JFileChooser();
			int response = fc.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				filePath = fc.getSelectedFile().getAbsolutePath();
				textField.setText(filePath);
			}
		} else if (e.getSource() == buttonOpen) {
			controller.hide(viewId);
			MainView mainView = (MainView)((ViewController)controller).getView(ViewId.Main);
			MainFrame mainFrame = (MainFrame)mainView.getFrame(FrameId.MAIN);
			mainFrame.loadFromFile(filePath, String.copyValueOf(passwordTextField.getPassword()));
		} else if (e.getSource() == buttonCancel) {
			controller.hide(viewId);
		}
	}
}