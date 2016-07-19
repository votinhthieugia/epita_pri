package fr.epita.pri.rackrepresentator.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.Server;

public class ServerDao extends SqlDao {
	public List<Server> getAll() {
		List<Server> servers = new ArrayList<Server>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from servers";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						servers.add(createFromResultSet(resultSet));
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
		
		return servers;
	}
	
	private Server createFromResultSet(ResultSet resultSet) throws Exception {
		Server server = new Server(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("rack_id"), resultSet.getInt("id"));
		server.setCategory(resultSet.getString("category"));
		server.setCriticality(resultSet.getInt("criticality"));
		server.setLow(resultSet.getInt("low"));
		server.setHigh(resultSet.getInt("high"));
		server.setNumU(resultSet.getInt("num_u"));
		server.setStartsAt(resultSet.getInt("starts_at"));
		server.setRackIndex(resultSet.getInt("rack_id"));
		server.setxReference(resultSet.getString("x_reference"));
		server.setSerialNumber(resultSet.getString("serial_number"));
		server.setModel(resultSet.getString("model"));
		server.setModelBrand(resultSet.getString("model_brand"));
		server.setModelNature(resultSet.getString("model_nature"));
		return server;
	}
	
	public List<Server> findByName(String name) {
		List<Server> servers = new ArrayList<Server>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from servers where name = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, name);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						servers.add(createFromResultSet(resultSet));
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
		return servers;
	}
	
	public List<Server> findByRackId(int rackId) {
		List<Server> servers = new ArrayList<Server>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from servers where rack_id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, rackId);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						servers.add(createFromResultSet(resultSet));
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
		
		return servers;
	}
	
	public Server findById(int id) {
		Server server = null;
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from servers where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, id);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						server = createFromResultSet(resultSet);
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
		
		return server;
	}
	
	public void create(Server server) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "insert into servers(rack_id, name, description, low, high, starts_at, num_u, model, model_nature, model_brand, category, x_reference, criticality, serial_number) "
								+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					int index = 1;
					statement.setInt(index++, server.getRackIndex());
					statement.setString(index++, server.getName());
					statement.setString(index++, server.getDescription());
					statement.setInt(index++, server.getLow());
					statement.setInt(index++, server.getHigh());
					statement.setInt(index++, server.getStartsAt());
					statement.setInt(index++, server.getNumU());
					statement.setString(index++, server.getModel());
					statement.setString(index++, server.getModelNature());
					statement.setString(index++, server.getModelBrand());
					statement.setString(index++, server.getCategory());
					statement.setString(index++, server.getxReference());
					statement.setInt(index++, server.getCriticality());
					statement.setString(index++, server.getSerialNumber());
					statement.execute();
					
					ResultSet rs = statement.getGeneratedKeys();
					
					if (rs.next()) {
						server.setIndex(rs.getInt(1));
					}

					rs.close();
					statement.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) closeConnection(connection);
		}
	}
	
	public void update(Server server) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "update servers set rack_id=?,name=?,description=?,low=?, high=?, starts_at=?, num_u=?, model=?, model_nature=?, model_brand=?, category=?, x_reference=?, criticality=?, serial_number=? where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					int index = 1;
					statement.setInt(index++, server.getRackIndex());
					statement.setString(index++, server.getName());
					statement.setString(index++, server.getDescription());
					statement.setInt(index++, server.getLow());
					statement.setInt(index++, server.getHigh());
					statement.setInt(index++, server.getStartsAt());
					statement.setInt(index++, server.getNumU());
					statement.setString(index++, server.getModel());
					statement.setString(index++, server.getModelNature());
					statement.setString(index++, server.getModelBrand());
					statement.setString(index++, server.getCategory());
					statement.setString(index++, server.getxReference());
					statement.setInt(index++, server.getCriticality());
					statement.setString(index++, server.getSerialNumber());
					statement.setInt(index++, server.getIndex());
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
	
	public void delete(Server server) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "delete from servers where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, server.getIndex());
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