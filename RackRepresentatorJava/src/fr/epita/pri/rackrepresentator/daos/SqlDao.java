package fr.epita.pri.rackrepresentator.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDao {
	public SqlDao() {
	}
	
	protected String getConnectionString() {
//		return "jdbc:mysql://localhost:3306/pri";
		return "jdbc:sqlite:resources/pri.db";
	}
	
	protected Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("org.sqlite.JDBC");
//		return DriverManager.getConnection(getConnectionString(), "root", "");
		return DriverManager.getConnection(getConnectionString());
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