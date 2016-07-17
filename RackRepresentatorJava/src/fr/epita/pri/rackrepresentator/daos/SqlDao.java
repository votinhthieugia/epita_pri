package fr.epita.pri.rackrepresentator.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDao {
	public SqlDao() {
	}
	
	protected String getConnectionString() {
		return "jdbc:mysql://localhost:3306/pri";
	}
	
	protected Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		return DriverManager.getConnection(getConnectionString(), "root", "");
	}
	
	public void closeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}