package fr.epita.pri.rackrepresentator.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.Rack;

public class RackDao extends SqlDao {
	public List<Rack> getAll() {
		List<Rack> racks = new ArrayList<Rack>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from racks";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						racks.add(createFromResultSet(resultSet));
					}
					
					resultSet.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
		
		return racks;
	}
	
	private Rack createFromResultSet(ResultSet resultSet) throws Exception {
		return new Rack(resultSet.getString("name"), 
						resultSet.getString("description"), 
						resultSet.getInt("center_id"), 
						resultSet.getInt("id"));
	}
	
	public List<Rack> findByName(String name) {
		List<Rack> racks = new ArrayList<Rack>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from racks where name = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, name);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						racks.add(createFromResultSet(resultSet));
					}
					
					resultSet.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
		
		return racks;
	}
	
	public Rack findById(int id) {
		Rack rack = null;
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from racks where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, id);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						rack = createFromResultSet(resultSet);
					}
					
					resultSet.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
		
		return rack;
	}
	
	public List<Rack> findByCenterId(int centerId) {
		List<Rack> racks = new ArrayList<Rack>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from racks where center_id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, centerId);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						racks.add(createFromResultSet(resultSet));
					}
					
					resultSet.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
		
		return racks;
	}
	
	public void create(Rack rack) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "insert into racks(name, description, center_id) values(?,?,?)";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					int index = 1;
					statement.setString(index++, rack.getName());
					statement.setString(index++, rack.getDescription());
					statement.setInt(index++, rack.getDataCenterIndex());
					statement.execute();
					ResultSet resultSet = statement.getGeneratedKeys();
					
					if (resultSet.next()) {
						rack.setIndex(resultSet.getInt(1));
					}
					
					resultSet.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
	}
	
	public void update(Rack rack) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "update racks set name = ?, description = ?, center_id = ? where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					int index = 1;
					statement.setString(index++, rack.getName());
					statement.setString(index++, rack.getDescription());
					statement.setInt(index++, rack.getDataCenterIndex());
					statement.setInt(index++, rack.getIndex());
					statement.execute();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
	}
	
	public void delete(Rack rack) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "delete from racks where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					int index = 1;
					statement.setInt(index++, rack.getIndex());
					statement.execute();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
	}
}