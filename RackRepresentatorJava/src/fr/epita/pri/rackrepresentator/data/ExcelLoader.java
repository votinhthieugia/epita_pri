package fr.epita.pri.rackrepresentator.data;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.epita.pri.rackrepresentator.models.DataCenter;
import fr.epita.pri.rackrepresentator.models.DataSystem;
import fr.epita.pri.rackrepresentator.models.Drawable;
import fr.epita.pri.rackrepresentator.models.Rack;
import fr.epita.pri.rackrepresentator.models.Server;

public class ExcelLoader implements IDataLoader {
	enum ColumnName {
		DataCenterName,
		Rack,
		Low,
		High,
		StartsAt,
		NumberOfU,
		CiName,
		ModelBrand,
		ModelNature,
		Category,
		Model,
		CiDescription,
		XReference,
		Criticality,
		SerialNumber
	};
	
	@Override
	public Drawable loadAll() {
		return loadAllFromFile("/Users/hoanganhdoan/Documents/workspace/epita_pri/RackRepresentatorJava/resources/DatacentreRack.xlsx");
		//return loadAllFromFile("C:\\Users\\Gustavo\\Downloads\\DatacentreRack.xlsx");
		
        
//	       Rack rack = new Rack("r1", "desc", 0, 0);
//	       rack.position(50, 200, 100, 400);
//	      
//	       Server server = new Server("s1", "desc", 0, 0, 0, 0);
//	       server.position(0, 0, 4, 1);
//	      
//	       rack.addServer(server);
//	       return rack;
	}

	@Override
	public Drawable loadDataCenter(int index) {
		return null;
	}

	@Override
	public Drawable loadRack(int index) {
		return null;
	}

	@Override
	public Drawable loadServer(int index) {
		return null;
	}

	@Override
	public Drawable loadAllFromFile(String filePath) {
		DataSystem system = new DataSystem("IBM", "Data Centre Rack");
		
		try {
			FileInputStream input = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(input);
			Sheet sheet = workbook.getSheetAt(0);
			int numRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 1; i < numRows + 1; i++) {
				Row row = sheet.getRow(i);
				if (row == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK) break;
				String dcName = row.getCell(ColumnName.DataCenterName.ordinal()).getStringCellValue();
				DataCenter dc = system.findByName(dcName);
				
				if (dc == null) {
					dc = new DataCenter(dcName, dcName, system.getCenters().size());
					system.addCenter(dc);
				}
				
				String rackName = row.getCell(ColumnName.Rack.ordinal()).getStringCellValue();
				Rack rack = dc.findByName(rackName);
				
				if (rack == null) {
					rack = new Rack(rackName, rackName, dc.getIndex(), dc.getRacks().size());
					dc.addRack(rack);
				}
				
				Server server = new Server(row.getCell(ColumnName.CiName.ordinal()).getStringCellValue(), 
											row.getCell(ColumnName.CiDescription.ordinal()).getStringCellValue(), 
											rack.getServers().size());
				server.setLow((int)row.getCell(ColumnName.Low.ordinal()).getNumericCellValue());
				server.setHigh((int)row.getCell(ColumnName.High.ordinal()).getNumericCellValue());
				server.setStartsAt((int)row.getCell(ColumnName.StartsAt.ordinal()).getNumericCellValue());
				server.setNumU((int)row.getCell(ColumnName.NumberOfU.ordinal()).getNumericCellValue());
				server.setCriticality((int)row.getCell(ColumnName.Criticality.ordinal()).getNumericCellValue());
				server.setCategory(row.getCell(ColumnName.Category.ordinal()).getStringCellValue());
				server.setModel(row.getCell(ColumnName.Model.ordinal()).getStringCellValue());
				server.setModelBrand(row.getCell(ColumnName.ModelBrand.ordinal()).getStringCellValue());
				server.setModelNature(row.getCell(ColumnName.ModelNature.ordinal()).getStringCellValue());
				server.setxReference(row.getCell(ColumnName.XReference.ordinal()).getStringCellValue());
				
				Cell cell = row.getCell(ColumnName.SerialNumber.ordinal());
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) server.setSerialNumber(cell.getStringCellValue());
				else server.setSerialNumber(String.valueOf(cell.getNumericCellValue()));
				
				Server bladeServer = rack.findByLowHigh(server.getLow(), server.getHigh());
				
				if (bladeServer != null) {
					server.setNext(bladeServer.getNext());
					bladeServer.setNext(server);
				} else {
					rack.addServer(server);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return system;
	}
}