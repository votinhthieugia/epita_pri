package fr.epita.pri.rackrepresentator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import fr.epita.pri.rackrepresentator.daos.DataCenterDao;
import fr.epita.pri.rackrepresentator.daos.RackDao;
import fr.epita.pri.rackrepresentator.daos.ServerDao;
import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;

public class ManageDrawableView extends BaseView implements ActionListener, ListSelectionListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private String currentOption = "Center";
	private int selectedRow = -1;
	private JComboBox<String> drawableSelector;
	private JButton buttonSearch;
	private JButton buttonUpdate;
	private JButton buttonCreate;
	private JButton buttonDelete;
	private JButton buttonAll;
	private JTextField searchTextField;
	private JLabel lblCreate;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<JComponent> centerUIList;
	private List<JComponent> rackUIList;
	private List<JComponent> serverUIList;
	private Rack rack;
	private DataCenter dc;
	private Server server;
	private DataCenterDao dcDao;
	private RackDao rackDao;
	private ServerDao serverDao;
	private List<Drawable> centers;
	private List<Drawable> racks;
	private List<Drawable> servers;
	private boolean isPopulated;

	public ManageDrawableView(String name, IViewController controller) {
		super(name, controller);
		setViewId(ViewId.ManageDrawable);
		
		centerUIList = new ArrayList<JComponent>();
		rackUIList = new ArrayList<JComponent>();
		serverUIList = new ArrayList<JComponent>();
		
		dcDao = new DataCenterDao();
		rackDao = new RackDao();
		serverDao = new ServerDao();
		
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createLabel("Manage", 20, 20, 120, 26);
		
		drawableSelector = new JComboBox<String>();
		drawableSelector.setBounds(80, 25, 120, 20);
		drawableSelector.addItem("Center");
		drawableSelector.addItem("Rack");
		drawableSelector.addItem("Server");
		drawableSelector.addItemListener(this);
		contentPane.add(drawableSelector);

		lblCreate = createLabel("New Center", 500, 20, 78, 26);
		searchTextField = createTextField(20, 60, 200, 20);
		buttonSearch = createButton("Search", 230, 58, 89, 23);
		buttonAll = createButton("All", 320, 58, 70, 23);
		buttonUpdate = createButton("Update", 100, 480, 80, 23);
		buttonDelete = createButton("Delete", 200, 480, 80, 23);
		buttonCreate = createButton("Create", 495, 160, 80, 23);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 130, 420, 150);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(this);
		
		loadServerUI();
		loadRackUI();
		loadCenterUI();
		listAll();
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private JTextField tfCenterName;
	private JTextField tfCenterDescription;
	private JTextField tfNewCenterName;
	private JTextField tfNewCenterDescription;
	
	private void loadCenterUI() {
		lblCreate.setText("New Center");
		buttonUpdate.setBounds(100, 360, 80, 23);
		buttonDelete.setBounds(200, 360, 80, 23);
		buttonCreate.setBounds(495, 160, 80, 23);
		for (JComponent c : rackUIList) c.setVisible(false);
		for (JComponent c : serverUIList) c.setVisible(false);
		
		if (centerUIList.size() > 0) {
			for (JComponent c : centerUIList) c.setVisible(true);
			return;
		}
		
		// Name.
		centerUIList.add(createLabel("Name", 20, 300, 46, 14));
		tfCenterName = createTextField(100, 298, 200, 20);
		centerUIList.add(tfCenterName);
		
		// Description.
		centerUIList.add(createLabel("Description", 20, 330, 80, 14));
		tfCenterDescription = createTextField(100, 328, 200, 20);
		centerUIList.add(tfCenterDescription);
		
		// New center.
		centerUIList.add(createLabel("Name", 500, 70, 46, 14));
		tfNewCenterName = createTextField(570, 70, 200, 20);
		centerUIList.add(tfNewCenterName);
		
		// Description.
		centerUIList.add(createLabel("Description", 500, 120, 80, 14));
		tfNewCenterDescription = createTextField(570, 118, 200, 20);
		centerUIList.add(tfNewCenterDescription);
	}
	
	private JTextField tfRackName;
	private JTextField tfRackDescription;
	private JTextField tfNewRackName;
	private JTextField tfNewRackDescription;
	private JComboBox<String> centerSelector;
	private JComboBox<String> centerSearchSelector;
	private JComboBox<String> centerNewSelector;
	
	private void loadRackUI() {
		lblCreate.setText("New Rack");
		buttonUpdate.setBounds(100, 360, 80, 23);
		buttonDelete.setBounds(200, 360, 80, 23);
		buttonCreate.setBounds(495, 210, 80, 23);
		for (JComponent c : centerUIList) c.setVisible(false);
		for (JComponent c : serverUIList) c.setVisible(false);
		
		if (rackUIList.size() > 0) {
			for (JComponent c : rackUIList) c.setVisible(true);
			return;
		}
		
		// Name.
		rackUIList.add(createLabel("Name", 20, 300, 46, 14));
		tfRackName = createTextField(100, 298, 200, 20);
		rackUIList.add(tfRackName);
		
		// Description.
		rackUIList.add(createLabel("Description", 20, 330, 80, 14));
		tfRackDescription = createTextField(100, 328, 200, 20);
		rackUIList.add(tfRackDescription);
		
		// New center.
		rackUIList.add(createLabel("Name", 500, 70, 46, 14));
		tfNewRackName = createTextField(570, 70, 200, 20);
		rackUIList.add(tfNewRackName);
		
		// Description.
		rackUIList.add(createLabel("Description", 500, 120, 80, 14));
		tfNewRackDescription = createTextField(570, 118, 200, 20);
		rackUIList.add(tfNewRackDescription);
		
		centerSelector = new JComboBox<String>();
		centerSelector.setBounds(310, 300, 120, 20);
		centerSelector.addActionListener(this);
		getContentPane().add(centerSelector);
		rackUIList.add(centerSelector);
		
		centerNewSelector = new JComboBox<String>();
		centerNewSelector.setBounds(495, 160, 120, 20);
		centerNewSelector.addActionListener(this);
		getContentPane().add(centerNewSelector);
		rackUIList.add(centerNewSelector);
		
		centerSearchSelector = new JComboBox<String>();
		centerSearchSelector.setBounds(17, 90, 120, 20);
		centerSearchSelector.addItemListener(this);
		getContentPane().add(centerSearchSelector);
		rackUIList.add(centerSearchSelector);
	}

	private JTextField tfServerName;
	private JTextField tfServerDescription;
	private JTextField tfServerLow;
	private JTextField tfServerHigh;
	private JTextField tfServerStartsAt;
	private JTextField tfServerNumU;
	private JTextField tfServerModel;
	private JTextField tfServerModelBrand;
	private JTextField tfServerModelNature;
	private JTextField tfServerCategory;
	private JTextField tfServerXReference;
	private JTextField tfServerSerialNumber;
	private JTextField tfServerCriticality;
	
	private JTextField tfNewServerName;
	private JTextField tfNewServerDescription;
	private JTextField tfNewServerLow;
	private JTextField tfNewServerHigh;
	private JTextField tfNewServerStartsAt;
	private JTextField tfNewServerNumU;
	private JTextField tfNewServerModel;
	private JTextField tfNewServerModelBrand;
	private JTextField tfNewServerModelNature;
	private JTextField tfNewServerCategory;
	private JTextField tfNewServerXReference;
	private JTextField tfNewServerSerialNumber;
	private JTextField tfNewServerCriticality;
	private JComboBox<String> rackSelector;
	private JComboBox<String> rackSearchSelector;
	private JComboBox<String> rackNewSelector;
	
	private void loadServerUI() {
		lblCreate.setText("New Server");
		buttonUpdate.setBounds(100, 540, 80, 23);
		buttonDelete.setBounds(200, 540, 80, 23);
		buttonCreate.setBounds(495, 490, 80, 23);
		for (JComponent c : rackUIList) c.setVisible(false);
		for (JComponent c : centerUIList) c.setVisible(false);
		
		if (serverUIList.size() > 0) {
			for (JComponent c : serverUIList) c.setVisible(true);
			return;
		}
		
		serverUIList.add(createLabel("Name", 20, 300, 70, 14));
		tfServerName = createTextField(100, 298, 200, 20);
		serverUIList.add(tfServerName);
		
		serverUIList.add(createLabel("Description", 20, 330, 70, 14));
		tfServerDescription = createTextField(100, 328, 200, 20);
		serverUIList.add(tfServerDescription);
		
		serverUIList.add(createLabel("Model", 20, 360, 70, 14));
		tfServerModel = createTextField(100, 358, 200, 20);
		serverUIList.add(tfServerModel);
		
		serverUIList.add(createLabel("Model Brand", 20, 390, 80, 14));
		tfServerModelBrand = createTextField(100, 388, 200, 20);
		serverUIList.add(tfServerModelBrand);
		
		serverUIList.add(createLabel("Model Nature", 20, 420, 80, 14));
		tfServerModelNature = createTextField(100, 418, 200, 20);
		serverUIList.add(tfServerModelNature);
		
		serverUIList.add(createLabel("Category", 20, 450, 80, 14));
		tfServerCategory = createTextField(100, 448, 200, 20);
		serverUIList.add(tfServerCategory);
		
		serverUIList.add(createLabel("Reference", 20, 480, 80, 14));
		tfServerXReference = createTextField(100, 478, 200, 20);
		serverUIList.add(tfServerXReference);
		
		serverUIList.add(createLabel("Serial No", 20, 510, 80, 14));
		tfServerSerialNumber = createTextField(100, 508, 200, 20);
		serverUIList.add(tfServerSerialNumber);
		
		serverUIList.add(createLabel("Low", 330, 300, 70, 14));
		tfServerLow = createTextField(400, 298, 40, 20);
		serverUIList.add(tfServerLow);
		
		serverUIList.add(createLabel("High", 330, 330, 70, 14));
		tfServerHigh = createTextField(400, 328, 40, 20);
		serverUIList.add(tfServerHigh);
		
		serverUIList.add(createLabel("Starts At", 330, 360, 70, 14));
		tfServerStartsAt = createTextField(400, 358, 40, 20);
		serverUIList.add(tfServerStartsAt);
		
		serverUIList.add(createLabel("Num U", 330, 390, 70, 14));
		tfServerNumU = createTextField(400, 388, 40, 20);
		serverUIList.add(tfServerNumU);
		
		serverUIList.add(createLabel("Criticality", 330, 420, 70, 14));
		tfServerCriticality = createTextField(400, 418, 40, 20);
		serverUIList.add(tfServerCriticality);
		
		rackSelector = new JComboBox<String>();
		rackSelector.setBounds(328, 450, 120, 20);
		rackSelector.addItemListener(this);
		getContentPane().add(rackSelector);
		serverUIList.add(rackSelector);
		
		rackSearchSelector = new JComboBox<String>();
		rackSearchSelector.setBounds(17, 90, 120, 20);
		rackSearchSelector.addItemListener(this);
		getContentPane().add(rackSearchSelector);
		serverUIList.add(rackSearchSelector);

		// New
		int xPos = 580;
		serverUIList.add(createLabel("Name", 500, 70, 70, 14));
		tfNewServerName = createTextField(xPos, 68, 200, 20);
		serverUIList.add(tfNewServerName);
		
		serverUIList.add(createLabel("Description", 500, 100, 70, 14));
		tfNewServerDescription = createTextField(xPos, 98, 200, 20);
		serverUIList.add(tfNewServerDescription);
		
		serverUIList.add(createLabel("Model", 500, 130, 70, 14));
		tfNewServerModel = createTextField(xPos, 128, 200, 20);
		serverUIList.add(tfNewServerModel);
		
		serverUIList.add(createLabel("Model Brand", 500, 160, 80, 14));
		tfNewServerModelBrand = createTextField(xPos, 158, 200, 20);
		serverUIList.add(tfNewServerModelBrand);
		
		serverUIList.add(createLabel("Model Nature", 500, 190, 80, 14));
		tfNewServerModelNature = createTextField(xPos, 188, 200, 20);
		serverUIList.add(tfNewServerModelNature);
		
		serverUIList.add(createLabel("Category", 500, 220, 80, 14));
		tfNewServerCategory = createTextField(xPos, 218, 200, 20);
		serverUIList.add(tfNewServerCategory);
		
		serverUIList.add(createLabel("Reference", 500, 250, 80, 14));
		tfNewServerXReference = createTextField(xPos, 248, 200, 20);
		serverUIList.add(tfNewServerXReference);
		
		serverUIList.add(createLabel("Serial No", 500, 280, 80, 14));
		tfNewServerSerialNumber = createTextField(xPos, 278, 200, 20);
		serverUIList.add(tfNewServerSerialNumber);
		
		serverUIList.add(createLabel("Low", 500, 310, 70, 14));
		tfNewServerLow = createTextField(xPos, 308, 40, 20);
		serverUIList.add(tfNewServerLow);
		
		serverUIList.add(createLabel("High", 500, 340, 70, 14));
		tfNewServerHigh = createTextField(xPos, 338, 40, 20);
		serverUIList.add(tfNewServerHigh);
		
		serverUIList.add(createLabel("Starts At", 500, 370, 70, 14));
		tfNewServerStartsAt = createTextField(xPos, 368, 40, 20);
		serverUIList.add(tfNewServerStartsAt);
		
		serverUIList.add(createLabel("Num U", 500, 400, 70, 14));
		tfNewServerNumU = createTextField(xPos, 398, 40, 20);
		serverUIList.add(tfNewServerNumU);
		
		serverUIList.add(createLabel("Criticality", 500, 430, 70, 14));
		tfNewServerCriticality = createTextField(xPos, 428, 40, 20);
		serverUIList.add(tfNewServerCriticality);
		
		rackNewSelector = new JComboBox<String>();
		rackNewSelector.setBounds(xPos - 4, 460, 120, 20);
		getContentPane().add(rackNewSelector);
		serverUIList.add(rackNewSelector);
	}
	
	private JLabel createLabel(String text, int x, int y, int width, int height) {
		JLabel lblName = new JLabel(text);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(x, y, width, height);
		getContentPane().add(lblName);
		return lblName;
	}
	
	private JTextField createTextField(int x, int y, int width, int height) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.setColumns(10);
		getContentPane().add(textField);
		return textField;
	}
	
	private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.addActionListener(this);
		button.setBounds(x, y, width, height);
		getContentPane().add(button);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAll) {
			listAll();
		} else if (e.getSource() == buttonSearch) {
			search();
		} else if (e.getSource() == buttonUpdate) {
			update();
		} else if (e.getSource() == buttonDelete) {
			delete();
		} else if (e.getSource() == buttonCreate) {
			create();
		}
	}
	
	private void update() {
		try {
			switch (currentOption) {
			case "Server":
				server = (Server)servers.get(selectedRow);
				server.setName(tfServerName.getText());
				server.setDescription(tfServerDescription.getText());
				server.setLow(Integer.valueOf(tfServerLow.getText()));
				server.setHigh(Integer.valueOf(tfServerHigh.getText()));
				server.setStartsAt(Integer.valueOf(tfServerStartsAt.getText()));
				server.setNumU(Integer.valueOf(tfServerNumU.getText()));
				server.setCriticality(Integer.valueOf(tfServerCriticality.getText()));
				server.setCategory(tfServerCategory.getText());
				server.setModel(tfServerModel.getText());
				server.setModelBrand(tfServerModelBrand.getText());
				server.setModelNature(tfServerModelNature.getText());
				server.setxReference(tfServerXReference.getText());
				server.setSerialNumber(tfServerSerialNumber.getText());
				for (int i = 0; i < racks.size(); i++) {
					if (racks.get(i).getName() == rackSelector.getSelectedItem().toString()) {
						server.setRackIndex(racks.get(i).getIndex());
					}
				}
				serverDao.update(server);
				break;
			case "Rack":
				rack = (Rack)racks.get(selectedRow);
				rack.setName(tfRackName.getText());
				rack.setDescription(tfRackDescription.getText());
				for (int i = 0; i < centers.size(); i++) {
					if (centers.get(i).getName() == centerSelector.getSelectedItem().toString()) {
						rack.setDataCenterIndex(centers.get(i).getIndex());
					}
				}
				rackDao.update(rack);
				break;
			case "Center":
				dc = (DataCenter)centers.get(selectedRow);
				dc.setName(tfCenterName.getText());
				dc.setDescription(tfCenterDescription.getText());
				dcDao.update(dc);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void resetServerField() {
		tfNewServerCategory.setText("");
		tfNewServerCriticality.setText("");
		tfNewServerDescription.setText("");
		tfNewServerHigh.setText("");
		tfNewServerLow.setText("");
		tfNewServerStartsAt.setText("");
		tfNewServerNumU.setText("");
		tfNewServerName.setText("");
		tfNewServerModel.setText("");
		tfNewServerModelBrand.setText("");
		tfNewServerModelNature.setText("");
		tfNewServerSerialNumber.setText("");
		tfNewServerXReference.setText("");
	}
	
	private void resetRackFields() {
		tfNewRackName.setText("");
		tfNewRackDescription.setText("");
	}
	
	private void resetCenterFields() {
		tfNewCenterName.setText("");
		tfNewCenterDescription.setText("");
	}
	
	private void create() {
		try {
			switch (currentOption) {
			case "Server":
				Server server = new Server();
				server.setName(tfNewServerName.getText());
				server.setDescription(tfNewServerDescription.getText());
				server.setLow(Integer.valueOf(tfNewServerLow.getText()));
				server.setHigh(Integer.valueOf(tfNewServerHigh.getText()));
				server.setStartsAt(Integer.valueOf(tfNewServerStartsAt.getText()));
				server.setNumU(Integer.valueOf(tfNewServerNumU.getText()));
				server.setCriticality(Integer.valueOf(tfNewServerCriticality.getText()));
				server.setCategory(tfNewServerCategory.getText());
				server.setModel(tfNewServerModel.getText());
				server.setModelBrand(tfNewServerModelBrand.getText());
				server.setModelNature(tfNewServerModelNature.getText());
				server.setxReference(tfNewServerXReference.getText());
				server.setSerialNumber(tfNewServerSerialNumber.getText());
				for (int i = 0; i < racks.size(); i++) {
					if (racks.get(i).getName() == rackNewSelector.getSelectedItem().toString()) {
						server.setRackIndex(racks.get(i).getIndex());
					}
				}
				serverDao.create(server);
				resetServerField();
				break;
			case "Rack":
				Rack rack = new Rack();
				rack.setName(tfNewRackName.getText());
				rack.setDescription(tfNewRackDescription.getText());
				for (int i = 0; i < centers.size(); i++) {
					if (centers.get(i).getName() == centerNewSelector.getSelectedItem().toString()) {
						rack.setDataCenterIndex(centers.get(i).getIndex());
					}
				}
				rackDao.create(rack);
				resetRackFields();
				break;
			case "Center":
				DataCenter dc = new DataCenter();
				dc.setName(tfNewCenterName.getText());
				dc.setDescription(tfNewCenterDescription.getText());
				dcDao.create(dc);
				resetCenterFields();
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void delete() {
		try {
			switch (currentOption) {
			case "Server":
				server = (Server)servers.get(selectedRow);
				serverDao.delete(server);
				servers.remove(server);
				tableModel.removeRow(selectedRow);
				selectedRow = -1;
				break;
			case "Rack":
				rack = (Rack)racks.get(selectedRow);
				serverDao.deleteByRackId(rack.getIndex());
				rackDao.delete(rack);
				racks.remove(rack);
				tableModel.removeRow(selectedRow);
				selectedRow = -1;
				break;
			case "Center":
				dc = (DataCenter)centers.get(selectedRow);
				List<Drawable> someRacks = rackDao.findByCenterId(dc.getIndex());
				for (int i = 0; i < someRacks.size(); i++) {
					Rack r = (Rack)someRacks.get(i);
					serverDao.deleteByRackId(r.getIndex());
				}
				rackDao.deleteByCenterId(dc.getIndex());
				dcDao.delete(dc);
				centers.remove(dc);
				tableModel.removeRow(selectedRow);
				selectedRow = -1;
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void listAll() {
		isPopulated = false;
		
		try {
			switch (currentOption) {
			case "Center":
				centers = dcDao.getAll();
				loadTable(centers);
				break;
			case "Rack":
				centers = dcDao.getAll();
				racks = rackDao.getAll();
				for (Drawable r : racks) {
					Rack ar = (Rack)r;
					ar.setParent(findParent(centers, ar.getDataCenterIndex()));
				}
				loadTable(racks);
				fillCombo(centerSelector, centers);
				fillCombo(centerSearchSelector, centers);
				fillCombo(centerNewSelector, centers);
				break;
			case "Server":
				centers = dcDao.getAll();
				racks = rackDao.getAll();
				servers = serverDao.getAll();
				for (Drawable s : servers) {
					Server se = (Server)s;
					se.setParent(findParent(racks, se.getRackIndex()));
				}
				loadTable(servers);
				fillCombo(rackNewSelector, racks);
				fillCombo(rackSearchSelector, racks);
				fillCombo(rackSelector, racks);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		isPopulated = true;
	}
	
	private void fillCombo(JComboBox<String> combo, List<Drawable> list) {
		combo.removeAllItems();
		for (Drawable d : list) combo.addItem(d.getName());
	}
	
	private Drawable findParent(List<Drawable> list, int id) {
		for (Drawable d : list) if (d.getIndex() == id) return d;
		return null;
	}
	
	private void loadTable(List<Drawable> list) {
		int size = list == null || list.isEmpty() ? 0 : list.size();
		Object[][] data = new String[size][2];
		int i = 0;
		if (list != null && list.size() > 0) {
			for (Drawable d : list) {
				data[i][0] = d.getName();
				data[i++][1] = d.getParent() != null ? d.getParent().getName() : "System";
			}
		}
		
		tableModel = createTableData(data);
		table.setModel(tableModel);
	}
	
	private DefaultTableModel createTableData(Object[][] data) {
		return new DefaultTableModel(data, new String[] { "NAME", "PARENT" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private void search() {
		isPopulated = false;
		
		try {
			switch (currentOption) {
			case "Center":
				centers = dcDao.findByName(searchTextField.getText());
				loadTable(centers);
				break;
			case "Rack":
				centers = dcDao.getAll();
				racks = rackDao.findByName(searchTextField.getText());
				for (Drawable r : racks) {
					Rack ar = (Rack)r;
					ar.setParent(findParent(centers, ar.getDataCenterIndex()));
				}
				loadTable(racks);
				fillCombo(centerSelector, centers);
				fillCombo(centerSearchSelector, centers);
				fillCombo(centerNewSelector, centers);
				break;
			case "Server":
				centers = dcDao.getAll();
				racks = rackDao.getAll();
				servers = serverDao.findByName(searchTextField.getText());
				for (Drawable s : servers) {
					Server se = (Server)s;
					se.setParent(findParent(racks, se.getRackIndex()));
				}
				loadTable(servers);
				fillCombo(rackNewSelector, racks);
				fillCombo(rackSearchSelector, racks);
				fillCombo(rackSelector, racks);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		isPopulated = true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				switch (currentOption) {
				case "Server": 
					displayServer((Server)servers.get(selectedRow));
					break;
				case "Rack": 
					displayRack((Rack)racks.get(selectedRow)); 
					break;
				case "Center": 
					 displayCenter((DataCenter)centers.get(selectedRow));
					break;
				}
			}
		}
	}
	
	private void displayServer(Server server) {
		tfServerName.setText(server.getName());
		tfServerDescription.setText(server.getDescription());
		tfServerLow.setText(String.valueOf(server.getLow()));
		tfServerHigh.setText(String.valueOf(server.getHigh()));
		tfServerStartsAt.setText(String.valueOf(server.getStartsAt()));
		tfServerModel.setText(server.getModel());
		tfServerModelBrand.setText(server.getModelBrand());
		tfServerModelNature.setText(server.getModelNature());
		tfServerCategory.setText(server.getCategory());
		tfServerCriticality.setText(String.valueOf(server.getCriticality()));
		tfServerNumU.setText(String.valueOf(server.getNumU()));
		tfServerSerialNumber.setText(server.getSerialNumber());
		tfServerXReference.setText(server.getxReference());
		for (int i = 0; i < racks.size(); i++) {
			Rack rack = (Rack)racks.get(i);
			if (server.getRackIndex() == rack.getIndex()) {
				rackSelector.setSelectedIndex(i);
				break;
			}
		}
	}
	
	private void displayRack(Rack rack) {
		tfRackName.setText(rack.getName());
		tfRackDescription.setText(rack.getDescription());
		
		for (int i = 0; i < centers.size(); i++) {
			DataCenter center = (DataCenter)centers.get(i);
			if (center.getIndex() == rack.getDataCenterIndex()) {
				centerSelector.setSelectedIndex(i);
				break;
			}
		}
	}
	
	private void displayCenter(DataCenter center) {
		tfCenterName.setText(center.getName());
		tfCenterDescription.setText(center.getDescription());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == drawableSelector) {
				currentOption = e.getItem().toString();
				switch (e.getItem().toString()) {
				case "Center":
					loadCenterUI();
					tableModel = createTableData(null);
					table.setModel(tableModel);
					listAll();
					break;
				case "Rack": 
					loadRackUI();
					tableModel = createTableData(null);
					table.setModel(tableModel);
					listAll();
					break;
				case "Server": 
					loadServerUI();
					tableModel = createTableData(null);
					table.setModel(tableModel);
					listAll();
					break;
				}
			} else if (e.getSource() == centerSearchSelector && isPopulated) {
				try {
					for (int i = 0; i < centers.size(); i++) {
						if (centers.get(i).getName().equals((e.getItem().toString()))) {
							racks = rackDao.findByCenterId(centers.get(i).getIndex());
							for (Drawable r : racks) {
								Rack ar = (Rack)r;
								ar.setParent(findParent(centers, ar.getDataCenterIndex()));
							}
							break;
						}
					}
					loadTable(racks);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (e.getSource() == rackSearchSelector && isPopulated) {
				try {
					for (int i = 0; i < racks.size(); i++) {
						if (racks.get(i).getName().equals((e.getItem().toString()))) {
							servers = serverDao.findByRackId(racks.get(i).getIndex());
							for (Drawable s : servers) {
								Server se = (Server)s;
								se.setParent(findParent(racks, se.getRackIndex()));
							}
							break;
						}
					}
					loadTable(servers);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}
}