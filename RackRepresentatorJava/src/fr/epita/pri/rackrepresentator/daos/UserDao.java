package fr.epita.pri.rackrepresentator.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epita.pri.rackrepresentator.encryption.IEncryptor;
import fr.epita.pri.rackrepresentator.models.User;

public class UserDao extends SqlDao implements IUserDao {
	private IEncryptor encryptor;
	
	public UserDao(IEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	@Override
	public User authenticate(String userName, String password) throws Exception {
		Connection connection = getConnection();
		
		if (connection != null) {
			User user = null;
			String query = "select * from users where name = ? and password = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				int index = 1;
				statement.setString(index++, userName);
				statement.setString(index++, encryptor.encryptUserPassword(password));
				ResultSet resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					user = new User(resultSet.getString("name"), resultSet.getString("password"), resultSet.getInt("type"));
				}
				
				resultSet.close();
				statement.close();
			}
			
			closeConnection(connection);
			return user;
		}
		
		return null;
	}

	@Override
	public List<User> getAll() throws Exception {
		Connection connection = getConnection();
		List<User> users = new ArrayList<User>();
		
		if (connection != null) {
			String query = "select * from users";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getInt("type")));
				}
				
				resultSet.close();
				statement.close();
			}
			
			closeConnection(connection);
		}
		
		return users;
	}

	@Override
	public List<User> findByName(String name) throws Exception {
		Connection connection = getConnection();
		List<User> users = new ArrayList<User>();
		
		if (connection != null) {
			String query = "select * from users where name = ? and type = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				int index = 1;
				statement.setString(index++, name);
				statement.setInt(index++, User.Normal);
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getInt("type")));
				}
				
				resultSet.close();
				statement.close();
			}
			
			closeConnection(connection);
		}
		
		return users;
	}

	@Override
	public void create(User user) throws Exception {
		Connection connection = getConnection();
		
		if (connection != null) {
			String query = "insert into users(name, password, type) values(?,?,?)";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				int index = 1;
				String encryptedPassword = encryptor.encryptUserPassword(user.getPassword());
				user.setPassword(encryptedPassword);
				statement.setString(index++, user.getName());
				statement.setString(index++, user.getPassword());
				statement.setInt(index++, user.getType());
				statement.execute();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
				statement.close();
			}
			
			closeConnection(connection);
		}
	}

	@Override
	public void update(User user) throws Exception {
		Connection connection = getConnection();
		
		if (connection != null) {
			String query = "update users set name = ?, password = ?, type = ? where id = ?";
			
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				int index = 1;
				String encryptedPassword = encryptor.encryptUserPassword(user.getPassword());
				user.setPassword(encryptedPassword);
				statement.setString(index++, user.getName());
				statement.setString(index++, user.getPassword());
				statement.setInt(index++, user.getType());
				statement.setInt(index++, user.getId());
				statement.execute();
				statement.close();
			}
			
			closeConnection(connection);
		}
	}

	@Override
	public void delete(User user) throws Exception {
		Connection connection = getConnection();
		
		if (connection != null) {
			String query = "delete from users where id = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				int index = 1;
				statement.setInt(index++, user.getId());
				statement.execute();
				statement.close();
			}
			
			closeConnection(connection);
		}
	}
}