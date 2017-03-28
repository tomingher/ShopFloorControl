package hu.tomi.shopfloor.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.tomi.shopfloor.model.bean.Storage;

public class StorageTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8437705751517054149L;
	
	private String[] columnNames = new String[] {"Storage ID", "Warehouse Name", "Number Of Shelves", "Height", "Width", "Depth",  "Load Capacity"};
		
	List<Storage> storages;	

	public StorageTableModel(List<Storage> storages	) {
		super();
		this.storages = storages;
	}

	@Override
	public int getRowCount() {

		return storages.size();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Storage storage = storages.get(rowIndex);
		String askedColumnName = columnNames[columnIndex];
		
		if (askedColumnName.equals("Storage ID")) {
            return storage.getId();
        }else if (askedColumnName.equals("Number Of Shelves")) {
            return storage.getNumberOfShelves();
        }else if (askedColumnName.equals("Height")) {
            return storage.getHeight();
        }else if (askedColumnName.equals("Width")) {
            return storage.getWidth();
        }else if (askedColumnName.equals("Depth")) {
            return storage.getDepth();
        }else if (askedColumnName.equals("Warehouse Name")) {
            return storage.getWarehouseName();
        }else if (askedColumnName.equals("Load Capacity")) {
            return storage.getLoadCApacity();
        }		
		
		return "fakap";
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
