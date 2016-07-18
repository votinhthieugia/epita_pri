package fr.epita.pri.rackrepresentator.main;

import fr.epita.pri.rackrepresentator.daos.IUserDao;
import fr.epita.pri.rackrepresentator.daos.UserDao;
import fr.epita.pri.rackrepresentator.encryption.IEncryptor;
import fr.epita.pri.rackrepresentator.encryption.MD5Encryptor;
import fr.epita.pri.rackrepresentator.models.User;

public class SessionController {
	private static User user;
	
	public static void create(String name, String password) {
		IEncryptor md5Encyptor = new MD5Encryptor();
		IUserDao userDao = new UserDao(md5Encyptor);
		try {
			user = userDao.authenticate(name, password);
		} catch (Exception ex) {
			user = null;
			ex.printStackTrace();
		}
	}
	
	public static void destroy() {
		user = null;
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SessionController.user = user;
	}

	public static boolean isAdminSession() {
		return user != null && user.isAdmin();
	}
	
	public static boolean exists() {
		return user != null;
	}
}