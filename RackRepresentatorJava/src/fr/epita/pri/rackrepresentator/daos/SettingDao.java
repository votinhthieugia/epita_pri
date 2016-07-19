package fr.epita.pri.rackrepresentator.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.epita.pri.rackrepresentator.settings.Setting;

public class SettingDao extends SqlDao {
	public void save(Setting setting) {
		try {
			Connection connection = getConnection();
			
			if (connection != null) {
				String query = "update settings set value = ? where key = ?";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, String.valueOf(setting.getNumDisplayedRacks()));
					statement.setString(2, "NUM_DISPLAY_RACK");
					statement.execute();
					statement.close();
				}
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, String.valueOf(setting.getReloadTime()));
					statement.setString(2, "RELOAD_TIME");
					statement.execute();
					statement.close();
				}
				
				closeConnection(connection);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void load(Setting setting) {
		try {
			Connection connection = getConnection();
			
			if (connection != null) {
				String query = "select * from settings";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						if (resultSet.getString("key").equals("NUM_DISPLAY_RACK"))
							setting.setNumDisplayedRacks(Integer.valueOf(resultSet.getString("value")));
						else if (resultSet.getString("key").equals("RELOAD_TIME"))
							setting.setReloadTime(Integer.valueOf(resultSet.getString("value")));
					}
					
					resultSet.close();
					statement.close();
				}
				
				closeConnection(connection);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
