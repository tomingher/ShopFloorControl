package hu.tomi.shopfloor.view.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.tomi.shopfloor.model.bean.ShelfData;

public class ShelfDataTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4696443665891271572L;
	
	private String[] columnNames = new String[] { "Warehouse name", "Storage name", "Shelf", "Shelf width", "Location" };
	
	List<ShelfData> shelfData;

	public ShelfDataTableModel(List<ShelfData> shelfData) {
		super();
		this.shelfData = shelfData;
	}

	@Override
	public int getRowCount() {
		
		return shelfData.size();
	}

	@Override
	public int getColumnCount() {
		
		return columnNames.length;
	}
	
	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		ShelfData shelfdata = shelfData.get(rowIndex);
		String askedColumnName = columnNames[columnIndex];
		
		if (askedColumnName.equals("Warehouse name")) {
            return shelfdata.getWarehouseName();
        }else if (askedColumnName.equals("Storage name")) {
            return shelfdata.getStorageName();
        }else if (askedColumnName.equals("Shelf")) {
            return shelfdata.getShelf();
        }else if (askedColumnName.equals("Shelf width")) {
            return shelfdata.getShelfWidth();
        }else if (askedColumnName.equals("Location")) {
            return shelfdata.getLocation();
        }		
		
		return "fakap";
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
