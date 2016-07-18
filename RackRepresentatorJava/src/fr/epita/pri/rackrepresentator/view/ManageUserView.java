package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import fr.epita.pri.rackrepresentator.daos.IUserDao;
import fr.epita.pri.rackrepresentator.daos.UserDao;
import fr.epita.pri.rackrepresentator.encryption.MD5Encryptor;
import fr.epita.pri.rackrepresentator.models.User;

public class ManageUserView extends BaseView implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private IUserDao userDao;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton buttonAll;
	private JButton buttonSearch;
	private JButton buttonUpdate;
	private JButton buttonCreate;
	private JButton buttonDelete;
	private JPasswordField passwordTextField;
	private JTextField searchTextField;
	private JTextField nameTextField;
	private JPasswordField newPasswordTextField;
	private JTextField newNameTextField;
	private List<User> users;
	private User currentUser;
	private int selectedRow = -1;
	private boolean isAll;

	public ManageUserView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.ManageUser);
		
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblList = new JLabel("Users");
		lblList.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblList.setBounds(20, 20, 78, 26);
		contentPane.add(lblList);
		
		JLabel lblValue = new JLabel("Name");
		lblValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValue.setBounds(20, 60, 46, 14);
		contentPane.add(lblValue);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(70, 60, 200, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		buttonSearch = new JButton("Search");
		buttonSearch.addActionListener(this);
		buttonSearch.setBounds(280, 58, 89, 23);
		contentPane.add(buttonSearch);
		
		buttonAll = new JButton("All");
		buttonAll.addActionListener(this);
		buttonAll.setBounds(370, 58, 70, 23);
		contentPane.add(buttonAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 420, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(this);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(20, 420, 46, 14);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(100, 420, 200, 20);
		nameTextField.setColumns(10);
		contentPane.add(nameTextField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(20, 450, 70, 14);
		contentPane.add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTextField.setBounds(100, 450, 200, 20);
		passwordTextField.addActionListener(this);
		contentPane.add(passwordTextField);
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(100, 480, 80, 23);
		buttonUpdate.addActionListener(this);
		contentPane.add(buttonUpdate);
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(200, 480, 80, 23);
		buttonDelete.addActionListener(this);
		contentPane.add(buttonDelete);
		
		// Create part.
		JLabel lblCreate = new JLabel("New User");
		lblCreate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreate.setBounds(500, 20, 78, 26);
		contentPane.add(lblCreate);
		
		JLabel lblNewName = new JLabel("Name");
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewName.setBounds(500, 70, 46, 14);
		contentPane.add(lblNewName);
		
		newNameTextField = new JTextField();
		newNameTextField.setBounds(580, 70, 200, 20);
		newNameTextField.setColumns(10);
		contentPane.add(newNameTextField);
		
		JLabel lblNewPassword = new JLabel("Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewPassword.setBounds(500, 110, 70, 14);
		contentPane.add(lblNewPassword);
		
		newPasswordTextField = new JPasswordField();
		newPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newPasswordTextField.setBounds(580, 110, 200, 20);
		contentPane.add(newPasswordTextField);
		
		buttonCreate = new JButton("Create");
		buttonCreate.setBounds(495, 160, 80, 23);
		buttonCreate.addActionListener(this);
		contentPane.add(buttonCreate);

		userDao = new UserDao(new MD5Encryptor());
		loadTable();
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonSearch) {
			try {
				isAll = false;
				users = userDao.findByName(searchTextField.getText());
				loadTable();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
		} else if (e.getSource() == buttonAll) {
			try {
				isAll = true;
				users = userDao.getAll();
				loadTable();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (e.getSource() == buttonUpdate) {
			if (currentUser.isAdmin()) {
				JOptionPane.showMessageDialog(null, "Cannot update admin user!");
				return;
			}
			
			try {
				currentUser.setName(nameTextField.getText());
				currentUser.setPassword(String.copyValueOf(passwordTextField.getPassword()));
				userDao.update(currentUser);
				tableModel.setValueAt(currentUser.getName(), selectedRow, 0);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (e.getSource() == buttonDelete) {
			if (currentUser.isAdmin()) {
				JOptionPane.showMessageDialog(null, "Cannot delete admin user!");
				return;
			}
			
			try {
				userDao.delete(currentUser);
				if (selectedRow != -1) {
					tableModel.removeRow(selectedRow);
					selectedRow = -1;
				}
				users.remove(currentUser);
				currentUser = null;
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (e.getSource() == buttonCreate) {
			User user = new User(newNameTextField.getText(), String.copyValueOf(newPasswordTextField.getPassword()), User.Normal);
			try {
				userDao.create(user);
				if (tableModel != null) tableModel.addRow(new Object[] { user.getName(), user.isAdmin() ? "Admin" : "User"});
				if (isAll && users != null) users.add(user);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
	private void loadTable() {
		int size = users == null || users.isEmpty() ? 0 : users.size(); 
		Object[][] data = new String[size][2];
		int i = 0;
		if (users != null && users.size() > 0) {
			for (User user : users) {
				data[i][0] = user.getName();			
				data[i++][1] = user.isAdmin() ? "Admin" : "User";	            
			}
		}
		
		tableModel = createTableData(data);
		table.setModel(tableModel);
	}
	
	private DefaultTableModel createTableData(Object[][] data) {
		return new DefaultTableModel(data, new String[] { "NAME", "TYPE" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			selectedRow = table.getSelectedRow();
			if (selectedRow >= 0 && selectedRow < users.size()) {
				currentUser = users.get(selectedRow);
				nameTextField.setText(currentUser.getName());
				passwordTextField.setText("******");
			}
		}
	}
}