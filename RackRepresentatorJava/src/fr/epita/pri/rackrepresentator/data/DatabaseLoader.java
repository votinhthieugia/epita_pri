package fr.epita.pri.rackrepresentator.data;

import java.util.List;

import fr.epita.pri.rackrepresentator.daos.DataCenterDao;
import fr.epita.pri.rackrepresentator.daos.RackDao;
import fr.epita.pri.rackrepresentator.daos.ServerDao;
import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;

public class DatabaseLoader {
	private ServerDao serverDao;
	private RackDao rackDao;
	private DataCenterDao dcDao;
	
	public DatabaseLoader() {
		serverDao = new ServerDao();
		rackDao = new RackDao();
		dcDao = new DataCenterDao();
	}

	public Drawable loadAll() {
		DataSystem system = new DataSystem("IBM", "Data Centre Rack");
		try {
			List<DataCenter> centers = dcDao.getAll();
			for (DataCenter center : centers) {
				List<Rack> racks = rackDao.findByCenterId(center.getIndex());
				for (Rack rack : racks) {
					List<Server> servers = serverDao.findByRackId(rack.getIndex());
					System.out.println(rack.getName() + " " + servers.size());
					for (Server server : servers) {
						Server bladeServer = rack.findByLowHigh(server.getLow(), server.getHigh());
						if (bladeServer != null) {
							server.setNext(bladeServer.getNext());
							bladeServer.setNext(server);
						} else {
							rack.addChild(server);
						}
					}
					center.addChild(rack);
				}
				system.addChild(center);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return system;
	}
}