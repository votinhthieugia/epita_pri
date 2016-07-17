package fr.epita.pri.rackrepresentator.daos;

import java.util.List;

import fr.epita.pri.rackrepresentator.models.User;

public interface IUserDao {
	public User authenticate(String userName, String password) throws Exception;
	public List<User> getAll() throws Exception;
	public List<User> findByName(String name) throws Exception;
	public void create(User user) throws Exception;
	public void update(User user) throws Exception;
	public void delete(User user) throws Exception;
}