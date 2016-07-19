package fr.epita.pri.rackrepresentator.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.Drawable;

public class DataCenterDao extends SqlDao {
	public List<Drawable> getAll() {
		List<Drawable> centers = new ArrayList<Drawable>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from centers";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						centers.add(createFromResultSet(resultSet));
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
		
		return centers;
	}
	
	private DataCenter createFromResultSet(ResultSet resultSet) throws Exception {
		return new DataCenter(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("id"));
	}
	
	public List<Drawable> findByName(String name) {
		List<Drawable> centers = new ArrayList<Drawable>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from centers where name like ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, "%" + name + "%");
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						centers.add(createFromResultSet(resultSet));
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
		
		return centers;
	}
	
	public DataCenter findById(int id) {
		DataCenter center = null;
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "select * from centers where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, id);
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						center = createFromResultSet(resultSet);
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
		
		return center;
	}
	
	public void create(DataCenter dc) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "insert into centers(name, description) values (?, ?)";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, dc.getName());
					statement.setString(2, dc.getDescription());
					statement.execute();
					ResultSet resultSet = statement.getGeneratedKeys();
					
					while (resultSet.next()) {
						dc.setIndex(resultSet.getInt(1));
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
	
	public void update(DataCenter dc) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "update centers set name = ?, description = ? where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, dc.getName());
					statement.setString(2, dc.getDescription());
					statement.setInt(3, dc.getIndex());
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
	
	public void delete(DataCenter dc) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			if (connection != null) {
				String query = "delete from centers where id = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, dc.getIndex());
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
	
	public static void main(String[] args) {
		DataCenterDao dao = new DataCenterDao();
		try {
			dao.create(new DataCenter("R216", "nothing", 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}