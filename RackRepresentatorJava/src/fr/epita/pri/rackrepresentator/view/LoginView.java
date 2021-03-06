package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.epita.pri.rackrepresentator.daos.IUserDao;
import fr.epita.pri.rackrepresentator.daos.UserDao;
import fr.epita.pri.rackrepresentator.encryption.MD5Encryptor;
import fr.epita.pri.rackrepresentator.main.SessionController;
import fr.epita.pri.rackrepresentator.models.User;

public class LoginView extends BaseView implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField userTextField;
	private JPasswordField passwordTextField;
	private JButton buttonAccept;
	private JButton buttonQuit;
	private IUserDao userDao;
	private boolean isAdminSetup;

	public LoginView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.Login);
		
		userDao = new UserDao(new MD5Encryptor());
		try {
			isAdminSetup = userDao.isAdminSetup();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Login");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(38, 0, 100, 100);
		getContentPane().add(lblTitle);
		
		if (!isAdminSetup) {
			JLabel lblMessage = new JLabel("Type in your name and password to create admin account!");
			lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblMessage.setForeground(Color.red);
			lblMessage.setBounds(38, 20, 400, 100);
			getContentPane().add(lblMessage);
		}
		
		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(38, 86, 79, 14);
		getContentPane().add(lblUsername);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userTextField.setBounds(124, 86, 230, 20);
		getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(38, 130, 79, 14);
		getContentPane().add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTextField.setBounds(124, 129, 230, 20);
		getContentPane().add(passwordTextField);
		passwordTextField.addActionListener(this);
		
		buttonAccept = new JButton("Accept");
		buttonAccept.addActionListener(this);
		buttonAccept.setBounds(67, 180, 100, 25);
		getContentPane().add(buttonAccept);

		buttonQuit = new JButton("Quit");
		buttonQuit.setBounds(227, 180, 100, 25);
		buttonQuit.addActionListener(this);
		getContentPane().add(buttonQuit);
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAccept) {
			login();
		} else if (e.getSource() == buttonQuit) {
			System.exit(0);
		}
	}
	
	private void login() {
		String username = userTextField.getText();
        String password = String.copyValueOf(passwordTextField.getPassword());
        
        if (!isAdminSetup) {
        	try {
        		userDao.create(new User(username, password, User.Admin));
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        }
        
        SessionController.create(username, password);
        
        if (SessionController.exists()) {
        	controller.show(ViewId.Main);
        } else {
        	JOptionPane.showMessageDialog(null, "User or password incorrect");
        	resetFields();
        }
	}
	
	private void resetFields() {
		userTextField.setText("");
		passwordTextField.setText("");
	}
}